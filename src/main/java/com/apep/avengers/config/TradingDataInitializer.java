package com.apep.avengers.config;

import com.apep.avengers.storage.TradingDataStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TradingDataInitializer implements CommandLineRunner {

    private final TradingDataStore tradingDataStore;

    public TradingDataInitializer(TradingDataStore tradingDataStore) {
        this.tradingDataStore = tradingDataStore;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running TradingDataInitializer...");
        tradingDataStore.demoUsage();
        System.out.println("TradingDataInitializer finished.");
    }
}
