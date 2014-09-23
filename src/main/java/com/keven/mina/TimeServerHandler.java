package com.keven.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TimeServerHandler extends IoHandlerAdapter {
    private static final Logger logger = LoggerFactory
            .getLogger(TimeServerHandler.class);

    @Override
    public void sessionOpened(IoSession session) throws Exception {
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
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("Sent " + message);
    }

    @Override
    public void sessionCreated(IoSession session) {
        // ��ʾ�ͻ��˵�ip�Ͷ˿�
        System.out.println(session.getRemoteAddress().toString());
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        logger.info("Received ---------------------" + message);
        MyRequestObject myReqOjb = (MyRequestObject) message;
        MyResponseObject myResObj = new MyResponseObject(myReqOjb.getName(),
                myReqOjb.getValue()+ " from server");
        session.write(myResObj);

//        String str = message.toString();
//        if (str.trim().equalsIgnoreCase("quit")) {
//            session.close(true);// �����Ự
//            return;
//        }
//        System.out.println("�ͻ���:--" + str);
//        Date date = new Date();
//        session.write("����ˣ�" + date.toString() + "�յ�" + str + "��Ϣ��");// ���ص�ǰʱ����ַ���
        System.out.println("Message written...");
    }
}