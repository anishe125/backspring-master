package pack.apigateway.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pack.apigateway.dto.ItemDto;


@FeignClient(name = "warehouse-service")
public interface WarehouseServiceFeignClient {

    @GetMapping(path = "/warehouse/items/{id}")
    ResponseEntity<ItemDto> getItemById(@PathVariable("id") int id);
}