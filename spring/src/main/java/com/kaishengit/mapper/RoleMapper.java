package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bayllech on 2017/1/13.
 */
public interface RoleMapper {

    List<Role> findAll();

    Role findById(Integer roleId);

    void saveRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
