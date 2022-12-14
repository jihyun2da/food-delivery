package fooddelivery.domain;

import fooddelivery.domain.OrderPlaced;
import fooddelivery.domain.Delivered;
import fooddelivery.domain.OrderCanceled;
import fooddelivery.HouseApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Data

public class Order  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String customerId;
    
    
    
    
    
    private String options;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){

        fooddelivery.external.Payment payment = new fooddelivery.external.Payment();
        payment.setOrderId(id);
        payment.setFoodId(foodId);
        // mappings goes here
        HouseApplication.applicationContext.getBean(fooddelivery.external.PaymentService.class)
            .pay(payment);


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

    }
    @PostUpdate
    public void onPostUpdate(){

        if (status.equals("finish")) {

            Delivered delivered = new Delivered(this);
            delivered.publishAfterCommit();
        }

    }
    @PostRemove
    public void onPostRemove(){
    }
    @PrePersist
    public void onPrePersist(){
    }
    @PreRemove
    public void onPreRemove(){

        if (status.equals("cooking")) {
            System.out.println("\n\n##### 조리 시작으로 취소 불가능 \n\n");
            return;
        }
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();

    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = HouseApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }



    public void deliveryconfirm(Order order){
        order.setStatus("finish");
    }

    public static void cancel(Rejected rejected){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(rejected.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        repository().findById(rejected.getOrderId()).ifPresent(order->{
            
            order.setStatus("취소"); // do something
            repository().save(order);


         });

        
    }
    public static void updateStatus(Accepted accepted){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(accepted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        repository().findById(accepted.getOrderId()).ifPresent(order->{
            
            order.setStatus("주문접수"); // do something
            repository().save(order);


         });

        
    }
    public static void updateStatus(Rejected rejected){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(rejected.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        repository().findById(rejected.getOrderId()).ifPresent(order->{
            
            order.setStatus("접수취소"); // do something
            repository().save(order);

         });

        
    }
    public static void updateStatus(Cooked cooked){

        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        
        repository().findById(cooked.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        repository().findById(cooked.getOrderId()).ifPresent(order->{
            
            order.setStatus("요리중"); // do something
            repository().save(order);


         });

    }


}
