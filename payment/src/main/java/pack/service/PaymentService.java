package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pack.dao.PaymentDao;
import pack.dto.PaymentDto;
import pack.dto.UserDetailsDto;
import pack.feign.OrderServiceFeignClient;


@Service
public class PaymentService {

    private final PaymentDao paymentDao;

    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @Autowired
    public PaymentService(@Qualifier("sqlite") PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public PaymentDto initPayment(int orderId, UserDetailsDto userDetails) {
        this.changeOrderStatus(orderId, userDetails);
        return paymentDao.initPayment(orderId, userDetails);
    }

    public PaymentDto getPaymentStatus(int orderId) {
        return paymentDao.getPaymentStatus(orderId);
    }

    private void changeOrderStatus(int orderId, UserDetailsDto userDetailsDto) {
        String orderStatus = "";
        if (userDetailsDto.getCardAuthorizationInfo().equals("AUTHORIZED")) {
            orderStatus = "PAID";
        } else if (userDetailsDto.getCardAuthorizationInfo().equals("UNAUTHORIZED")) {
            orderStatus = "FAILED";
        }

        if (!orderStatus.equals("")) {
            orderServiceFeignClient.setOrderStatus(orderId, orderStatus);
        }
    }
}