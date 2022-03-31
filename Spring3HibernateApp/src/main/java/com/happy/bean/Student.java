package com.happy.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "stdid")
	private Integer id;
	@Column(name="stdname")
	private String name;
	@Column(name="stdage")
	private Integer age;
	@Column(name="stdgrade")
	private String grade;
	@Column(name="stdteacher")
	private String stdteacher;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStdteacher() {
		return stdteacher;
	}
	public void setStdteacher(String stdteacher) {
		this.stdteacher = stdteacher;
	}
	

}


