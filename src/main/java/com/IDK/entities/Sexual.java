package com.IDK.entities;

import java.util.List;

public class Sexual extends Cell {


    public Sexual() {
        super();
    }

    public void divide() {
        for (Cell cell : cells) {
            if (cell instanceof Sexual && cell.reproduce) {
                cell.setTimesEaten(0);
                cell.setReproduce(false);
                Sexual newCell = new Sexual();
                newCell.setCurrentSaturated(0);
                cells.add(newCell);
                this.setTimesEaten(0);
                this.setReproduce(false);
                log.info("Sexual cell " + this.hashCode() + " paired with " + cell.hashCode() + " and resulted new cell:" + newCell.hashCode());
                return;
            }
        }
        log.info("Sexual cell " + this.hashCode() + " couldn't pair");
    }



}
