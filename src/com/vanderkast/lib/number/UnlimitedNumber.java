package com.vanderkast.lib.number;

import java.util.List;

public interface UnlimitedNumber {
    /**
     * @return true if number >= 0
     *              false if number < 0
     */
    boolean positive();

    /**
     * @return List of number's digits in REVERSE order
     */
    List<Byte> reversedVector();


    List<Byte> vector();
}
