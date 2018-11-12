package com.arc.tracker;

public class QueryInfo {
    private int selectCount;

    public int getSelectCount() {
        return selectCount;
    }

    public void incrementSelectCount() {
        selectCount++;
    }

    public void clear() {
        selectCount = 0;
    }

}
