package com.github.xphsc.collect.sort;

/**
 * Created by ${huipei.x} on 2017-5-31.
 */
public abstract class SortField<F, T extends Comparable<?>>  {

    /** 升序逆序标记 */
    private boolean isAsc = true;

    /** null值在前面还是在后面，默认null值排在最后*/
    private boolean isNullFirst = false;

    public SortField() {
    }

    /**
     * 指定排序顺序
     * @param sortOrder 当值为ASC，升序排列；当DESC时，逆序排列
     */
    public SortField(SortOrder sortOrder) {
        this.isAsc = sortOrder == SortOrder.ASC;
    }

    /**
     * 指定排序顺序和null值在最前还是最后
     * @param sortOrder
     * @param isNullFirst
     */
    public SortField(SortOrder sortOrder, boolean isNullFirst) {
        this.isAsc = sortOrder == SortOrder.ASC;
        this.isNullFirst = isNullFirst;
    }

    /**
     * 输入F类型，返回T类型
     * @param input 非null
     * @return
     */
    public abstract T apply(F input);

    public boolean isAsc() {
        return isAsc;
    }

    public boolean isNullFirst() {
        return isNullFirst;
    }

}
