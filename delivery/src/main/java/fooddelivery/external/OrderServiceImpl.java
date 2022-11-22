package fooddelivery.external;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    /**
     * Fallback
     */
    public Order getOrder(Long id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus("결제폭주");
        return order;
    }
}

