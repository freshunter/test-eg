package com.keven.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TimeClientHandler extends IoHandlerAdapter {
    private static final Logger logger = LoggerFactory
            .getLogger(TimeClientHandler.class);

    public TimeClientHandler() {
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        MyRequestObject myObj = new MyRequestObject("my name","my value");
        session.write(myObj);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        logger.error(cause.getMessage(), cause);
        session.close(true);
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        MyResponseObject myResObj = (MyResponseObject) message;
        logger.info("Received " + myResObj);
        System.out.println(myResObj.getValue()+"222222");// 显示接收到的消息
        session.close(true);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("Sent " + message);
    }
}