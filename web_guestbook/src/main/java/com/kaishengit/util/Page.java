package com.kaishengit.util;

import java.util.List;

/**
 * 对分页结果的封装
 * Created by 帅比 on 2016/12/1.
 */
public class Page<T> {
    //总页数
    private int totalPages;
    //每页显示条数
    private int pageSize = 3;
    //当前页数
    private int pageNo;
    //起始行数
    private int start;
    //当前页数据
    private List<T> items;
    //数据总条数
    private int counts;

    public Page(int pageNo,int counts) {
        this.counts = counts;
        if (pageNo < 1) {
            pageNo = 1;
        }
        //获取总页数
        totalPages = counts / pageSize;
        if (counts % pageSize != 0) {
            totalPages++;
        }

        if (totalPages == 0) {
            totalPages = 1;
            if (pageNo > totalPages) {
                pageNo = totalPages;
            }
        }
        this.pageNo = pageNo;

        //计算起始页行数
        start = (pageNo - 1) * pageSize;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
