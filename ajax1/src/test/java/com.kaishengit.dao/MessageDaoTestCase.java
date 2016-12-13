package com.kaishengit.dao;

import static org.junit.Assert.*;
import com.kaishengit.entity.Message;
import org.junit.Test;

import java.util.List;

/**
 * Created by 帅比 on 2016/12/13.
 */
public class MessageDaoTestCase {
    @Test
    public void testFindByMaxId() {
        MessageDao messageDao = new MessageDao();
        List<Message> messagesList = messageDao.findByMaxId(4);
        assertEquals(1,messagesList.size());
    }
}

