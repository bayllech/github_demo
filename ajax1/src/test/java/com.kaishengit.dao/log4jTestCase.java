package com.kaishengit.dao;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;

/**
 * Created by 帅比 on 2016/12/13.
 */
public class log4jTestCase {
    @Test
    public void testlog() {
        Logger logger = Logger.getLogger(log4jTestCase.class);

        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
    }
}
