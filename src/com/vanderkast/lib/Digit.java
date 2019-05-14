package com.vanderkast.lib;

import java.util.List;

public interface Digit {
    /**
     * Adds passed digit to current object
     * example: 5 + 8 = 13 =>
     * current = 3, digit = 8, returned overflow = 1;
     * @return sum overflow ~ (current + digit) > 10 ? 1 : 0;
     */
    Digit plus(Digit digit);

    /**
     * minuses passed digit from current object
     * example 3 - 9 = -6 =>
     * current = 4, digit = 9, returned overflow = 1;
     * @return difference overflow ~ (current - digit) < 0 ? 1 : 0;
     */
    Digit minus(Digit digit);

    /**
     * example 5 ~ [true, false, true]
     *      8 ~ [false, false, false, true]
     * @return reversed binary representation of digit
     */
    List<Boolean> binary();

    /**
     * @return decimal representation of digit
     */
    int asInt();

    /**
     * @return decimal representation of digit BUT CHAR
     */
    char asChar();
}
