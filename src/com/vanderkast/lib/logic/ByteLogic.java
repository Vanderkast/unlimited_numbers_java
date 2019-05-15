package com.vanderkast.lib.logic;

import com.vanderkast.lib.number.UnlimitedNumber;

import java.util.List;

public class ByteLogic implements Logic {
    @Override
    public boolean greater(UnlimitedNumber left, UnlimitedNumber right) {
        if (left.positive() ^ right.positive())
            return left.positive();
        if(left.vector().size() != right.vector().size())
            return left.vector().size() > right.vector().size() == left.positive();
        return absGreater(left.vector(), right.vector());
    }

    @Override
    public boolean lower(UnlimitedNumber left, UnlimitedNumber right) {
        if (left.positive() ^ right.positive())
            return right.positive();
        if(left.vector().size() != right.vector().size())
            return left.vector().size() > right.vector().size() != left.positive();
        return !absGreater(left.vector(), right.vector());
    }

    public boolean absGreater(List<Byte> a, List<Byte> b) {
        if(a.size() != b.size())
            return a.size() > b.size();

        int difOn = a.size() - 1;
        while (difOn > 0 && a.get(difOn).equals(b.get(difOn)))
            difOn--;

        return a.get(difOn) > b.get(difOn);
    }

    @Override
    public boolean equal(UnlimitedNumber left, UnlimitedNumber right) {
        if (left.positive() ^ right.positive())
            return false;
        if (left.vector().size() != right.vector().size())
            return false;

        return differenceOn(left.vector(), right.vector()) == -1;
    }

    private int differenceOn(List<Byte> a, List<Byte> b) {
        int diffPos = 0;
        while (diffPos < a.size() && a.get(diffPos).equals(b.get(diffPos)))
            diffPos++;

        return diffPos == a.size() ? -1 : diffPos;
    }

    @Override
    public boolean greaterOrEqual(UnlimitedNumber left, UnlimitedNumber right) {
        return !lower(left, right);
    }

    @Override
    public boolean lowerOrEqual(UnlimitedNumber left, UnlimitedNumber right) {
        return !greater(left, right);
    }
}
