package com.example.olga.aa_app;

public class Symptom implements Comparable {
    private int name;
    private long timestamp;

    public Symptom(int name) {
        this.name = name;
        timestamp = System.currentTimeMillis();
    }

    /**
     * Returning a a resource identifier is not the most elegant solution but has the benefit that the ID is more or
     * less unique and that an activity can format symptoms properly if wanted.
     *
     * @return Resource identifier pointing to a symptom name string.
     */
    public int getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Symptom) {
            return Integer.compare(((Symptom) o).name, name);
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
        return ((Symptom) o).name == name;
    }
}
