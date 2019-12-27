package priv.linyu.bill.core.entity;

import java.io.Serializable;

/**
 * @className: PageHepler
 * @author: q-linyu
 * @description: 分页辅助类
 * @date: 2019/12/22 14:04
 * @version: 1.0
 **/
public class PageHepler implements Serializable {

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 每页显示的条数
     */
    private Integer limit;


    public PageHepler() { }

    public PageHepler(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

