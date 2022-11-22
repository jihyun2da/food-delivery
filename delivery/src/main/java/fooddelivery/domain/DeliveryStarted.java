package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private String address;
    private Long orderId;
    private String riderId;

    public DeliveryStarted(Delivery aggregate){
        super(aggregate);
    }
    public DeliveryStarted(){
        super();
    }
}
