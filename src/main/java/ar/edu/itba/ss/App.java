package ar.edu.itba.ss;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static ar.edu.itba.ss.CommandParser.*;
import static ar.edu.itba.ss.Parser.L;
import static ar.edu.itba.ss.Parser.particles;

public class App {

    //each cells contains the list of particles it has
    private static List<List<Particle>> cells;

    public static void main(String[] args) {
        CommandParser.parseCommandLine(args);
        long parsingStartTime = 0;
        try {
            parsingStartTime = System.currentTimeMillis();
            Parser.start();
        } catch (Exception e) {
            System.out.println("Invalid file name...");
            System.exit(1);
        }
        System.out.println("Time taken to parse file: " + (System.currentTimeMillis() - parsingStartTime) + " ms");
        final long startingTime = System.currentTimeMillis();
        if(bruteForce) {
            bruteForceAlgorithm();
        }
        else {
            cellIndexAlgorithm();
        }
        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startingTime;
        String algorithm = bruteForce ? "Brute force algorithm" : "Cell index algorithm";
        System.out.println(algorithm  + " execution time: " + executionTime + " milliseconds");
        System.out.println("Total execution time: " + (endTime - parsingStartTime) + " milliseconds");
        printAndWriteToFile();

    }

    private static void printAndWriteToFile() {
        File file = new File("/files/output.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output.txt", "UTF-8");
        } catch (Exception e) {
            System.out.println("Couldnt write output to file...");
        }


        for (Particle p : particles) {
            writer.print(p.getId());
            System.out.print(p.getId());
            for (Particle neighbour : p.getNeighbours()) {
                writer.print(" " + neighbour.getId());
                System.out.print(" " + neighbour.getId());
            }
            writer.print("\n");
            System.out.print("\n");
        }
        writer.close();
    }

    // O(N^2)
    private static void bruteForceAlgorithm() {
        final double rc = 0.1;
        for(Particle p1: particles) {
            for (Particle p2 : particles) {
                if(!p1.equals(p2) && !p1.getNeighbours().contains(p2)) {

                    double distance;
                    if (periodicContour){
                        distance = p1.calculatePeriodicDistance(p2);
                    }else{
                        distance = p1.calculateDistance(p2);
                    }
                    if (distance < RC){
                        p1.addNeighbour(p2);
                        p2.addNeighbour(p1);
                    }
                }
            }
        }
    }

    // O(N)
    private static void cellIndexAlgorithm() {
        initializeCells();
        populateCells();
        for (List<Particle> cell : cells) {
            for (Particle p : cell) {
                double cellX = p.getCellX();
                double cellY = p.getCellY();
                checkNeighbourCells(p, cellX, cellY);
                checkNeighbourCells(p, cellX, cellY + 1);
                checkNeighbourCells(p, cellX + 1, cellY + 1);
                checkNeighbourCells(p, cellX + 1, cellY);
                checkNeighbourCells(p, cellX + 1, cellY - 1);
            }
        }
    }

    private static void checkNeighbourCells(Particle p, double cellX, double cellY) {
        if(periodicContour) {
            if(cellX >= M) cellX = 0;
            if(cellY >= M) cellY = 0;
            if(cellX == -1) cellX = M - 1;
            if(cellY == -1) cellY = M - 1;
        }
        else {
            if(cellX < 0 || cellY < 0 || cellX >= M || cellY >= M) return;
        }
        int currentCell = (int) (cellY * M + cellX);
        List<Particle> particlesInCurrentCell = cells.get(currentCell);
        for(Particle neighbourCellParticle: particlesInCurrentCell) {
            if(!neighbourCellParticle.equals(p)) {
                double distance = p.calculateDistance(neighbourCellParticle);
                if(distance < RC) {
                    p.addNeighbour(neighbourCellParticle);
                    neighbourCellParticle.addNeighbour(p);
                }
            }
        }
    }

    private static void initializeCells() {
        cells = new ArrayList<>();
        for (int i = 0; i < (M * M) ; i++){
            cells.add(new ArrayList<Particle>());
        }
    }

    private static void populateCells() {
        for (Particle p : particles){
            double cellX = Math.floor(p.getX() / (L / M));
            double cellY = Math.floor(p.getY() / (L / M));
            int cellNumber = (int) (cellY * M + cellX);
            List <Particle> cell = cells.get(cellNumber);
            p.setCellX(cellX);
            p.setCellY(cellY);
            cell.add(p);
        }
    }
}
