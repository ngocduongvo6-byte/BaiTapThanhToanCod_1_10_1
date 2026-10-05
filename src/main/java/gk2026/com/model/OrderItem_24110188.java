package gk2026.com.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "OrderItem")
public class OrderItem_24110188 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order_24110188 order;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product_24110188 product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal subtotal;

    public Integer getOrderItemId() { return orderItemId; }
    public void setOrderItemId(Integer value) { orderItemId = value; }
    public Order_24110188 getOrder() { return order; }
    public void setOrder(Order_24110188 value) { order = value; }
    public Product_24110188 getProduct() { return product; }
    public void setProduct(Product_24110188 value) { product = value; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer value) { quantity = value; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal value) { unitPrice = value; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal value) { subtotal = value; }
}
