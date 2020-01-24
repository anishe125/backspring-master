package pack.dao;

import pack.dto.PaymentDto;
import pack.dto.UserDetailsDto;


public interface PaymentDao {
    PaymentDto initPayment(int orderId, UserDetailsDto userDetails);

    PaymentDto getPaymentStatus(int orderId);
}
