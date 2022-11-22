package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import lombok.Data;
import java.util.*;

@Data
public class Delivered extends AbstractEvent {

    private Long id;
    private String address;
    private Long orderId;
    private String riderId;
}
