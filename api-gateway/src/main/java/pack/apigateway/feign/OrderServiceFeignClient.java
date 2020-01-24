package pack.apigateway.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.apigateway.dto.OrderDto;


@FeignClient(name = "order-service")
public interface OrderServiceFeignClient {

    @GetMapping(path = "/orders/{id}")
    ResponseEntity<OrderDto> getItemById(@PathVariable("id") int id);
}