package fooddelivery.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="DeliveryCount_table")
@Data
public class DeliveryCount {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String orderId;
        private String count;
        private String riderId;


}