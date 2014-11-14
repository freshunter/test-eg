package com.kkk.rest.ws;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kkk.rest.db.DataBean;
import com.kkk.rest.model.TestEntity;

@Service
public class TestEntityWsImpl implements TestEntityWs {

    private static final Logger log = LoggerFactory.getLogger(TestEntityWsImpl.class);

//    @Resource(name = "dataBean")
    @Autowired
    @Qualifier("dataBean")
    DataBean db;

    @Override
    public Collection<TestEntity> get() {
        return db.findAll(TestEntity.class);
    }

    @Override
    public TestEntity get(Long id) {
        log.info("get test id:" + id);
        System.out.println("get test id:" + id);
        return db.getByPK(TestEntity.class, id);
    }

    @Override
    public TestEntity create(TestEntity test) {
        log.info("create :" + test.toString());
        System.out.println("=================================");
        db.insertByCheckUniqueKey(test, "name", test.getName());
        return test;
    }

    @Override
    public TestEntity update(TestEntity test) {
        log.info("update :" + test.toString());
        return db.update(test);
    }

    @Override
    public void delete(Long id) {
        log.info("delete test, id:" + id);
        db.delete(TestEntity.class, id);
    }

}
