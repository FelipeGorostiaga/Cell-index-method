package ar.edu.itba.ss;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class App {

    //each cells contains the list of particles it has
    private static List<List<Particle>> cells;
    private static List<Particle> particles;
    //TODO CHANGE THIS
    private static int MATRIX_SIZE = 5;
    private static double RC;

    public static void main(String[] args) {

        String algorithm = "Brute force";
        long parsingStartTime = System.currentTimeMillis();
        try {
            Parser.start();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Invalid filename...");
            return;
        }
        System.out.println("Time taken to parse file: " + (System.currentTimeMillis() - parsingStartTime) +
                " ms");
        final long startingTime = System.currentTimeMillis();

        bruteForceAlgorithm();
        cellIndexAlgorithm();

        // N number of particle
        // M cell division (cell qty MxM) lo elijo yo
        // L surface of grid (area L*L)
        // RC minimum distance to calculate between particles (radio de interaccion)

        final long endTime = System.currentTimeMillis();
        final long executionTime = endTime - startingTime;
        System.out.println(algorithm  + "  execution time" + executionTime + " milliseconds");
        System.out.println("Total execution time: " + (endTime - startingTime) + " milliseconds");

    }

    // O(N^2)
    private static void bruteForceAlgorithm() {
        final double rc = 0.1;
        for(Particle p1: particles) {
            for (Particle p2 : particles) {
                if(!p1.equals(p2) && !p1.getNeighbours().contains(p2)) {
                    if(p1.calculateDistance(p2) <= rc) {
                        p1.addNeighbour(p2);
                        p2.addNeighbour(p1);
                    }
                }
            }
        }
    }

    // O(N)
    private static void cellIndexAlgorithm() {
        // send calculated M_VALUE
        initializeCells(100);
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

        // periodic contour
        if(cellX > MATRIX_SIZE) cellX = 0;
        if(cellY > MATRIX_SIZE) cellY = 0;
        if(cellX < 0) cellX = MATRIX_SIZE;
        if(cellY < 0) cellY = MATRIX_SIZE;

        // normal
        if(cellX < 0 || cellY < 0 || cellX > MATRIX_SIZE || cellY > MATRIX_SIZE) return;
        int currentCell = (int) (cellY * MATRIX_SIZE + cellX);

        List<Particle> particlesInCurrentCell = cells.get(currentCell);

        for(Particle neighbourCellParticle: particlesInCurrentCell) {
            if(!neighbourCellParticle.equals(p)) {
                double distance = p.calculateDistance(neighbourCellParticle);
                if(distance <= RC) {
                    p.addNeighbour(neighbourCellParticle);
                    neighbourCellParticle.addNeighbour(p);
                }
            }
        }


    }

    private static void initializeCells(int m) {
        cells = new ArrayList<List<Particle>>();
        for (int i = 0; i < (m * m) ; i++){
            cells.add(new ArrayList<Particle>());
        }
    }

    private static void populateCells() {
        for (Particle p : particles){
            double cellX = Math.floor(p.getX() / (Parser.GRID_LENGTH / MATRIX_SIZE));
            double cellY = Math.floor(p.getY() / (Parser.GRID_LENGTH / MATRIX_SIZE));
            int cellNumber = (int) (cellY * MATRIX_SIZE + cellX);
            List <Particle> cell = cells.get(cellNumber);
            p.setCellX(cellX);
            p.setCellY(cellY);
            cell.add(p);
        }
    }
}
