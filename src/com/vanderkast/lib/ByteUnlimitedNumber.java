package com.vanderkast.lib;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ByteUnlimitedNumber implements UnlimitedNumber {
    private ArrayList<Byte> digits;

    public ByteUnlimitedNumber(String number) {
        digits = new ArrayList<>(number.length());
        for (int i = 0; i < number.length(); i++) {
            digits.add(new Byte(String.valueOf(number.charAt(i))));
        }
    }

    public ByteUnlimitedNumber(List<Byte> vector, boolean unsafe) {
        if (unsafe)
            this.digits = new ArrayList<>(vector);
        else
            fillDigitsSafely(vector);
    }

    public ByteUnlimitedNumber(List<Byte> vector) {
        fillDigitsSafely(vector);
    }

    private void fillDigitsSafely(List<Byte> vector) {
        this.digits = new ArrayList<>(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            this.digits.add((byte) (abs(vector.get(i) % 10)));
        }
    }

    @Override
    public void plus(UnlimitedNumber number) {

    }

    @Override
    public void minus(UnlimitedNumber number) {

    }

    @Override
    public void composition(UnlimitedNumber number) {

    }

    @Override
    public void division(UnlimitedNumber number) {

    }

    @Override
    public void modular(UnlimitedNumber number) {

    }

    @Override
    public List<Byte> vector() {
        return digits;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Byte x : digits) {
            builder.append(x);
        }
        return builder.toString();
    }
}
