package com.kaishengit.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 帅比 on 2016/12/13.
 */
public class log4jTestCase {
    @Test
    public void testlog() {
//        Logger logger = Logger.getLogger(log4jTestCase.class);
        Logger logger = LoggerFactory.getLogger(log4jTestCase.class);

        logger.trace("{}-{} trace message","tom","hello");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
    }
}
