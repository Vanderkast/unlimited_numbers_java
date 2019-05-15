package com.vanderkast.lib.math;

import com.vanderkast.lib.number.UnlimitedNumber;

public interface Math {
    UnlimitedNumber sum(UnlimitedNumber left, UnlimitedNumber right);

    UnlimitedNumber difference(UnlimitedNumber left, UnlimitedNumber right);

    UnlimitedNumber composition(UnlimitedNumber left, UnlimitedNumber right);

    UnlimitedNumber division(UnlimitedNumber left, UnlimitedNumber right);

    UnlimitedNumber modular(UnlimitedNumber left, UnlimitedNumber right);
}
