package com.kh.app.student.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession sqlSession, Student student) {
		return sqlSession.insert("student.insertStudent", student);
	}
	
	@Override
	public int insertStudentMap(SqlSession sqlSession, Map<String, Object> map) {
		return sqlSession.insert("student.insertStudentMap", map);
	}
}
