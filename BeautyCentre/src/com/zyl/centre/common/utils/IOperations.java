package com.zyl.centre.common.utils;

import java.io.Serializable;
import java.util.List;

/*
 * ͨ�õĲ����ӿ�
 */
public interface IOperations<T extends Serializable> {
     
        T findOne(final long id);

        List<T> findAll();

        void create(final T entity);

        T update(final T entity);

        void delete(final T entity);

        void deleteById(final long entityId);

}
