package com.kkk.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = TestEntity.QUERY_BY_NAME, query = "from TestEntity r where r."
        + TestEntity.PARAM_NAME + "= :" + TestEntity.PARAM_NAME) })
@Entity
@XmlRootElement(name = "TestEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestEntity extends AbstractTestEntity {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_BY_NAME = "findByName";
    public static final String PARAM_NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "testentity_seq")
    @SequenceGenerator(name = "testentity_seq", sequenceName = "testentity_seq")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    String name;
    String type;

}
