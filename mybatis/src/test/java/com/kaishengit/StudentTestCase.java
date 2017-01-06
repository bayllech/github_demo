package com.kaishengit;

import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bayllech on 2017/1/5.
 */
public class StudentTestCase {
    private SqlSession sqlSession = null;
    @Before
    public void getsqlSession() {
        sqlSession = SqlSessionFactoryUtil.getSqlSession();
    }


    @Test
    public void findStudent() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findById(1);
        School school = student.getSchool();
        System.out.println(student);
        System.out.println(school);

    }

    @Test
    public void findStudentByParam() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String,Object> map = new HashMap<>();
        map.put("name", "tom");
        map.put("address", "河南");
        Student student = studentMapper.findByParam(map);
    }

}
