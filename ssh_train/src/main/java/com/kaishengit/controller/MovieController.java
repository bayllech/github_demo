package com.kaishengit.controller;

import com.kaishengit.pojo.Movie;
import com.kaishengit.service.MovieService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("movieList",movieService.findAll());
        return "list";
    }

    @GetMapping("/{id:\\d+}")
    public String movieCategory(@PathVariable Integer id, Model model) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
       return "movie";

    }
}
