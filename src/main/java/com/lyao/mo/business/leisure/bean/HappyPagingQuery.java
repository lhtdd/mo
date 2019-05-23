package com.lyao.mo.business.leisure.bean;

/**
 * @author lyao
 * @date 2019/5/17 15:30
 * @description
 */
public class HappyPagingQuery {
    public int initial;
    public int perPageCount;
    public int lastHappyId;

    public HappyPagingQuery(){
        this.initial = 0;
        this.perPageCount = 0;
        this.lastHappyId = 0;
    }

    public HappyPagingQuery(int initial, int perPageCount, int lastHappyId){
        this.initial = initial;
        this.perPageCount = perPageCount;
        this.lastHappyId = lastHappyId;
    }

    public HappyPagingQuery(int perPageCount, int lastHappyId){
        this.initial = 0;
        this.perPageCount = perPageCount;
        this.lastHappyId = lastHappyId;
    }
    public HappyPagingQuery(int perPageCount){
        this.initial = 0;
        this.perPageCount = perPageCount;
        this.lastHappyId = 0;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public int getPerPageCount() {
        return perPageCount;
    }

    public void setPerPageCount(int perPageCount) {
        this.perPageCount = perPageCount;
    }

    public int getLastHappyId() {
        return lastHappyId;
    }

    public void setLastHappyId(int lastHappyId) {
        this.lastHappyId = lastHappyId;
    }
}
