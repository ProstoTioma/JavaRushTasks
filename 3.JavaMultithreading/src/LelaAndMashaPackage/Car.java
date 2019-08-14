package LelaAndMashaPackage;

public class Car {
    private String name;
    private int year;
    private double speed;
    private static double weight;

    public static void setWeight(double weight) {
        Car.weight = weight;
    }

    public static double getWeight() {
        return weight;
    }

    public double getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Car(String name) {
        this.name = name;
    }

    public Car() {
    }


    public Car(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public Car(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    public Car(String name, int year, double speed) {
        this.name = name;
        this.year = year;
        this.speed = speed;
    }
}
