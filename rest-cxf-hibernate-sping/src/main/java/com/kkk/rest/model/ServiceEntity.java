package com.kkk.rest.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class ServiceEntity extends KKKEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceentity_seq")
    @SequenceGenerator(name = "serviceentity_seq", sequenceName = "serviceentity_seq")
    private Long id;
	
	List<AbstractTestEntity> testSet;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
