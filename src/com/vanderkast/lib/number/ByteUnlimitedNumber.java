package com.vanderkast.lib.number;

import com.vanderkast.lib.VectorExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static java.lang.Math.abs;

public class ByteUnlimitedNumber implements UnlimitedNumber {
    private List<Byte> digits;
    private boolean isPositive;

    public ByteUnlimitedNumber(String number) {
        digits = new ArrayList<>(number.length());

        isPositive = number.charAt(0) != '-';

        fillDigitsFromNumber(number, ignoringLength(number, isPositive ? 0 : 1));

        setSign(isPositive);
    }

    private void fillDigitsFromNumber(String number, int toCharPos){
        for (int i = number.length() - 1; i >= toCharPos; i--) {
            digits.add(new Byte(String.valueOf(number.charAt(i))));
        }
    }

    private int ignoringLength(String number, int from){
        int ignoringPos = from;
        while ((ignoringPos < number.length() - 1) && number.charAt(ignoringPos) == '0') ignoringPos++;
        return  ignoringPos;
    }

    public ByteUnlimitedNumber(Byte[] vector, boolean positive, boolean unsafe) {
        fillDigits(vector, unsafe);
        setSign(positive);
    }

    public ByteUnlimitedNumber(List<Byte> vector, boolean positive, boolean unsafe) {
        fillDigits(vector, unsafe);
        setSign(positive);
    }

    public ByteUnlimitedNumber(List<Byte> vector, boolean positive) {
        fillDigitsSafely(vector);
        this.isPositive = positive;
    }

    public ByteUnlimitedNumber(List<Byte> vector) {
        fillDigitsSafely(vector);
    }

    public ByteUnlimitedNumber(Byte[] vector) {
        fillDigitsSafely(vector);
        setSign(true);
    }

    public ByteUnlimitedNumber(Byte[] vector, boolean positive) {
        fillDigitsSafely(vector);
        setSign(positive);
    }

    private void fillDigits(List<Byte> vector, boolean unsafe) {
        if (unsafe)
            this.digits = new ArrayList<>(vector);
        else
            fillDigitsSafely(vector);
    }

    private void fillDigits(Byte[] vector, boolean unsafe) {
        if (unsafe)
            this.digits = Arrays.asList(vector);
        else
            fillDigitsSafely(vector);
    }

    private void fillDigitsSafely(List<Byte> vector) {
        this.digits = new ArrayList<>(vector.size());
        for (int i = vector.size() - 1; i >= 0; i--) {
            this.digits.add((byte) (abs(vector.get(i) % 10)));
        }

        this.isPositive = true;
    }

    private void fillDigitsSafely(Byte[] vector) {
        this.digits = new ArrayList<>(vector.length);
        for (int i = vector.length - 1; i >= 0; i--) {
            this.digits.add((byte) (abs(vector[i] % 10)));
        }

        this.isPositive = true;
    }

    private void setSign(boolean positive) {
        if (isZero()) {
            this.isPositive = true;
            return;
        }
        this.isPositive = positive;
    }

    public boolean isZero(){
        return digits != null && digits.size() == 1 && digits.get(0) == 0;
    }

    @Override
    public boolean positive() {
        return isPositive;
    }

    @Override
    public List<Byte> reversedVector() {
        return digits;
    }

    @Override
    public List<Byte> vector() {
        return VectorExtension.reversed(digits);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        fillString(builder);
        return builder.toString();
    }

    private void fillString(StringBuilder builder) {
        if (!isPositive) builder.append('-');

        for (int i = digits.size() - 1; i >= 0; i--)
            builder.append(digits.get(i));
    }
}
