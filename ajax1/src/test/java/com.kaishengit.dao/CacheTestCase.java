package com.kaishengit.dao;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;

/**
 * Created by 帅比 on 2016/12/14.
 */
public class CacheTestCase  {
    @Test
    public void testCache() {
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("user");

        Element element = new Element("user1", "jack");
        ehcache.put(element);

        Element e = ehcache.get("user1");
        System.out.println(e.getObjectValue());
    }
}
