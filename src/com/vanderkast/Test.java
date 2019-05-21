package com.vanderkast;

import com.vanderkast.lib.logic.ByteLogic;
import com.vanderkast.lib.logic.Logic;
import com.vanderkast.lib.math.ByteMath;
import com.vanderkast.lib.math.Math;
import com.vanderkast.lib.number.ByteUnlimitedNumber;
import com.vanderkast.lib.number.UnlimitedNumber;

import java.util.Scanner;

/**
 * Created by mazurov_vk on 15.05.2019.
 */
public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstString = input.next();
        String secondString = input.next();

        UnlimitedNumber first = new ByteUnlimitedNumber(firstString);
        UnlimitedNumber second = new ByteUnlimitedNumber(secondString);

        show(firstString, first);
        show(secondString, second);

        Math math = new ByteMath();

        sum(math, first, second);
        dif(math, first, second);
        composition(math, first, second);
        div(math, first, second);
        mod(math, first, second);

        Logic logic = new ByteLogic();
        bigger(logic, first, second);
        lower(logic, first, second);
        equal(logic, first, second);
    }

    private static void show(String input, UnlimitedNumber number) {
        System.out.print(input + " number in vector:\t");
        System.out.println(number.vector());
    }

    private static void sum(Math math, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Sum of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(math.sum(left, right).toString());
    }

    private static void dif(Math math, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Difference of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(math.difference(left, right).toString());
    }

    private static void composition(Math math, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Composition of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(math.composition(left, right).toString());
    }

    private static void div(Math math, UnlimitedNumber left, UnlimitedNumber right) {
        try {
            System.out.print(left.toString() + " div " + right.toString() + ":\t");
            System.out.println(math.division(left, right).toString());
        } catch (IllegalArgumentException e){
            System.out.println(e.getClass().toString() + " was catched. Hmmm... might it be infinity??");
        }
    }

    private static void mod(Math math, UnlimitedNumber left, UnlimitedNumber right) {
        try {
            System.out.print(left.toString() + " mod " + right.toString() + ":\t");
            System.out.println(math.modular(left, right).toString());
        } catch (IllegalArgumentException e){
            System.out.println(e.getClass().toString() + " was catched. Hmmm... might it be infinity??");
        }
    }

    private static void bigger(Logic logic, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Greater test of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(logic.greater(left, right));
    }

    private static void lower(Logic logic, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Lower test of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(logic.lower(left, right));
    }

    private static void equal(Logic logic, UnlimitedNumber left, UnlimitedNumber right) {
        System.out.print("Equal test of " + left.toString() + " " + right.toString() + ":\t");
        System.out.println(logic.equal(left, right));
    }
}
