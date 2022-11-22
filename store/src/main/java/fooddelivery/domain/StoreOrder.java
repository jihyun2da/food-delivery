package fooddelivery.domain;

import fooddelivery.domain.Cooked;
import fooddelivery.domain.Accepted;
import fooddelivery.domain.Rejected;
import fooddelivery.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="StoreOrder_table")
@Data

public class StoreOrder  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private Long orderId;
    
    
    
    
    
    private String status;

    @PostPersist
    public void onPostPersist(){
    }

    public static StoreOrderRepository repository(){
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(StoreOrderRepository.class);
        return storeOrderRepository;
    }



    public void finishCook(){
        Cooked cooked = new Cooked(this);
        cooked.setStatus("finish");
        cooked.publishAfterCommit();
    }
    public void accept(){
        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();
    }
    public void reject(){
        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();
    }
    public void startCook(){
        Cooked cooked = new Cooked(this);
        cooked.publishAfterCommit();
    }

    public static void addOrder(Paid paid){

        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setOrderId(paid.getOrderId());
        storeOrder.setFoodId(paid.getFoodId());
        storeOrder.setStatus("accept");
        repository().save(storeOrder);

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

        
    }
    public static void noticeCancleOrder(OrderCanceled orderCanceled){

        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

        repository().findByOrderId(orderCanceled.getId()).ifPresent(storeOrder->{
            
            storeOrder.setStatus("주문취소"); // do something
            repository().save(storeOrder);

         });
        
    }


}
