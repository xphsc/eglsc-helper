package com.github.xphsc.page;

import com.github.xphsc.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by ${huipei.x} on 2017-6-18.
 */
public class PageImpl<T> extends Page<T>{

    public PageImpl(){}

    public PageImpl(int currentPage, int pageSize, long recordCount, List<T> recordList) {
        this.currentPage = currentPage;
        if(currentPage < 1) {
            this.currentPage = 1;
        }

        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.recordList = recordList;
        //上一页等于当前页减一
        this.prePage = this.currentPage - 1;
        if(this.prePage < 1) {
            this.hasPrePage = false;//没有上一页
            this.prePage = 1;
        }else {
            this.hasPrePage = true;//有上一页
        }

        //计算总页数
        this.pageCount = (int)Math.ceil(recordCount / (double)pageSize);
        if(this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }

        //下一页等于当前页加一
        this.nextPage = this.currentPage + 1;
        if(this.nextPage > this.pageCount) {
            this.hasNextPage = false;//没有下一页
            this.nextPage = this.pageCount;
        }else {
            this.hasNextPage = true;//有下一页
        }

        //偏移量
        this.offset = (this.currentPage - 1)*pageSize;
    }

    public PageImpl(int currentPage,int pageSize,List<T> recordList) {
        if (Lists.isEmpty(recordList)) {
            throw new IllegalArgumentException("recordList must be not empty!");
        }
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.recordCount = recordList.size();
        this.pageCount = (int)Math.ceil(recordCount / (double)pageSize);
        if(this.currentPage > this.pageCount) {
            this.currentPage = this.pageCount;
        }
        this.prePage = currentPage-1>1? currentPage-1:1;
        this.nextPage = currentPage>=pageCount? pageCount: currentPage + 1;
        this.recordList = getPageList(recordList);
        this.offset = (this.currentPage - 1)*pageSize;

    }

    public List<T> getPageList(List<T> recordList) {
        int fromIndex = (currentPage - 1) * pageSize;
        if (fromIndex >= recordList.size()) {
            return Collections.emptyList();//空数组
        }
        if(fromIndex<0){
            return Collections.emptyList();//空数组
        }
        int toIndex = currentPage * pageSize;
        if (toIndex >= recordList.size()) {
            toIndex = recordList.size();
        }
        return recordList.subList(fromIndex, toIndex);
    }


}
