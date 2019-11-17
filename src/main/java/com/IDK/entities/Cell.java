package com.IDK.entities;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.concurrent.Callable;

@Getter
@Setter
public abstract class Cell implements Callable<String> {

    // Seconds
    public Random random = new Random();
    private final int T_full = random.nextInt(5);
    private final int T_starve = random.nextInt(20);

    protected int currentSaturated;
    protected int currentStarve;

    protected int timesEaten;

    protected boolean alive;
    protected boolean reproduce;
    private Food food = Food.getInstance();

    public static List<Cell> cells;

    protected static final Logger log = LoggerFactory.getLogger(Cell.class);
    public Cell() {
        this.currentSaturated = T_full;
        this.currentStarve = 0;
        this.timesEaten = 0;
        this.alive = true;
        this.reproduce = false;
        cells = getCells();
    }

    private void die() {
        log.info(this.hashCode() + " cell died");
        cells.remove(this);
        food.add(random.nextInt(4) + 1);
        alive = false;
    }

    abstract void divide() ;

    private void eat() {
        if (food.consume()) {
            currentSaturated = T_full;
            currentStarve = 0;
            timesEaten++;
            log.info("Cell " + this.hashCode() + " ate, current starve:{} current saturated:{}", this.currentStarve, this.currentSaturated);
        }
    }

    public void foodCycleScheduledJob() {
        if (currentSaturated == 0) {
            currentStarve++;
        } else {
            currentSaturated--;
        }

        log.info(this.hashCode() + " food cycle current starve:{}, current saturated:{}", this.currentStarve, this.currentSaturated);
    }

    public static List<Cell> getCells() {
        if (cells == null) {
            cells = Collections.synchronizedList(new ArrayList<Cell>());
        }

        return cells;
    }

    @Override
    public String call() throws Exception {
        while (alive) {
            if (currentStarve == T_starve) {
                this.die();
                break;
            }
            this.eat();

            log.info(this.hashCode() + " " + timesEaten +" times eaten");

            if (timesEaten >= 10) {
                this.reproduce = true;
                this.divide();
            }

            this.foodCycleScheduledJob();
            Thread.sleep(1000);
        }
        //Thread.currentThread().interrupt();
        return this.toString();
    }
}
