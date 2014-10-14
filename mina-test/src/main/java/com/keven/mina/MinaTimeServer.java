package com.keven.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

class MinaTimeServer {
    private static final int PORT = 9123;// ��������˿�

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
//        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));// ָ�����������
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));//����java����
        
        acceptor.setHandler(new TimeServerHandler());// ָ��ҵ���߼�������

        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));// ���ö˿ں�
        acceptor.bind();// ��������
    }
}