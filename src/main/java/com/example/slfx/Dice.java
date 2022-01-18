package com.example.slfx;

import java.util.Random;

public class Dice {
    Random r= new Random();
    public int getnumber(){
        return r.nextInt(6)+1;
    }
}
