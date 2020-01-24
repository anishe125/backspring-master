package pack.service.rabbitmq;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pack.entity.OrderDto;

@Service
public class RabbitMQPublisher {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${order.rabbitmq.exchange}")
    private String exchange;

    public void publish(String routingKey, OrderDto orderDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, orderDto);
        System.out.println("Send msg = " + orderDto);
    }
}