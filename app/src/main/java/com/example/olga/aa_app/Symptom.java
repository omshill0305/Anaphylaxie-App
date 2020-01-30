package com.example.olga.aa_app;

public class Symptom implements Comparable {
    private String name;
    private long timestamp;

    public Symptom(String name) {
        this.name = name;
        timestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Symptom) {
            return ((Symptom) o).name.compareTo(name);
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Symptom)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        return ((Symptom) o).name.equalsIgnoreCase(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
