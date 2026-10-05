package gk2026.com.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order_24110188 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users_24110188 user;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false, length = 30)
    private String paymentMethod;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer value) { orderId = value; }
    public Users_24110188 getUser() { return user; }
    public void setUser(Users_24110188 value) { user = value; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime value) { orderDate = value; }
    public String getStatus() { return status; }
    public void setStatus(String value) { status = value; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String value) { paymentMethod = value; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal value) { totalAmount = value; }
}
