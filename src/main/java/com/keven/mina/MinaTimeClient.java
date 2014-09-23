package com.keven.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

class MinaTimeClient {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // �����ͻ���������.
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
//        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // �����ı����������
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory())); // ����java����
        connector.setConnectTimeoutMillis(3000);
        connector.setHandler(new TimeClientHandler());// �����¼�������
        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1",
                9123));// ��������
        cf.awaitUninterruptibly();// �ȴ����Ӵ������
//        cf.getSession().write("hello");// ������Ϣ
//        boolean fag = true;
//        while (fag) {
//            Scanner sc = new Scanner(System.in);
//            String str = sc.nextLine();
//            System.out.println(str);
//            cf.getSession().write(str);// ������Ϣ
//            if (str.equals("quit"))
//                fag = false;
//        }
        cf.getSession().getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
        connector.dispose();
    }
}