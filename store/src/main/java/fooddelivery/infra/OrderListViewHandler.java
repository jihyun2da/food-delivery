package fooddelivery.infra;

import fooddelivery.domain.*;
import fooddelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderListViewHandler {


    @Autowired
    private OrderListRepository orderListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            OrderList orderList = new OrderList();
            // view 객체에 이벤트의 Value 를 set 함
            orderList.setId(orderPlaced.getId());
            orderList.setFoodId(orderPlaced.getFoodId());
            // view 레파지 토리에 save
            orderListRepository.save(orderList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_UPDATE_1(@Payload OrderPlaced orderPlaced) {
        try {
            if (!orderPlaced.validate()) return;
                // view 객체 조회

                List<OrderList> orderListList = orderListRepository.findByFoodId(orderPlaced.getFoodId());
                for(OrderList orderList : orderListList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderList.setCount(orderList.getCount() + 1);
                // view 레파지 토리에 save
                orderListRepository.save(orderList);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

