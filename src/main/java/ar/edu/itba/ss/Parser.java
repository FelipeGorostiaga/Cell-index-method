package ar.edu.itba.ss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    /* package private */
    static double GRID_LENGTH;
    static int NUM_PARTICLES;
    static int M;
    static List<Particle> particles = new LinkedList<Particle>();

    public static void start() throws FileNotFoundException {
        parse();
    }

    //static
    private static void parse() throws FileNotFoundException {
        File file = new File("test.txt");
        Scanner sc = new Scanner(file);
        NUM_PARTICLES = sc.nextInt();
        GRID_LENGTH = sc.nextDouble();
        for (int i = 0; i < NUM_PARTICLES; i++){
            // id, radius, property
            particles.add(new Particle(i , sc.nextDouble(), sc.nextDouble()));
        }
    }

    //TODO: DYNAMIC
}
