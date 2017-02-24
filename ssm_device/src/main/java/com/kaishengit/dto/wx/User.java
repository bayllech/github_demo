package com.kaishengit.dto.wx;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String userid;
    private String name;
    private String mobile;
    private List<Integer> department;

}
