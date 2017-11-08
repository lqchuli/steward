package com.lingyu.steward.service.base;

import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Created by allan on 09/11/2017.
 */
public interface SearchableCrudService<T, ID extends Serializable, S> {
    /**
     * 通过id得到一个实体
     *
     * @param id
     * @return
     */
    T findOne(ID id);

    /**
     * 保存一个实例
     *
     * @param t
     * @return
     */
    T save(T t);

    /**
     * 删除一个实例
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 根据条件分组查找
     *
     * @param pageIndex 页码，索引从1开始
     * @param pageSize  每页数量
     * @param s         查询条件
     * @return
     */
    Page<T> findAll(int pageIndex, int pageSize, S s);
}
