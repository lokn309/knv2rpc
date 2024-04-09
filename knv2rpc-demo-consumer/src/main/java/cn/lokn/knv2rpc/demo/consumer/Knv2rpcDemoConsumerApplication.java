package cn.lokn.knv2rpc.demo.consumer;

import cn.lokn.knv2rpc.core.annotation.KNConsumer;
import cn.lokn.knv2rpc.demo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Knv2rpcDemoConsumerApplication {

    @KNConsumer
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Knv2rpcDemoConsumerApplication.class, args);
    }

    public ApplicationRunner consumerRun() {
        return x -> {
            System.out.println(userService.findById(1));
        };
    }

}
