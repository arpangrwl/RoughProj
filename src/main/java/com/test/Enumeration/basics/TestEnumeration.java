package com.test.Enumeration.basics;

import static com.test.Enumeration.basics.TestEnumeration.itsName.first;
import static com.test.Enumeration.basics.TestEnumeration.itsName.sec;
import static com.test.Enumeration.basics.TestEnumeration.itsName.third;

/**
 * Created by Arpan on 4/3/17.
 */
public class TestEnumeration {
    public static void main(String[] args) {

        System.out.println(itsName.sec);

        func(first);
    }

    public static void func(itsName its)
    {
        switch(its){

            case first:
                System.out.println("first = " + first);
                break;

            case sec:
                System.out.println("sec = " + sec);
                break;

            case third:
                System.out.println("third = " + third);
                break;
        }

    }

    public enum itsName{
        first,
        sec,
        third
    }

    public enum see{

        one(1), two(2), three(3);

        private int var ;

        private see(int var){
            this.var = var;
        }
    }
}
