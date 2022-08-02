package com.kh.app.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.app.common.AbstractController;
import com.kh.app.student.model.service.StudentService;

public class StudentEnrollController extends AbstractController {

	private StudentService studentService;
	
	public StudentEnrollController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//student db 등록
		return "redirect:/";
	}
}
