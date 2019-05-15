package com.vanderkast;

import com.vanderkast.lib.math.ByteMath;
import com.vanderkast.lib.math.Math;
import com.vanderkast.lib.number.ByteUnlimitedNumber;
import com.vanderkast.lib.number.UnlimitedNumber;

public class Main {

    public static void main(String[] args) {
        UnlimitedNumber a = new ByteUnlimitedNumber("-90100");
        UnlimitedNumber b = new ByteUnlimitedNumber("-1010");
        Byte[] l = new Byte[]{0,0,5,3,3,2,2,1,1,1,1,9,1,1, 0 , 0};
        System.out.println(new ByteUnlimitedNumber(l, true, false).vector());
        System.out.println(a.vector());
        System.out.println(b.vector());
        Math math = new ByteMath();
        System.out.println(math.sum(a, b).toString());
        System.out.println(math.difference(a, b).toString());
        System.out.println(math.composition(a, b).toString());
    }
}
