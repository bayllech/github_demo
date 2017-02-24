package com.kaishengit.service.Impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.service.WXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class UserServiceImpl implements UserService {

/*    @Autowired
    private UserDao userDao;*/

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private WXService wxService;


    @Override
    @Transactional
    public void save(User user, Integer[] roleIds) {
        userMapper.save(user);
        addRole(user, roleIds);
        //保存用户到微信
        com.kaishengit.dto.wx.User wxUser = new com.kaishengit.dto.wx.User();
        wxUser.setUserid(user.getId().toString());
        wxUser.setName(user.getUsername());
        wxUser.setMobile(user.getMobile());
        wxUser.setDepartment(Arrays.asList(roleIds));
        wxService.saveUser(wxUser);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll() ;
    }

    @Override
    public void delById(Integer id) {
        userMapper.delUser(id);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    @Transactional
    public void editUser(User user, Integer[] roleIds) {
        roleMapper.delRoleById(user.getId());
        addRole(user, roleIds);

        userMapper.editUser(user);
    }

    private void addRole(User user, Integer[] roleIds) {
        if (roleIds != null) {
            for (Integer roleId : roleIds) {
                Role role = roleMapper.findById(roleId);
                if (role != null) {
                    roleMapper.saveRole(user.getId(), roleId);
                }
            }
        }
    }

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

}
