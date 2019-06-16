package com.demo.listener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列配置
 *
 * @author yanlin
 * @version v1.3
 * @date 2019-06-16 8:39 PM
 * @since v8.0
 **/
@Configuration
public class DelayListener {
    //死信队列
    private static final String DELAY_QUEUE = "delay_queue";
    //正常队列
    private static final String NORMAL_QUEUE = "normal_queue";
    //死信交换器
    private static final String DELAY_EXCHANGE = "delay_exchange";
    //死信路由键
    private static final String DELAY_KEY = "delay_key";

    /**
     * 正常队列
     *
     * @return
     */
    @Bean
    public Queue normalQueue() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000);//设置10s过期时间
        //x-dead-letter-exchange参数是设置该队列的死信交换器（DLX）
        map.put("x-dead-letter-exchange", DELAY_EXCHANGE);
        //x-dead-letter-routing-key参数是给这个DLX指定路由键
        map.put("x-dead-letter-routing-key", DELAY_KEY);
        return new Queue(NORMAL_QUEUE, true, false, false, map);
    }

    /**
     * 正常交换器
     *
     * @return
     */
    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange("normal_exchange", true, false);
    }

    /**
     * 正常队列绑定
     *
     * @param normalQueue
     * @param normalExchange
     * @return
     */
    @Bean
    public Binding normalBinding(Queue normalQueue, DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("normal_key");
    }

    /**
     * 死信队列
     *
     * @return
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE);
    }

    /**
     * 死信交换器
     *
     * @return
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE, true, false);
    }


    /**
     * 死信队列绑定交换器
     *
     * @param delayQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding delayBinding(Queue delayQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(DELAY_KEY);
    }


}
