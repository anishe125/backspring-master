package pack.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.entity.Order;
import pack.entity.OrderItemDto;
import pack.entity.exceptions.DataIntegrityViolationException;
import pack.entity.exceptions.ItemNotFoundException;
import pack.service.OrderService;

@RequestMapping("orders")
@RestController
public class AppController {

    private final OrderService orderService;

    @Autowired
    public AppController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity getItems() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity getItemById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(orderService.getOrderByID(id));
        } catch (ItemNotFoundException e) {
            return new GlobalControllerExceptionHandler().handleItemNotFound();
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity getOrderItems(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(orderService.getOrderItems(id));
        } catch (ItemNotFoundException e) {
            return new GlobalControllerExceptionHandler().handleItemNotFound();
        }
    }

    @PostMapping
    public ResponseEntity addOrder(@RequestBody Order order) {
        try {
            return ResponseEntity.ok(orderService.addOrder(order));
        } catch (DataIntegrityViolationException e) {
            return new GlobalControllerExceptionHandler().handleConflict();
        }
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity setOrderStatus(@PathVariable("id") int id, @PathVariable("status") String status) {
        try {
            return ResponseEntity.ok(orderService.setOrderStatus(id, status));
        } catch (ItemNotFoundException e) {
            return new GlobalControllerExceptionHandler().handleItemNotFound();
        }catch (DataIntegrityViolationException e) {
            return new GlobalControllerExceptionHandler().handleConflict();
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity addItem(@PathVariable("id") int id, @RequestBody OrderItemDto item) {
        try {
            return ResponseEntity.ok(orderService.addItems(id, item));
        } catch (ItemNotFoundException e) {
            return new GlobalControllerExceptionHandler().handleItemNotFound();
        }
        catch (DataIntegrityViolationException e) {
            return new GlobalControllerExceptionHandler().handleConflict();
        }
    }
}