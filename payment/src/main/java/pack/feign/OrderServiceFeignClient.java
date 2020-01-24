package pack.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name="order-service")
public interface OrderServiceFeignClient {

    @PutMapping("/orders/{id}/status/{status}")
    ResponseEntity setOrderStatus(@PathVariable("id") int id, @PathVariable("status") String status);
}