package com.sky.result;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 */

public class PageResult implements Serializable {

    private long total; //总记录数

    private List records; //当前页数据集合

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public PageResult() {
    }

    public PageResult(long total, List records) {
        this.total = total;
        this.records = records;
    }
}
