
package com.github.xphsc.page;

import java.io.Serializable;
import java.util.List;


/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public class Page<T> implements Serializable {

/**当前页*/

public int currentPage;

/**每页显示记录数*/

public int pageSize;

/**总记录数*/

public long recordCount = 1L;

/**记录集合*/

public List<T> recordList;

/**总页数*/

public int pageCount;

/**偏移数*/

public int offset;

/**上一页*/

public int prePage;

/**下一页*/

public int nextPage;

/**是否有上一页*/

public boolean hasPrePage;

/**是否有下一页*/

public boolean hasNextPage;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage) {
        this.hasPrePage = hasPrePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

}

