package com.ssm.mq;


import com.ssm.entity.Mood;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者jmsTemplate
 *
 * @author Ay
 * @date 2017/11/30
 */
@Component
public class MoodProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final Mood mood) {
        System.err.println("生产者--->>>用户id：" + mood.getUserId() + " 给说说id：" + mood.getId() + " 点赞");
        //mood实体需要实现Serializable序列化接口
        jmsTemplate.convertAndSend(destination, mood);
    }
}
