package cn.lokn.knv2rpc.demo.provider;

import cn.lokn.knv2rpc.core.api.RpcRequest;
import cn.lokn.knv2rpc.core.api.RpcResponse;
import cn.lokn.knv2rpc.core.provider.ProviderBootstrap;
import cn.lokn.knv2rpc.core.provider.ProviderConfig;
import cn.lokn.knv2rpc.demo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Import({ProviderConfig.class})
public class Knv2rpcDemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Knv2rpcDemoProviderApplication.class, args);
    }

    @Autowired
    ProviderBootstrap providerBootstrap;

    @RequestMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request) {
        return providerBootstrap.invoke(request);
    }

    @Bean
    ApplicationRunner providerRun() {
        return x -> {
            final RpcRequest request = new RpcRequest();
            request.setService("cn.lokn.knv2rpc.demo.api.UserService");
            request.setMethod("findById");
            request.setArgs(new Object[]{100});

            final RpcResponse result = providerBootstrap.invoke(request);
            System.out.println(result);
        };
    }

}
