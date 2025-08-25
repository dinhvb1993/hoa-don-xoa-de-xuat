package com.simple_official_spring_template.socket.socket_test;

import com.simple_official_spring_template.api.socket_test.Greeting;
import com.simple_official_spring_template.api.socket_test.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic2/greetings2")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}