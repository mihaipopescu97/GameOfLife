package com.IDK;

import com.IDK.entities.Asexual;
import com.IDK.entities.Cell;
import com.IDK.entities.Sexual;


import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Cell> cells = Cell.getCells();
        cells.add(new Asexual());
        cells.add(new Asexual());
        cells.add(new Sexual());
        cells.add(new Sexual());
        cells.add(new Sexual());


        ExecutorService executor = Executors.newFixedThreadPool(8);

        executor.invokeAll(cells);


    }
}
