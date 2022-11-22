package fooddelivery.external;

import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {


    /**
     * Fallback
     */
    public Payment getPayment(Long id) {
        Payment payment = new Payment();
        payment.setOrderId(id);
        payment.setFoodId("준비중");
        return payment;
    }

    @Override
    public void pay(Payment payment) {
        
        
    }
}

