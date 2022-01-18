package com.example.slfx;

import javafx.scene.image.Image;

public class Player {
    private int currpos=-1;
    private int postomove=-1;
    private boolean myturn=false;
    private boolean start=false;
    public Tile currenttile;
    public void setturn(boolean exp){
        this.myturn=exp;
    }
    public void setstart(){
        this.start=true;
    }
    public boolean getturn(){
        return this.myturn;
    }
    public void setmove(int c){
        this.postomove=c;
    }
    public int getmove(){
        return this.postomove;
    }
    public boolean getstart(){
        return this.start;
    }
    public void settile(Tile t){
        this.currenttile=t;
    }
    public int getpos(){
        return this.currpos;
    }
    public void setpos(int xc){
        this.currpos=xc;
    }
    public Tile gettile(){
        return this.currenttile;
    }
}
