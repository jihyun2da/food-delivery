package fooddelivery.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name = "house", url = "${api.url.house}")
public interface PaymentService {
<<<<<<< HEAD
    @RequestMapping(method= RequestMethod.GET, path="/payments/{id}")
    public Payment getPayment(@PathVariable("id") Long id);
    @RequestMapping(method= RequestMethod.GET, path="/payments")
=======
    @RequestMapping(method= RequestMethod.POST, path="/payments")
>>>>>>> origin/template
    public void pay(@RequestBody Payment payment);
}

