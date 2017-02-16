package com.kaishengit;

import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.pojo.School;
import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
//        map.put("name", "tom");
        map.put("address", "河南");
        Student student = studentMapper.findByParam(map);
    }

    @Test
    public void update() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lulu");
        map.put("id", "3");
        studentMapper.update(map);

        sqlSession.commit();
    }

    @Test
    public void findByIds() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.findByIds(Arrays.asList(1,2,3,4,5));
        for (Student s : studentList) {
            System.out.println(s);
        }
        sqlSession.close();
    }

    @Test
    public void batcSave() {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("liu","郑州"));
        studentList.add(new Student("lei","洛阳"));
        studentMapper.batchSave(studentList);

        sqlSession.commit();
    }
}
