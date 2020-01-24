package pack.apigateway.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pack.apigateway.dto.AggregatedOrderDto;
import pack.apigateway.service.ApiGatewayService;


@RestController
@RequestMapping("api")
public class AppController {
    private final ApiGatewayService apiGatewayService;

    public AppController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @GetMapping(path = "/orders/{id}")
    public AggregatedOrderDto getAggregatedOrder(@PathVariable int id) {
        return apiGatewayService.getAggregatedOrder(id);
    }
}
