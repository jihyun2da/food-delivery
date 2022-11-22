package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class Delivered extends AbstractEvent {

    private Long id;
    private String address;
    private Long orderId;
    private String riderId;
}


