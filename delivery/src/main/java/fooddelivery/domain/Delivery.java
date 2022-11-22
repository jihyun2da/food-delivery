package fooddelivery.domain;

import fooddelivery.domain.DeliveryStarted;
import fooddelivery.external.Order;
import fooddelivery.external.OrderService;
import fooddelivery.DeliveryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Delivery_table")
@Data

public class Delivery  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String riderId;

    @PostPersist
    public void onPostPersist(){


        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
    }

    public static DeliveryRepository repository(){
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(DeliveryRepository.class);
        return deliveryRepository;
    }



    public void accept(){
    }

    public static void addDeliveryList(Cooked cooked){

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */
        
        Order order = DeliveryApplication.applicationContext.getBean(fooddelivery.external.OrderService.class)
            .getOrder(cooked.getOrderId());
            System.out.println("\n\n##### 배달추가 " + order + "\n\n");
        Delivery delivery = new Delivery();
        delivery.setOrderId(cooked.getOrderId());
        delivery.setRiderId(String.valueOf(cooked.getOrderId()));
        delivery.setAddress(order.getAddress());
        repository().save(delivery);

        /** Example 2:  finding and process
        
        repository().findById(cooked.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

        
    }


}
