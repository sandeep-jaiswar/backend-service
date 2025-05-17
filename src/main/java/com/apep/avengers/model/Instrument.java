package com.apep.avengers.model;

import java.io.Serializable;

public class Instrument implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String symbol;
    private String name;
    private String exchange;

    // Getters and Setters (or use Lombok)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return String.valueOf(exchange);
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Instrument{" +
               "id=" + id +
               ", symbol='" + symbol + "'" +
               ", name='" + name + "'" +
               ", exchange='" + exchange + "'" +
               '}';
    }
}
