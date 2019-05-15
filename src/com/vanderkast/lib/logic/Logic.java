package com.vanderkast.lib.logic;

import com.vanderkast.lib.number.UnlimitedNumber;

public interface Logic {
    public boolean greater(UnlimitedNumber lift, UnlimitedNumber right);

    public boolean lower(UnlimitedNumber lift, UnlimitedNumber right);

    public boolean equal(UnlimitedNumber lift, UnlimitedNumber right);

    public boolean greaterOrEqual(UnlimitedNumber lift, UnlimitedNumber right);

    public boolean lowerOrEqual(UnlimitedNumber lift, UnlimitedNumber right);
}
