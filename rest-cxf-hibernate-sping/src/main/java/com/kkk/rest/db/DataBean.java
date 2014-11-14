package com.kkk.rest.db;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kkk.rest.exception.KErrorCode;
import com.kkk.rest.exception.KSystemException;
import com.kkk.rest.model.KKKEntity;

@Repository("dataBean")
public class DataBean {
    private static Logger log = LoggerFactory.getLogger(DataBean.class);

    @PersistenceContext
    protected EntityManager em;

    // private int pageSizeSubscriber = 10000;

    @PostConstruct
    public void init() {

    }

    /** Insert the entity to save. */
    @Transactional
    public <T extends KKKEntity> void insert(T e) {
        em.persist(e);
    }

    @Transactional
    public <T extends KKKEntity> T insertByCheckUniqueKey(T e, String key, String value) {
        @SuppressWarnings("unchecked")
        T tmpE = (T) this.findFirstBy(e.getClass(), key, value);
        if (tmpE != null) {
            log.warn("The record has been already existed: " + tmpE.toString());
            throw new KSystemException(KErrorCode.RECORD_ALREADY_EXIST);
        }
        insert(e);
        return e;
    }

    /** Update the entity. */
    @Transactional
    public <T extends KKKEntity> T update(T e) {
        e.setUpdateTime(new Date());
        return em.merge(e);
    }

    @Transactional
    protected <T extends KKKEntity> int update(Class<T> clazz, String key, String oldValue, String newValue) {
        int number = em.createQuery(
                "UPDATE " + clazz.getSimpleName() + "s SET s." + key + "='" + newValue + "' WHERE s." + key + "='"
                        + oldValue + "'", clazz).executeUpdate();
        System.out.println("Update " + number + "records in '" + key + "' - " + oldValue + " >>> " + newValue);
        return number;
    }

    /** Delete the entity. */
    @Transactional
    public <T extends KKKEntity> void delete(T e) {
        em.remove(e);
    }

    /** Delete the entity. */
    @Transactional
    public <T extends KKKEntity> void delete(Class<T> entityClass, Object primaryKey) {
        delete(em.find(entityClass, primaryKey));
    }

    @Transactional(readOnly = true)
    public <T extends KKKEntity> T getByPK(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public <T extends KKKEntity> List<T> findAll(Class<T> clazz) {
        List<T> results = em.createQuery("SELECT s FROM " + clazz.getSimpleName() + " s", clazz).getResultList();

        return results;
    }

    protected <T extends KKKEntity> List<T> findAllBy(Class<T> clazz, String key, String value) {
        if (value == null)
            value = "";
        List<T> results = em.createQuery(
                "SELECT s FROM " + clazz.getSimpleName() + " s WHERE s." + key + "='" + value + "'", clazz)
                .getResultList();

        return results;
    }

    protected <T extends KKKEntity> T findFirstBy(Class<T> clazz, String key, String value) {
        if (value == null)
            value = "";
        List<T> results = em.createQuery(
                "SELECT s FROM " + clazz.getSimpleName() + " s WHERE s." + key + "='" + value + "'", clazz)
                .getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }

        return null;
    }
}
