package com.example.slfx;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Tile {
    private double x;
    private double y;
    public Tile(double a,double b){
        this.x=a;
        this.y=b;
    }
    public double getlocX(){
        return this.x;
    }
    public double getlocY(){
        return this.y;
    }
}
class normal{
    Tile curr;
    Tile dest;
    Tile next;
    public normal(Tile nextile,Tile destination,Tile current){
        this.curr=current;
        this.dest=destination;
        this.next=nextile;
    }
    public Tile movefinal(Player p){
        return dest;
    }
}
class Snake extends normal {
    public Snake(Tile nextile, Tile destination, Tile current) {
        super(nextile, destination, current);
    }
}
class ladder extends normal{
    public ladder(Tile nextile,Tile destination,Tile current){
        super(nextile,destination,current);
    }
}
