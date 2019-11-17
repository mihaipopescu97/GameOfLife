package com.IDK.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class Food {

    private static AtomicInteger amount = new AtomicInteger();
    private static Food instance;
    private static final Logger log = LoggerFactory.getLogger(Food.class);


    public static Food getInstance() {
        if(instance == null) {
            amount.set(40);
            instance = new Food();
        }
        return instance;
    }

    public synchronized boolean consume() {
        if(amount.get() > 0) {
            log.info(amount.decrementAndGet() + " food left");
            return true;
        } else {
            log.info("No food left!");
            return false;
        }
    }

    public void add(int addedFood) {
        log.info(addedFood+" food added");
        amount.addAndGet(addedFood);
    }

}
