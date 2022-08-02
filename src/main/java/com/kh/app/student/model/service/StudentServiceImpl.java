package com.kh.app.student.model.service;

import static com.kh.app.common.SqlSessionUtils.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.student.model.dao.StudentDao;
import com.kh.app.student.model.dto.Student;

public class StudentServiceImpl implements StudentService {

	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public int insertStudent(Student student) {
		SqlSession sqlSession = getSqlSession();
		int result = 0;
		try {
			result = studentDao.insertStudent(sqlSession, student);
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		
		return result;
	}
}
