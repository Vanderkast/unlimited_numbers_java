package com.vanderkast;

import com.vanderkast.lib.math.ByteMath;
import com.vanderkast.lib.math.Math;
import com.vanderkast.lib.number.ByteUnlimitedNumber;
import com.vanderkast.lib.number.UnlimitedNumber;

/**
 * Реализовать модуль реализующий целые числе без ограничения по длине
 * реализовать операции суммы, резности, произведения, целочисленного деления, взятие остатка от деления
 * логические операции сравнения
 */
public class Main {
    public static void main(String[] args) {
        UnlimitedNumber a = new ByteUnlimitedNumber("90550");
        UnlimitedNumber b = new ByteUnlimitedNumber("0");
        Byte[] l = new Byte[]{0,0,5,3,3,2,2,1,1,1,1,9,1,1,0,0};

        System.out.println(new ByteUnlimitedNumber(l, true, false).reversedVector());
        System.out.println(a.reversedVector());
        System.out.println(b.reversedVector());

        Math math = new ByteMath();

        System.out.println(math.sum(a, b).toString());
        System.out.println(math.difference(a, b).toString());
        System.out.println(math.composition(a, b).toString());

        System.out.println(new ByteUnlimitedNumber("0").isZero());
        System.out.println(new ByteUnlimitedNumber("0").reversedVector());

        System.out.println(math.division(new ByteUnlimitedNumber("10"), new ByteUnlimitedNumber("-3")).toString());
    }
}
