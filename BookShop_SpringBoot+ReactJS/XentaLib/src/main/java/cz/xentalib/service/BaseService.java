package cz.xentalib.service;

import cz.xentalib.dao.BaseDao;


import java.util.List;


public abstract class BaseService<E> {

    protected abstract BaseDao<E> getPrimaryDao();


    public E find(Integer id) {
        return getPrimaryDao().find(id);
    }

    public List<E> findAll() {
        return getPrimaryDao().findAll();
    }


    public void persist(E entity) {
        getPrimaryDao().persist(entity);
    }


    public void remove(E entity) {
        getPrimaryDao().remove(entity);
    }


    public void update(E entity) {
        getPrimaryDao().update(entity);
    }
}
