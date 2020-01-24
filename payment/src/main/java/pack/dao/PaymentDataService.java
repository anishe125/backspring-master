package pack.dao;

import org.springframework.stereotype.Repository;
import pack.dto.PaymentDto;
import pack.dto.UserDetailsDto;
import pack.entity.Payment;

import java.sql.*;

@Repository("sqlite")
public class PaymentDataService implements PaymentDao {

    private static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Anna\\backspring-master\\payment\\Payments.db";
    private Connection connection;

    public PaymentDataService() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PaymentDto initPayment(int orderId, UserDetailsDto userDetails) {

        Payment payment = new Payment(
                orderId,
                userDetails.getCardAuthorizationInfo(),
                userDetails.getName()
        );

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO Payments VALUES(?, ?, ?);"
            );

            statement.setInt(1, payment.getOrderId());
            statement.setString(2, payment.getStatus());
            statement.setString(3, payment.getUserName());

            int resultSet = statement.executeUpdate();
            if (resultSet != 0) {
                return PaymentDto.fromPayment(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PaymentDto getPaymentStatus(int orderId) {
        ResultSet resultSet = null;

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "Select * FROM Payments WHERE orderId=?;"
            );
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PaymentDto paymentDto = new PaymentDto();
                paymentDto.setOrderId(resultSet.getInt("orderId"));
                paymentDto.setStatus(resultSet.getString("status"));
                resultSet.close();
                return paymentDto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
