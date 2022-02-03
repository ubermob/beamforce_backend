package ru.beamforce.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author Andrey Korneychuk on 03-Feb-22
 * @version 1.0
 */
public class Student {
	@NotNull(message = "LastName can not be null!!")
	@NotEmpty(message = "LastName can not be empty!!")
	private String name;

	@NotNull(message = "Choose the subject count you are going to study!")
	@Min(value = 4, message = "Student should enroll to minimum 4 subjects!!")
	@Max(value = 8, message = "Student can enroll to maximum 8 subjects!!")
	private int subjectCount;

	@NotNull
	@Min(1)
	@Max(12)
	private int grade;

	@NotNull
	@Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
	@Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")
	private String mobileNo;

	@NotNull(message = "Please enter birth date")
	@Past(message = "Birth date should be less than current date!!")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Student() {
	}

	public Student(String name, int subjectCount, int grade, String mobileNo, LocalDate birthDate) {
		this.name = name;
		this.subjectCount = subjectCount;
		this.grade = grade;
		this.mobileNo = mobileNo;
		this.birthDate = birthDate;
	}
}