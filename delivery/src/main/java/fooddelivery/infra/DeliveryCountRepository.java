package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="deliveryCounts", path="deliveryCounts")
public interface DeliveryCountRepository extends PagingAndSortingRepository<DeliveryCount, Long> {

    List<DeliveryCount> findByRiderId(String riderId);


    
}
