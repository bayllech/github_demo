package com.kaishengit.service;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;

import java.util.List;

public class MessageService {
    MessageDao messageDao = new MessageDao();

    public List<Message> findByMaxId(int id) {
        return messageDao.findByMaxId(id);
    }
}
