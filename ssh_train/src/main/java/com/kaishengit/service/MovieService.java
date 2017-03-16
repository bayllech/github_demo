package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieDao movieDao;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieDao.findAll();
    }
}
