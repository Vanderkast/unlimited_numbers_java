package com.vanderkast.lib;

import java.util.ArrayList;
import java.util.List;

public class VectorExtension {
    public static List<Byte> reversed(List<Byte> vector) {
        List<Byte> result = new ArrayList<>(vector.size());
        for (int i = vector.size() - 1; i >= 0; i--) {
            result.add(vector.get(i));
        }
        return result;
    }

    public static Byte[] reversed(Byte[] vector) {
        Byte[] result = new Byte[vector.length];
        for (int i = vector.length - 1; i >= 0; i--) {
            result[i] = vector[i];
        }
        return result;
    }

    public static Byte[] trimmed(Byte[] vector) {
        int zeros = 0;

        while (zeros < vector.length - 1 && vector[zeros] == 0)
            zeros ++;

        Byte[] result = new Byte[vector.length - zeros];
        System.arraycopy(vector, zeros, result, 0, vector.length - zeros);
        return result;
    }

    public static Byte[] trimmedBack(Byte[] vector){
        int zeros = 0;
        while (zeros < vector.length - 1 && vector[vector.length - zeros - 1] == 0)
            zeros ++;

        Byte[] result = new Byte[vector.length - zeros];
        System.arraycopy(vector,0, result, 0, vector.length - zeros);
        return result;
    }

    public static List<Byte> trimmed(List<Byte> vector) {
        List<Byte> result = new ArrayList<>(vector);

        trim(result);

        return result;
    }

    public static void trim(List<Byte> vector) {
        while (vector.get(0) == 0 && vector.size() > 1)
            vector.remove(0);
    }

    public static Byte[] trimmedAndReversed(Byte[] vector) {
        return reversed(trimmed(vector));
    }

    public static List<Byte> trimmedAndReversed(List<Byte> vector) {
        return reversed(trimmed(vector));
    }

    public static List<Byte> fromInteger(int number){
        List<Byte> result = new ArrayList<>();
        while (number > 1){
            result.add((byte) (number % 10));
            number /= 10;
        }
        return result;
    }
}
