package fooddelivery.domain;

import fooddelivery.domain.Paid;
import fooddelivery.HouseApplication;
import javax.persistence.*;

import org.springframework.beans.BeanUtils;

import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Payment_table")
@Data

public class Payment  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String foodId;

    @PostPersist
    public void onPostPersist(){


        Paid paid = new Paid(this);
        paid.publishAfterCommit();

    }

    @PrePersist
    public void onPrePersist(){
        // Paid paid = new Paid();
        // BeanUtils.copyProperties(this, paid);
        // paid.publish();
    }

    public static PaymentRepository repository(){
        PaymentRepository paymentRepository = HouseApplication.applicationContext.getBean(PaymentRepository.class);
        return paymentRepository;
    }




    public static void cancelPayment(Rejected rejected){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(rejected.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        repository().findById(rejected.getOrderId()).ifPresent(payment->{
            
            repository().delete(payment);

         });

        
    }
    public static void cancelPayment(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

        repository().findById(orderCanceled.getId()).ifPresent(payment->{
            
            repository().delete(payment);

         });

        
    }


}
