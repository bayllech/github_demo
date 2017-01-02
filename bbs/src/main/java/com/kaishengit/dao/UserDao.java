package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.Page;
import com.kaishengit.vo.UserVo;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserDao {
    public User findUserByName(String username) {
        String sql = "select * from t_user where username = ?";
        return DbHelp.query(sql, new BeanHandler<>(User.class), username);
    }

    public void saveUser(User user) {
        String sql = "insert into t_user(username,password,email,phone,state,avatar) values (?,?,?,?,?,?)";
        DbHelp.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getState(), user.getAvatar());
    }

    public User findUserByEmail(String email) {
        System.out.println(email);
        String sql = "select * from t_user where email = ?";
        return DbHelp.query(sql, new BeanHandler<>(User.class), email);
    }

    public void update(User user) {
        String sql = "update t_user set password=?,email=?,phone=?,state=?,avatar=? where id=?";
        DbHelp.update(sql, user.getPassword(), user.getEmail(), user.getPhone(), user.getState(), user.getAvatar(),user.getId());
    }

    /**
     * 通过id查找用户
     * @param userid
     * @return
     */
    public User findUserById(Integer userid) {
        String sql = "select * from t_user where id = ?";
        return DbHelp.query(sql, new BeanHandler<User>(User.class), userid);
    }

    public Integer count() {
        String sql = "select count(*) from t_user where state != 0 order by id";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public List<User> findAllUsers(Page<UserVo> page) {
        String sql = "select * from t_user where state != 0 order by createtime limit ?,?";
        return DbHelp.query(sql,new BeanListHandler<User>(User.class),page.getStart(),page.getPageSize());
    }

    public UserVo findUserVo(Integer id) {
        String sql = "select tll.logintime lastLoginTime,tll.ip loginIP,tu.id userId,tu.username username,tu.createtime ,tu.state userState from t_login_log tll ,t_user tu where userid = ? order by logintime desc limit 0,1";
        return DbHelp.query(sql,new BeanHandler<UserVo>(UserVo.class),id);
    }

    public List<UserVo> findAll(Page<UserVo> page) {
        String sql = "SELECT logintime lastLoginTime,ip loginIP,userid userId,username username,createtime ,state userState FROM (SELECT tll.logintime,tll.ip,tll.userid,tu.* FROM t_user tu,t_login_log tll WHERE tu.id = tll.userid AND tu.state !=0 ORDER BY logintime DESC ) AS res GROUP BY (userid) LIMIT ?,?;";
        return DbHelp.query(sql,new BeanListHandler<UserVo>(UserVo.class),page.getStart(),page.getPageSize());
    }
}
