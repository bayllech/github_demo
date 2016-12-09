package com.kaishengit.service;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;

import java.util.List;

/**
 * Created by 帅比 on 2016/12/9.
 */
public class MessageService {
    MessageDao messageDao = new MessageDao();

    public List<Message> findByMaxId(int id) {
        return messageDao.findByMaxId(id);
    }
}
