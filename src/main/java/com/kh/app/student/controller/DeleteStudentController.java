package com.kh.app.student.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.kh.app.common.AbstractController;
import com.kh.app.student.model.service.StudentService;

public class DeleteStudentController extends AbstractController {

	private static Logger log = Logger.getLogger(DeleteStudentController.class);
	private StudentService studentService;
	
	public DeleteStudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		log.debug(no);
		
		//2. 업무 로직
		int result = studentService.deleteStudent(no);
		
		//3. json 응답 처리
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "삭제되었습니다.");
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
		
		return null;
	}
}
