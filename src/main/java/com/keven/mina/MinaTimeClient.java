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
        // 创建客户端连接器.
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter());
//        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // 设置文本编码过滤器
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory())); // 传输java对象
        connector.setConnectTimeoutMillis(3000);
        connector.setHandler(new TimeClientHandler());// 设置事件处理器
        ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1",
                9123));// 建立连接
        cf.awaitUninterruptibly();// 等待连接创建完成
//        cf.getSession().write("hello");// 发送消息
//        boolean fag = true;
//        while (fag) {
//            Scanner sc = new Scanner(System.in);
//            String str = sc.nextLine();
//            System.out.println(str);
//            cf.getSession().write(str);// 发送消息
//            if (str.equals("quit"))
//                fag = false;
//        }
        cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
        connector.dispose();
    }
}