package com.happy.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happy.bean.Student;



@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().saveOrUpdate(student);
	}

	@SuppressWarnings("unchecked")
	public List<Student> listStudents() {
		return (List<Student>) sessionFactory.getCurrentSession().createCriteria(Student.class).list();
	}

	public Student getStudent(int stdid) {
		return (Student) sessionFactory.getCurrentSession().get(Student.class, stdid);
	}

	public void deleteStudent(Student student) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Student WHERE stdid = "+student.getId()).executeUpdate();
	}


}
