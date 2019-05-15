package com.vanderkast.lib.math;

import com.vanderkast.lib.VectorExtension;
import com.vanderkast.lib.logic.ByteLogic;
import com.vanderkast.lib.number.ByteUnlimitedNumber;
import com.vanderkast.lib.number.UnlimitedNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteMath implements Math {
    @Override
    public UnlimitedNumber sum(UnlimitedNumber left, UnlimitedNumber right) {
        boolean leftAbsGreater = new ByteLogic().absGreater(left.vector(), right.vector());

        if (left.positive() && right.positive()) {
            return sumSignPositive(leftAbsGreater, left.vector(), right.vector());
        }

        if (left.positive() ^ right.positive())
            return sumSignXOR(leftAbsGreater, left, right);

        return sumSignNegative(leftAbsGreater, left.vector(), right.vector());
    }

    private UnlimitedNumber sumSignPositive(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), true, true);
        return new ByteUnlimitedNumber(byteSum(right, left), true, true);
    }

    private UnlimitedNumber sumSignXOR(boolean leftAbsGreater, UnlimitedNumber left, UnlimitedNumber right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteDif(left.vector(), right.vector()), left.positive(), true);

        return new ByteUnlimitedNumber(byteDif(right.vector(), left.vector()), right.positive(), true);
    }

    private UnlimitedNumber sumSignNegative(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteSum(left, right), false, true);

        return new ByteUnlimitedNumber(byteSum(right, left), false, true);
    }

    @Override
    public UnlimitedNumber difference(UnlimitedNumber left, UnlimitedNumber right) {
        boolean leftAbsGreater = new ByteLogic().absGreater(left.vector(), right.vector());

        if (left.positive() && right.positive())
            return difSignPositive(leftAbsGreater, left.vector(), right.vector());

        if (left.positive() ^ right.positive())
            return difSignXOR(leftAbsGreater, left, right);

        return difSignNegative(leftAbsGreater, left.vector(), right.vector());
    }

    private UnlimitedNumber difSignPositive(boolean leftAbsGreater, List<Byte> left, List<Byte> right) {
        if (leftAbsGreater)
            return new ByteUnlimitedNumber(byteDif(left, right), true, true);
        return new ByteUnlimitedNumber(byteDif(right, left), false, true);
    }

    private UnlimitedNumber difSignXOR(boolean leftAbsGreater, UnlimitedNumber left, UnlimitedNumber right) {
        if (left.positive())
            return difSignXORLeftPositive(leftAbsGreater, left.vector(), right.vector());
        return difSignXORLeftNegative(leftAbsGreater, left.vector(), right.vector());
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
        int additionalZeros = 0;
        while (right.vector().get(additionalZeros) == 0) additionalZeros++;

        boolean resultPositive = left.positive() == right.positive();

        List<Byte> result = sumOfScales(copyWithShift(left.vector(), additionalZeros), right.vector());

        return new ByteUnlimitedNumber(VectorExtension.reversed(result), resultPositive);
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
        return null;
    }

    @Override
    public UnlimitedNumber modular(UnlimitedNumber left, UnlimitedNumber right) {
        return null;
    }
}
