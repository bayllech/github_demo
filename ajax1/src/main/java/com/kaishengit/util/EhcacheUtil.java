package com.kaishengit.util;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.io.Serializable;

/**
 * Created by 帅比 on 2016/12/14.
 */
public class EhcacheUtil {
    private static CacheManager cacheManager = new CacheManager();

    public Ehcache getEhcache(String cacheName) {
        return cacheManager.getEhcache(cacheName);
    }

    public void set(String cacheName, Serializable key, Serializable val) {
        Element element = new Element(key,val);
        getEhcache(cacheName).put(element);
    }

    public void set(Ehcache ehcache, Serializable key, Serializable val) {
        Element element = new Element(key,val);
        ehcache.put(element);
    }

    public void set(String cacheName, Object key, Object val) {
        Element element = new Element(key,val);
        getEhcache(cacheName).put(element);
    }

    public Object get(String cacheName,Serializable key) {
        Element element = getEhcache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }
    public Object get(Ehcache ehcache,Serializable key) {
        Element element = ehcache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public void removeAll(String cacheName) {
        getEhcache(cacheName).removeAll();
    }

    public void removeAll(Ehcache ehcache) {
        ehcache.removeAll();
    }

    public void remove(String cacheName,Serializable key) {
        getEhcache(cacheName).remove(key);
    }

    public void remove(Ehcache ehcache,Serializable key) {
        ehcache.remove(key);
    }

}
