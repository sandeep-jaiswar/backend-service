package com.apep.avengers.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long userId;
    private long instrumentId;
    private OrderType type;
    private OrderSide side;
    private BigDecimal quantity;
    private BigDecimal price; // For Limit orders
    private OrderStatus status;
    private LocalDateTime timestamp;

    // Enum for Order Type
    public enum OrderType {
        MARKET, LIMIT, STOP
    }

    // Enum for Order Side
    public enum OrderSide {
        BUY, SELL
    }

    // Enum for Order Status
    public enum OrderStatus {
        NEW, PARTIALLY_FILLED, FILLED, CANCELED, REJECTED
    }

    // Getters and Setters (or use Lombok)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", userId=" + userId +
               ", instrumentId=" + instrumentId +
               ", type=" + type +
               ", side=" + side +
               ", quantity=" + quantity +
               ", price=" + price +
               ", status=" + status +
               ", timestamp=" + timestamp +
               '}';
    }
}
