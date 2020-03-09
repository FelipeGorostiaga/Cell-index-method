package ar.edu.itba.ss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Parser {

    static int N;
    static Queue<Particle> particles = new LinkedList<>();
    static double L;
    static double maxRadius = 0;

    static void start() throws Exception {
        parseStaticFile();
        parseDynamicFile();
    }

    private static void parseStaticFile() throws FileNotFoundException {
        File file = new File(CommandParser.staticFilePath);
        Scanner sc = new Scanner(file);
        N = sc.nextInt();
        L = sc.nextDouble();
        for (int i = 0; i < N; i++) {
            // id, radius, property
            double radius =  sc.nextDouble();
            if(radius > maxRadius) maxRadius = radius;
            particles.add(new Particle(i + 1 , radius, sc.nextDouble()));
        }
    }

    private static void parseDynamicFile() throws FileNotFoundException{
        File dynamicFile = new File(CommandParser.dynamicFilePath);
        Scanner sc = new Scanner(dynamicFile);
        sc.nextInt();
        for (int i = 0; i < N; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            Particle particle = particles.poll();
            particle.setX(x);
            particle.setY(y);
            particles.add(particle);
        }
    }

}
