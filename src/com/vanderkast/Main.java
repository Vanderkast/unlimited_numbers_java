package com.vanderkast;

import com.vanderkast.lib.ByteUnlimitedNumber;
import com.vanderkast.lib.UnlimitedNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String n = "5904040";
        Byte[] num = {5, 6, 7, 7, 23};
        List<Byte> bytes = new ArrayList<>(Arrays.asList(num));
        UnlimitedNumber number = new ByteUnlimitedNumber(bytes, false);
        System.out.println(number.toString());
    }
}
