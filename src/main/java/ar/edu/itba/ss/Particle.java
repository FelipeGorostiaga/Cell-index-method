package ar.edu.itba.ss;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static ar.edu.itba.ss.Parser.L;

public class Particle implements Comparable<Particle> {

    private int id;
    private double x;
    private double y;
    private double radius;
    private double property;
    private Set<Particle> neighbours;
    private double cellX;
    private double cellY;

    public Particle(int id, double x, double y, double radius, double property){
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.neighbours = new TreeSet<>();
        this.property = property;
    }

    Particle(int id, double radius, double property){
        this.id = id;
        this.radius = radius;
        this.property = property;
        this.neighbours = new TreeSet<>();
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    double getX() {
        return x;
    }

    void setX(double x) {
        this.x = x;
    }

    double getY() {
        return y;
    }

    void setY(double y) {
        this.y = y;
    }

    double getRadius() {
        return radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    double getProperty() {
        return property;
    }

    void setProperty(double property) {
        this.property = property;
    }

    Set<Particle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Particle> neighbours) {
        this.neighbours = neighbours;
    }

    double getCellX() {
        return cellX;
    }

    void setCellX(double cellX) {
        this.cellX = cellX;
    }

    double getCellY() {
        return cellY;
    }

    void setCellY(double cellY) {
        this.cellY = cellY;
    }

    double calculateDistance(Particle particle){
        return Math.sqrt(Math.pow(x - particle.getX(), 2) + Math.pow(y - particle.getY(), 2))
                - radius - particle.getRadius();
    }

    void addNeighbour(Particle neighbour){
        this.neighbours.add(neighbour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Particle { Id = " + id +
                ", x = " + x +
                ", y = " + y +
                ", radius = " + radius +
                ", property = " + property +
                '}';
    }

    double calculatePeriodicDistance(Particle particle) {
        double xDistance = Math.abs(x - particle.x);
        // La distancia requerida "dando la vuelta" es menor a la directa
        if (xDistance > L / 2) xDistance = L - xDistance;
        double yDistance = Math.abs(y - particle.y);
        if (yDistance > L / 2) yDistance = L - yDistance;
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public int compareTo(Particle particle){
        return id - particle.getId();
    }

}
