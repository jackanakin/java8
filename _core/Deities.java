package _core;

import _core.Pantheon.Culture;

public class Deities {
    private String name;
    private double power;
    private Culture culture;

    public Deities(String name, double power, Culture culture) {
        this.name = name;
        this.power = power;
        this.culture = culture;
    }

    public Deities() {
        super();
    }

    public Deities(String name) {
        super();
    }

    public Culture getCulture() {
        return culture;
    }

    public String getName() {
        return name;
    }

    public double getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "[Name=" + name + ", Power=" + power + "]";
    }
}
