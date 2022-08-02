package com.kh.app.student.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.student.model.dto.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int insertStudent(SqlSession sqlSession, Student student) {
		return sqlSession.insert("student.insertStudent", student);
	}
}
