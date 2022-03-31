package com.happy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.happy.bean.Student;

import com.happy.service.StudentService;


@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/saveS", method = RequestMethod.POST)
	public ModelAndView savestudent(@ModelAttribute("command") Student studentBean, 
			BindingResult result) {
		Student student = prepareModel(studentBean);
		studentService.addStudent(student);
		return new ModelAndView("redirect:/addS.html");
	}

	@RequestMapping(value="/students", method = RequestMethod.GET)
	public ModelAndView listStudents() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("students",  prepareListofBean(studentService.listStudents()));
		return new ModelAndView("studentList", model);
	}

	@RequestMapping(value = "/addS", method = RequestMethod.GET)
	public ModelAndView addStudent(@ModelAttribute("command")  Student studentBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("students",  prepareListofBean(studentService.listStudents()));
		return new ModelAndView("addStudents", model);
	}
	
	@RequestMapping(value = "/indexS", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("indexS");
	}
	
	@RequestMapping(value = "/deleteS", method = RequestMethod.GET)
	public ModelAndView editStudent(@ModelAttribute("command")  Student studentBean,
			BindingResult result) {
		studentService.deleteStudent(prepareModel(studentBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", null);
		model.put("students",  prepareListofBean(studentService.listStudents()));
		return new ModelAndView("addStudent", model);
	}
	
	@RequestMapping(value = "/editS", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@ModelAttribute("command")  Student studentBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("student", prepareStudentBean(studentService.getStudent(studentBean.getId())));
		model.put("students",  prepareListofBean(studentService.listStudents()));
		return new ModelAndView("addStudent", model);
	}
	
	private Student prepareModel(Student studentBean){
		Student student = new Student();
		student.setStdteacher(studentBean.getStdteacher());
		student.setGrade(studentBean.getGrade());
		student.setAge(studentBean.getAge());
		student.setName(studentBean.getName());
		student.setId(studentBean.getId());
		studentBean.setId(null);
		return student;
	}
	
	private List<Student> prepareListofBean(List<Student> students){
		List<Student> beans = null;
		if(students != null && !students.isEmpty()){
			beans = new ArrayList<Student>();
			Student bean = null;
			for(Student student : students){
				bean = new Student();
				bean.setId(student.getId());
				bean.setName(student.getName());
				bean.setAge(student.getAge());
				bean.setGrade(student.getGrade());
				bean.setStdteacher(student.getStdteacher());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	private Student prepareStudentBean(Student student){
		Student bean = new Student();
		bean.setStdteacher(student.getStdteacher());
		bean.setGrade(student.getGrade());
		bean.setAge(student.getAge());
		bean.setName(student.getName());
		bean.setId(student.getId());
		return bean;
	}
}
