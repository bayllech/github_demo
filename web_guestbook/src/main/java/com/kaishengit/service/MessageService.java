package com.kaishengit.service;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Message;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;

import java.util.List;

public class MessageService {

    public void addBook(Message message) {
        MessageDao messageDao = new MessageDao();
        messageDao.addBook(message);
    }

    public void delBook(String id) {
        MessageDao messageDao = new MessageDao();
        messageDao.delBook(id);
    }

    public Message findById(String id) {
        MessageDao messageDao = new MessageDao();
        return  messageDao.findById(id);

    }

    public void updateBook(Message book) {
        MessageDao messageDao = new MessageDao();
        messageDao.updateBook(book);
    }

    public Page<Message> findByPageNo(int pageNo,Admin admin) {
        MessageDao messageDao = new MessageDao();
        //获取总条数
        int counts = messageDao.count(admin).intValue();
        //获取page封装对象
        Page<Message> bookPage = new Page<>(pageNo, counts);
        //获取当前页内容
        List<Message> bookList = messageDao.findByPageNo(admin,bookPage.getStart(),bookPage.getPageSize());
        bookPage.setItems(bookList);

        return bookPage;
    }

    public Page<Message> findAll(int pageNo) {
        MessageDao messageDao = new MessageDao();
        //获取总条数
        int counts = messageDao.count().intValue();
        //获取page封装对象
        Page<Message> bookPage = new Page<>(pageNo, counts);
        //获取当前页内容
        List<Message> bookList = messageDao.findAll(bookPage.getStart(),bookPage.getPageSize());
        bookPage.setItems(bookList);

        return bookPage;
    }
}
