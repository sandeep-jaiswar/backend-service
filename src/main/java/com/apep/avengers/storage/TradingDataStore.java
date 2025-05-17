package com.apep.avengers.storage;

import com.apep.avengers.model.Instrument;
import com.apep.avengers.model.Order;
import net.openhft.chronicle.map.ChronicleMap;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component; // Added this import

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TradingDataStore {

    private ChronicleMap<Long, Order> orderMap;
    private ChronicleMap<Long, Instrument> instrumentMap;

    private static final String ORDER_STORE_FILE = "order-store.dat";
    private static final String INSTRUMENT_STORE_FILE = "instrument-store.dat";

    // Estimated average size in bytes for Order and Instrument objects
    // These are rough estimates and might need tuning based on actual data size
    private static final int AVERAGE_ORDER_SIZE = 256;
    private static final int AVERAGE_INSTRUMENT_SIZE = 128;


    @PostConstruct
    public void init() {
        try {
            // Configure and build the ChronicleMaps
            // These files will be created in the application's working directory
            orderMap = ChronicleMap
                .of(Long.class, Order.class)
                .name("order-store")
                .averageValueSize(AVERAGE_ORDER_SIZE) // Use averageValueSize
                .entries(10_000_000) // Estimate the maximum number of orders
                .createOrRecoverPersistedTo(new File(ORDER_STORE_FILE));

            instrumentMap = ChronicleMap
                .of(Long.class, Instrument.class)
                .name("instrument-store")
                .averageValueSize(AVERAGE_INSTRUMENT_SIZE) // Use averageValueSize
                .entries(1_000) // Estimate the maximum number of instruments
                .createOrRecoverPersistedTo(new File(INSTRUMENT_STORE_FILE));

            System.out.println("Chronicle Maps initialized successfully.");

        } catch (IOException e) {
            System.err.println("Failed to initialize Chronicle Maps: " + e.getMessage());
            // Depending on the application's requirements, you might want to throw an exception
            // or handle this failure more gracefully.
        }
    }

    @PreDestroy
    public void close() {
        if (orderMap != null) {
            orderMap.close();
            System.out.println("Order Chronicle Map closed.");
        }
        if (instrumentMap != null) {
            instrumentMap.close();
            System.out.println("Instrument Chronicle Map closed.");
        }
    }

    public void saveOrder(Order order) {
        if (orderMap != null) {
            orderMap.put(order.getId(), order);
        } else {
            System.err.println("Order Chronicle Map is not initialized.");
        }
    }

    public Order findOrderById(long orderId) {
        if (orderMap != null) {
            return orderMap.get(orderId);
        }
        return null;
    }

    public void saveInstrument(Instrument instrument) {
        if (instrumentMap != null) {
            instrumentMap.put(instrument.getId(), instrument);
        }
    }

    public Instrument findInstrumentById(long instrumentId) {
        if (instrumentMap != null) {
            return instrumentMap.get(instrumentId);
        }
        return null;
    }

    // Example methods to demonstrate usage (can be called from a service or controller)
    public void demoUsage() {
        // Create and save an instrument
        Instrument btcUsd = new Instrument();
        btcUsd.setId(1L);
        btcUsd.setSymbol("BTC/USD");
        btcUsd.setName("Bitcoin / US Dollar");
        btcUsd.setExchange("CRYPTO");
        saveInstrument(btcUsd);
        System.out.println("Saved Instrument: " + btcUsd);

        // Retrieve the instrument
        Instrument retrievedInstrument = findInstrumentById(1L);
        System.out.println("Retrieved Instrument: " + retrievedInstrument);

        // Create and save an order
        Order buyOrder = new Order();
        buyOrder.setId(101L);
        buyOrder.setUserId(1L);
        buyOrder.setInstrumentId(1L);
        buyOrder.setType(Order.OrderType.LIMIT);
        buyOrder.setSide(Order.OrderSide.BUY);
        buyOrder.setQuantity(new BigDecimal("0.5"));
        buyOrder.setPrice(new BigDecimal("70000.00"));
        buyOrder.setStatus(Order.OrderStatus.NEW);
        buyOrder.setTimestamp(LocalDateTime.now());
        saveOrder(buyOrder);
        System.out.println("Saved Order: " + buyOrder);

        // Retrieve the order
        Order retrievedOrder = findOrderById(101L);
        System.out.println("Retrieved Order: " + retrievedOrder);
    }
}
