package ar.edu.itba.ss;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Particle {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getProperty() {
        return property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    public Set<Particle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Particle> neighbours) {
        this.neighbours = neighbours;
    }

    public double getCellX() {
        return cellX;
    }

    public void setCellX(double cellX) {
        this.cellX = cellX;
    }

    public double getCellY() {
        return cellY;
    }

    public void setCellY(double cellY) {
        this.cellY = cellY;
    }

    public double calculateDistance(Particle particle){
        return Math.sqrt(Math.pow(x - particle.getX(), 2) + Math.pow(y - particle.getY(), 2))
                - radius - particle.getRadius();
    }

    public void addNeighbour(Particle neighbour){
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

    public double calculatePeriodicDistance(Particle particle) {
        double xDistance = Math.abs(x - particle.x);
        // La distancia requerida "dando la vuelta" es menor a la directa
        if (xDistance > Parser.GRID_LENGTH / 2) xDistance = Parser.GRID_LENGTH - xDistance;
        double yDistance = Math.abs(y - particle.y);
        if (yDistance > Parser.GRID_LENGTH / 2) yDistance = Parser.GRID_LENGTH - yDistance;
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public int compareTo(Particle particle){
        return id - particle.getId();
    }

}
