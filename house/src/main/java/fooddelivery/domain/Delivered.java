package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Delivered extends AbstractEvent {

    private Long id;
    private String foodId;
    private String customerId;
    private String options;
    private String address;
    private String status;

    public Delivered(Order aggregate){
        super(aggregate);
    }
    public Delivered(){
        super();
    }
}
