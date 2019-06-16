package com.demo.listener;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试备份交换器
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-06-16 2:38 PM
 * @since v8.0
 **/
@Configuration
public class AEListener {
    //一个正常对队列
    private static final String queue = "good-queue";
    //备份对队列
    private static final String AEQueue = "AE-queue";


    @Bean
    public Queue goodQueue() {
        return new Queue(queue);
    }

    @Bean
    public Queue AEQueue() {
        return new Queue(AEQueue);
    }

    @Bean
    public DirectExchange goodExchange() {
        Map<String, Object> arguments = new HashMap<>();
        //如果设置此参数，TestAE交换器绑定的路由没有匹配到就会发送到exchange-unroute交换器所绑定的队列进行消费
        arguments.put("alternate-exchange", "exchange-unroute");
        return new DirectExchange("TestAE", true, false, arguments);
    }

    @Bean
    public FanoutExchange unRouteExchange() {
        // 此处的交换器的名字要和 exchange() 方法中 alternate-exchange 参数的值一致
        return new FanoutExchange("exchange-unroute");
    }

    //被路由到的 交换器绑定队列
    @Bean
    Binding bindingExchangeGood(Queue goodQueue, DirectExchange goodExchange) {
        return BindingBuilder.bind(goodQueue()).to(goodExchange()).with("abc");
    }

    //未被路由到的 交换器绑定队列
    @Bean
    Binding bindingExchangeAE(Queue AEQueue, FanoutExchange unRouteExchange) {
        return BindingBuilder.bind(AEQueue).to(unRouteExchange);
    }
}
