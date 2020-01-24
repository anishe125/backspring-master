package pack.apigateway.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.apigateway.dto.ItemDto;


@FeignClient(name = "item-service")
public interface ItemServiceFeignClient {

    @GetMapping(path = "/items/items/{id}")
    ResponseEntity<ItemDto> getItemById(@PathVariable("id") int id);
}