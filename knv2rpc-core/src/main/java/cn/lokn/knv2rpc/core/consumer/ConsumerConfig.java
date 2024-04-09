package cn.lokn.knv2rpc.core.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: lokn
 * @date: 2024/04/09 23:59
 */
@Configuration
public class ConsumerConfig {

    /**
     * 创建并返回一个ConsumerBootstrap实例。
     * 这个方法没有参数。
     *
     * @return ConsumerBootstrap 返回一个ConsumerBootstrap实例，用于消费者启动配置。
     */
    @Bean
    public ConsumerBootstrap creteConsumerBootstrap() {
        return new ConsumerBootstrap();
    }

    /**
     * 创建一个ApplicationRunner Bean，用于在Spring应用程序启动时执行特定的逻辑。
     *
     * @param consumerBootstrap 一个被自动装配的ConsumerBootstrap实例，用于启动消费者逻辑。
     * @return 返回一个ApplicationRunner实例，该实例在Spring应用程序启动完成后执行consumerBootstrap的start方法。
     */
    @Bean
    public ApplicationRunner consumerRun(@Autowired ConsumerBootstrap consumerBootstrap) {
        // 创建并返回一个ApplicationRunner实例，其run方法仅启动consumerBootstrap
        return x -> {
            consumerBootstrap.start(); // 启动消费者
        };
    }


}
