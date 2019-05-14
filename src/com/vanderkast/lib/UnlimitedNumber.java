package com.vanderkast.lib;

import java.util.List;

public interface UnlimitedNumber {
    void plus(UnlimitedNumber number);

    void minus(UnlimitedNumber number);

    void composition(UnlimitedNumber number);

    void division(UnlimitedNumber number);

    void modular(UnlimitedNumber number);

    List<Byte> vector();
}
