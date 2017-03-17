package com.kaishengit.dao;

import com.kaishengit.pojo.Movie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDao extends BaseDao<Movie,Integer> {





    /*@Autowired
    private SessionFactory sessionFactory;

    public Session session(){
        return sessionFactory.getCurrentSession();
    }

    public List<Movie> findAll() {
        Criteria criteria = session().createCriteria(Movie.class);
        return criteria.list();
    }

    public Movie findById(Integer id) {
        return (Movie) session().get(Movie.class,id);
    }*/
}
