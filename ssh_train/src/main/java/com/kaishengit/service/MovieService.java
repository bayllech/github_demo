package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.pojo.Movie;
import com.kaishengit.util.Page;
import com.kaishengit.util.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieDao movieDao;
    @Value("${pageSize}")
    private int pageSize;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    public Movie findById(Integer id) {
        return movieDao.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Movie> findByPage(int pageNo, List<QueryParam> queryParamList) {
        return movieDao.findByPage(pageNo,pageSize,queryParamList);
    }

}
