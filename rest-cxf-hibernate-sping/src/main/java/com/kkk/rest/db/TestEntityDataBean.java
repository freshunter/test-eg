package com.kkk.rest.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kkk.rest.model.TestEntity;

@Repository
public class TestEntityDataBean extends DataBean {

    private static Logger log = LoggerFactory.getLogger(TestEntityDataBean.class);

    @Transactional
    public TestEntity persistEntity(TestEntity test) {
        return this.insertByCheckUniqueKey(test, "name", test.getName());
    }

}
