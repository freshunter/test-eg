package com.kkk.rest.ws;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kkk.rest.model.ServiceEntity;

@Service
public class TestServiceWsImpl implements TestServiceWs {

    private static final Logger log = LoggerFactory.getLogger(TestServiceWsImpl.class);

    @Override
    public Collection<ServiceEntity> get(Long serviceID) {
        // TODO Auto-generated method stub
        log.warn("kkkkkkkkkkkkkkkkkkkkkkk");
        System.out.println("=================================");
        return Arrays.asList(new ServiceEntity());
    }

    @Override
    public ServiceEntity create(ServiceEntity service) {
        // TODO Auto-generated method stub
        log.warn("kkkkkkkkkkkkkkkkkkkkkkk");
        return new ServiceEntity();
    }

    @Override
    public ServiceEntity update(ServiceEntity service, Long serviceID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long serviceID) {
        // TODO Auto-generated method stub

    }

}
