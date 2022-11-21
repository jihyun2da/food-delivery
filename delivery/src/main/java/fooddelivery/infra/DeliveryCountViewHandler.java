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
public class DeliveryCountViewHandler {


    @Autowired
    private DeliveryCountRepository deliveryCountRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_CREATE_1 (@Payload DeliveryStarted deliveryStarted) {
        try {

            if (!deliveryStarted.validate()) return;

            // view 객체 생성
            DeliveryCount deliveryCount = new DeliveryCount();
            // view 객체에 이벤트의 Value 를 set 함
            deliveryCount.setId(deliveryStarted.getId());
            deliveryCount.setOrderId(String.valueOf(deliveryStarted.getOrderId()));
            deliveryCount.setRiderId(deliveryStarted.getRiderId());
            // view 레파지 토리에 save
            deliveryCountRepository.save(deliveryCount);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_1(@Payload DeliveryStarted deliveryStarted) {
        try {
            if (!deliveryStarted.validate()) return;
                // view 객체 조회

                List<DeliveryCount> deliveryCountList = deliveryCountRepository.findByRiderId(deliveryStarted.getRiderId());
                for(DeliveryCount deliveryCount : deliveryCountList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryCount.setCount(deliveryCount.getCount() + 1);
                // view 레파지 토리에 save
                deliveryCountRepository.save(deliveryCount);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

