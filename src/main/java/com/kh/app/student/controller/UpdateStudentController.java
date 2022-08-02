package com.kh.app.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kh.app.common.AbstractController;
import com.kh.app.student.model.dto.Student;
import com.kh.app.student.model.service.StudentService;

public class UpdateStudentController extends AbstractController {

	private static Logger log = Logger.getLogger(UpdateStudentController.class);
	private StudentService studentService;
	
	public UpdateStudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setTel(tel);
		log.debug(student);
		
		//2. 업무 로직
		int result = studentService.updateStudent(student);
		
		//3. json 응답처리
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(student, response.getWriter());
		
		return null;
	}
}
