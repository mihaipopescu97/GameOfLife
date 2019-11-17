package com.IDK.entities;

import java.util.List;

public class Asexual extends Cell {

    public Asexual() {
        super();
        currentSaturated = 0;
    }

    public void divide() {
        this.setCurrentStarve(0);
        this.setCurrentSaturated(0);
        this.setTimesEaten(0);

        List<Cell> cells = Cell.getCells();

        Asexual newCell = new Asexual();
        cells.add(newCell);

        log.info("Asexual cell " + this.hashCode() + " divided into {} {}", this.hashCode(), newCell.hashCode() );
    }
}
