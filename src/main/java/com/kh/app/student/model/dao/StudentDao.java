package com.kh.app.student.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.student.model.dto.Student;

public interface StudentDao {

	int insertStudent(SqlSession sqlSession, Student student);

}
