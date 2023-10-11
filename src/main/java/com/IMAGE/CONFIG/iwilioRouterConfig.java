//package com.IMAGE.CONFIG;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import com.IMAGE.CONTROLLER.imageController;
//
//@Configuration
//public class iwilioRouterConfig {
//	
//
//	@Autowired
//    private imageController handler;
//
//    @Bean
//    public RouterFunction<ServerResponse> handleSMS() {
//        return RouterFunctions.route()
//                .POST("/router/sendOTP", handler::sendOtp)
//                .POST("/router/validateOTP", handler::validateOTP)
//                .build();
//    }
//
//
//}
