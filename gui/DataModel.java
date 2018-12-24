package gui;

import java.util.Arrays;

public class DataModel {
    private static int size;
    private int sequence;
    private String[] points;

    public DataModel(int sequence, String[] points) {
        this.sequence = sequence;
        this.points = Arrays.copyOf(points, points.length);
        size = points.length;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String[] getPoints() {
        return points;
    }

    public void setPoints(String[] points) {
        this.points = points;
    }

    public static int getSize() {
        return size;
    }

    public String toString() {
        String res = sequence + ": ";
        for (String s : points) {
            res += s + ", ";
        }

        return res;
    }
}
