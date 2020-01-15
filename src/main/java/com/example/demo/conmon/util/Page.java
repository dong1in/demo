package com.example.demo.conmon.util;

import java.util.List;

public class Page {
    //开始位置
    public Integer start;
    //每一页数量
    public Integer offset;
    //总记录 数
    public Integer rowCount;
    //当前页码
    public Integer page;
    //最大页码
    public Integer maxPage;
    //数据
    public List data;

    //public Page() {}
    public Page(Integer limit, Integer page, Integer rowCount) {
        this.rowCount = rowCount;
        maxPage = (rowCount - 1) / limit + 1;
        if (page < 1) {
            page = 1;
        }
        if (page > maxPage) {
            page = maxPage;
        }
        start = (page - 1) * limit;
    }
}
