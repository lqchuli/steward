package com.lingyu.steward.service.base;

import com.lingyu.steward.datacenter.base.JpaSearchableRepository;
import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * @author allan
 * @date 09/11/2017
 */
public abstract class AbstractSearchableCrudService<T, ID extends Serializable, S> implements SearchableCrudService<T, ID, S> {
    protected final JpaSearchableRepository<T, ID> repository;

    protected AbstractSearchableCrudService(JpaSearchableRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findOne(ID id) {
        return null;
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public Page<T> findAll(int pageIndex, int pageSize, S s) {
        // TODO: 09/11/2017 定义一个实体，里面有Specification和Sort
        return null;
    }
}
