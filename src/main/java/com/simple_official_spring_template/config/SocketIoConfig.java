//package com.simple_official_spring_template.config;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
//
//@Configuration
//public class SocketIoConfig {
//    @Value("${rt-server.host}")
//    private String host;
//
//    @Value("${rt-server.port}")
//    private Integer port;
//
////    @Value("${wss.origin.host}")
////    private String originHost;
//
//
//    @Bean
//    public SocketIOServer socketIOServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname(host);
//        config.setPort(port);
//        config.setOrigin("http://localhost");
//        final SocketIOServer server = new SocketIOServer(config);
//        return server;
//    }
//
//    @Bean
//    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
//        return new SpringAnnotationScanner(socketServer);
//    }
//}