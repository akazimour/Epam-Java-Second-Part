package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private int degrees;

    public static Direction ofDegrees(int degrees) {
        Direction[] values = Direction.values();

        while (degrees > values[values.length-1].degrees){
            degrees = degrees - 360;
        }
        while (degrees < values[0].degrees){
            degrees = degrees + 360;
        }
        for (Direction v : values){
            if (degrees == v.degrees){
                return v;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
        Direction direction = ofDegrees(degrees);
        if (direction != null) {
            return direction;
        } else if (direction == null) {
            Direction[] values = direction.values();
            while (degrees >= 360){
                degrees = degrees -360;
            }
                return ofDegrees(findClosest(values, degrees));
            }
        {
        }
        return direction;
    }

    public Direction opposite() {
        int actualDegrees = this.degrees;
        int oppositeDegrees = 0;

        if (actualDegrees < 180) {
            oppositeDegrees = actualDegrees + 180;
            return ofDegrees(oppositeDegrees);
        }
        else if (actualDegrees >= 180){
            oppositeDegrees = actualDegrees -180;
            return ofDegrees(oppositeDegrees);
        }else
            return null;
    }

    public int differenceDegreesTo(Direction direction) {
        int actualDegrees = this.degrees;
        int i = actualDegrees - direction.degrees;
        if (i==-315){
            return 45;
        }
       return Math.abs(i);
    }

    public static int findClosest(Direction[] arr, int target) {
        int idx = 0;
        int dist = Math.abs(arr[0].degrees - target);

        for (int i = 1; i< arr.length; i++) {
            int cdist = Math.abs(arr[i].degrees - target);

            if (cdist < dist) {
                idx = i;
                dist = cdist;
            }
        }
        return arr[idx].degrees;
    }
}
