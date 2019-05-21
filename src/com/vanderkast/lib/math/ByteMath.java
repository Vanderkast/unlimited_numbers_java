package com.vanderkast.lib.math;

import com.vanderkast.lib.VectorExtension;
import com.vanderkast.lib.logic.ByteLogic;
import com.vanderkast.lib.logic.Logic;
import com.vanderkast.lib.number.ByteUnlimitedNumber;
import com.vanderkast.lib.number.UnlimitedNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteMath implements Math {
    @Override
    public UnlimitedNumber sum(UnlimitedNumber left, UnlimitedNumber right) {
        boolean leftAbsGreater = new ByteLogic().absGreater(left.reversedVector(), right.reversedVector());

        if (left.positive() && right.positive()) {
            return sumSignPositive(leftAbsGreater, left.reversedVector(), right.reversedVector());
        }

        if (left.positive() ^ right.positive())
            return sumSignXOR(leftAbsGreater, left, right);

        return sumSignNegative(leftAbsGreater, left.reversedVector(), right.reversedVector());
    }

    private UnlimitedNumber sumSignPositive(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), true, true);
        return new ByteUnlimitedNumber(byteSum(right, left), true, true);
    }

    private UnlimitedNumber sumSignXOR(boolean leftAbsGreater, UnlimitedNumber left, UnlimitedNumber right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteDif(left.reversedVector(), right.reversedVector()), left.positive(), true);

        return new ByteUnlimitedNumber(byteDif(right.reversedVector(), left.reversedVector()), right.positive(), true);
    }

    private UnlimitedNumber sumSignNegative(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), false, true);

        return new ByteUnlimitedNumber(byteSum(right, left), false, true);
    }

    @Override
    public UnlimitedNumber difference(UnlimitedNumber left, UnlimitedNumber right) {
        boolean leftAbsGreater = new ByteLogic().absGreater(left.reversedVector(), right.reversedVector());

        if (left.positive() && right.positive())
            return difSignPositive(leftAbsGreater, left.reversedVector(), right.reversedVector());

        if (left.positive() ^ right.positive())
            return difSignXOR(leftAbsGreater, left, right);

        return difSignNegative(leftAbsGreater, left.reversedVector(), right.reversedVector());
    }

    private UnlimitedNumber difSignPositive(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteDif(left, right), true, true);
        return new ByteUnlimitedNumber(byteDif(right, left), false, true);
    }

    private UnlimitedNumber difSignXOR(boolean leftAbsGreater, UnlimitedNumber left, UnlimitedNumber right) {
        if (left.positive())
            return difSignXORLeftPositive(leftAbsGreater, left.reversedVector(), right.reversedVector());
        return difSignXORLeftNegative(leftAbsGreater, left.reversedVector(), right.reversedVector());
    }

    private UnlimitedNumber difSignXORLeftPositive(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), true, true);
        return new ByteUnlimitedNumber(byteSum(right, left), true, true);
    }

    private UnlimitedNumber difSignXORLeftNegative(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), false, true);
        return new ByteUnlimitedNumber(byteSum(right, left), false, true);
    }

    private UnlimitedNumber difSignNegative(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteDif(left, right), false, true);
        return new ByteUnlimitedNumber(byteDif(right, left), false, true);
    }

    private Byte[] byteSum(List<Byte> bigger, List<Byte> smaller) {
        int resultLength = bigger.size() + 1;
        Byte[] result = new Byte[resultLength];

        result[0] = (byte) 0;
        for (int i = 0; i < bigger.size(); i++) {
            byte box = (byte) (result[i] + bigger.get(i));

            box += (i < smaller.size() ? smaller.get(i) : (byte) 0);

            if (box > 9) {
                result[i + 1] = (byte) 1;
                result[i] = (byte) (box % 10);
            } else {
                result[i + 1] = (byte) 0;
                result[i] = box;
            }
        }

        return VectorExtension.trimmedBack(result);
    }

    private Byte[] byteDif(List<Byte> bigger, List<Byte> smaller) {
        int resultLength = bigger.size() + 1;
        Byte[] result = new Byte[resultLength];

        result[0] = (byte) 0;
        for (int i = 0; i < bigger.size(); i++) {
            byte box = (byte) (result[i] + bigger.get(i));

            box -= (i < smaller.size() ? smaller.get(i) : (byte) 0);

            if (box < 0) {
                result[i + 1] = (byte) -1;
                result[i] = (byte) (box + 10);
            } else {
                result[i + 1] = (byte) 0;
                result[i] = box;
            }
        }

        return VectorExtension.trimmedBack(result);
    }

    @Override
    public UnlimitedNumber composition(UnlimitedNumber left, UnlimitedNumber right) {
        if(isZero(left) || isZero(right))
            return new ByteUnlimitedNumber("0");

        int additionalZeros = 0;
        while (right.reversedVector().size() > additionalZeros && right.reversedVector().get(additionalZeros) == 0) additionalZeros++;

        boolean resultPositive = left.positive() == right.positive();

        List<Byte> result = sumOfScales(copyWithShift(left.reversedVector(), additionalZeros), right.reversedVector());

        return new ByteUnlimitedNumber(VectorExtension.reversed(result), resultPositive);
    }

    private boolean isZero(UnlimitedNumber number){
        return number.vector().size() == 1 && number.vector().get(0) == 0;
    }

    private List<Byte> copyWithShift(List<Byte> vector, int zeros) {
        List<Byte> mirror = new ArrayList<>(vector);
        for (int i = 0; i < zeros; i++)
            mirror.set(i, (byte) 0);
        return mirror;
    }

    private List<Byte> sumOfScales(List<Byte> mirror, List<Byte> right) {
        List<Byte> box, result = new ArrayList<>();
        for (int i = 0; i < right.size(); i++) {
            box = scaleAndShift(mirror, right.get(i), i);

            if (box.size() == 1 && box.get(0) == 0) continue;

            result = Arrays.asList(byteSum(box, result));
        }
        return result;
    }

    private List<Byte> scaleAndShift(List<Byte> vector, Byte scale, int shiftLength) {
        if (scale == 0) return Arrays.asList((new Byte[]{0}));

        List<Byte> result = new ArrayList<>();

        for (int i = 0; i < shiftLength; i++)
            result.add((byte) 0);

        result.addAll(Arrays.asList(scale(vector, scale)));
        return result;
    }

    private Byte[] scale(List<Byte> vector, Byte scale) {
        Byte[] result = new Byte[vector.size() + 1];
        result[0] = (byte) 0;

        for (int i = 0; i < vector.size(); i++) {
            byte box = (byte) (result[i] + vector.get(i) * scale);

            if (box > 9) {
                result[i] = (byte) (box % 10);
                result[i + 1] = (byte) (box / 10);
            } else {
                result[i + 1] = (byte) 0;
                result[i] = box;
            }
        }

        return result;
    }

    @Override
    public UnlimitedNumber division(UnlimitedNumber left, UnlimitedNumber right) {
        if(isZero(left))
            return new ByteUnlimitedNumber("0");
        if(isZero(right))
            throw new IllegalArgumentException("Dividing by zero is illegal.");

        ByteLogic logic = new ByteLogic();
        if (logic.absGreater(right.reversedVector(), left.reversedVector()))
            return new ByteUnlimitedNumber("0");

        if(logic.equal(right, left))
            return new ByteUnlimitedNumber("1");

        boolean resultPositive = left.positive() == right.positive();

        return new ByteUnlimitedNumber(
                (calculateDivision(absolute(right), absolute(right), absolute(left)).vector()),
                resultPositive, true);
    }

    private UnlimitedNumber calculateDivision(UnlimitedNumber counter, UnlimitedNumber divider, UnlimitedNumber limit) {
        UnlimitedNumber step = new ByteUnlimitedNumber("0");
        UnlimitedNumber one = new ByteUnlimitedNumber("1");

        Logic logic = new ByteLogic();
        while (logic.lowerOrEqual(counter, limit)) {
            counter = sum(counter, divider);
            step = sum(step, one);
        }
        return step;
    }

    @Override
    public UnlimitedNumber modular(UnlimitedNumber left, UnlimitedNumber right) {
        Logic logic = new ByteLogic();
        UnlimitedNumber absRight = absolute(right);
        UnlimitedNumber absLeft = absolute(left);
        if(logic.lower(absLeft, absRight))
            return left;
        if(logic.equal(absLeft, absRight))
            return new ByteUnlimitedNumber("0");
        if(isZero(right))
            throw new IllegalArgumentException("Dividing by zero is illegal.");

        UnlimitedNumber box = absolute(right);
        while(logic.lower(box, absLeft)){
            box = sum(box, absRight);
        }
        return new ByteUnlimitedNumber(difference(absLeft, difference(box, absRight)).vector(), left.positive());
    }

    @Override
    public UnlimitedNumber absolute(UnlimitedNumber number) {
        return new ByteUnlimitedNumber(number.reversedVector(), true, true);
    }
}
