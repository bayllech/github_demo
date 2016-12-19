package com.kaishengit.dao;

import com.kaishengit.util.DbHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bayllech on 2016/12/15.
 */
public class LoginLogDao {
    public void saveIp(Integer id, String ip) {
        String sql = "insert into t_login_log (ip,userid) values (?,?)";
        DbHelp.update(sql,ip,id);
    }
}
