package com.vanderkast;

import com.vanderkast.lib.BooleanDigit;
import com.vanderkast.lib.Digit;

public class Main {

    public static void main(String[] args) {
        int a = 8;
        Digit digit = new BooleanDigit(a);
        System.out.println(digit.asChar());
        System.out.println(a);
        System.out.println(digit.binary());
    }
}
