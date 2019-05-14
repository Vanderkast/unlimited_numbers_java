package com.vanderkast.lib;

import java.util.ArrayList;
import java.util.List;

public class BooleanDigit implements Digit {
    private static String binaryError = "List<Boolean> must be an reversed binary representation of positive integer from [0;9]";

    private ArrayList<Boolean> vector = new ArrayList<>();

    public BooleanDigit(char symbol) {
        fillVectorByInteger(symbol);
    }

    public BooleanDigit(int number) {
        checkNumberOnLegal(number);
        fillVectorByInteger(number);
    }

    public BooleanDigit(List<Boolean> binary) {
        checkBinaryOnLegal(binary);
        this.vector.addAll(binary);
    }

    private void fillVectorByInteger(int number) {
        while (number > 1) {
            vector.add(mod2(number));
            number /= 2;
        }
        vector.add(mod2(number));
    }

    private void checkNumberOnLegal(int number) {
        if (0 > number || number > 9)
            throw new IllegalArgumentException("Number must be an positive integer from [0;9]");
    }

    private void checkBinaryOnLegal(List<Boolean> binary) {
        if (binary.size() == 0 || binary.size() > 4 || binaryBiggerThenNine(binary))
            throw new IllegalArgumentException(binaryError);
    }

    private boolean binaryBiggerThenNine(List<Boolean> binary) {
        return binary.size() == 4 && binary.get(0) && (binary.get(2) || binary.get(1));
    }

    private boolean mod2(int number) {
        return number % 2 == 1;
    }

    @Override
    public Digit plus(Digit digit) {
        boolean thisLengthBigger = this.vector.size() > digit.binary().size();
        int biggerLength = thisLengthBigger ? this.vector.size() : digit.binary().size();
        ArrayList<Boolean> sum = new ArrayList<>(biggerLength);

        boolean overflow = sum(thisLengthBigger ? this.vector : digit.binary(), thisLengthBigger ? digit.binary() : this.vector);
        return new BooleanDigit(overflow ? 1 : 0);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    @Override
    public Digit minus(Digit digit) {
        return null;
    }

    @Override
    public List<Boolean> binary() {
        return vector;
    }

    @Override
    public int asInt() {
        int i = 1, result = 0;
        for (Boolean x : vector) {
            result += x ? i : 0;
            i <<= 1;
        }
        return result;
    }

    @Override
    public char asChar() {
        return String.valueOf(asInt()).charAt(0);
    }
}
