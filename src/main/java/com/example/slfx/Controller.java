package com.example.slfx;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.effect.Glow;

import java.util.ArrayList;

public class Controller {
    Player p1 =new Player();
    Player p2 =new Player();
    Glow glow = new Glow();
    TranslateTransition translate;
    static ArrayList<Tile> board =new ArrayList<Tile>();
    static ArrayList<normal> game=new ArrayList<normal>();
    @FXML
    ImageView Playeturn;
    @FXML
    Button eee;
    @FXML
    ImageView winner;
    @FXML
    Button bt;
    @FXML
    ImageView Playerone;
    @FXML
    ImageView Playertwo;
    @FXML
    ImageView Arrow;
    Image oooo = new Image(getClass().getResourceAsStream("dice1.png"));
    Image two = new Image(getClass().getResourceAsStream("dice2.png"));
    Image three = new Image(getClass().getResourceAsStream("dice3.png"));
    Image four = new Image(getClass().getResourceAsStream("dice4.png"));
    Image five = new Image(getClass().getResourceAsStream("dice5.png"));
    Image six = new Image(getClass().getResourceAsStream("dice6.png"));
    Image pone = new Image(getClass().getResourceAsStream("blue_token.png"));
    Image ptwo = new Image(getClass().getResourceAsStream("green_token.png"));
    Image winone=new Image(getClass().getResourceAsStream("p1win.png"));
    Image wintwo=new Image(getClass().getResourceAsStream("p2win.png"));
    @FXML
    Button rolldice;
    @FXML
    ImageView img;
    public void rolling(ActionEvent e) throws InterruptedException {
        Dice d=new Dice();
        int rr=d.getnumber();
        rolldice.setDisable(true);
        Thread thread = new Thread(){
            public void run(){
                try {
                    Arrow.setVisible(false);
                    for (int i = 0; i < 15; i++) {
                        rollsetter(d.getnumber());
                        Thread.sleep(100);
                    }
                    rolldice.setDisable(false);
                    rollsetter(rr);
                    Arrow.setVisible(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        System.out.println("DICE ROLL" + rr);
        Thread mov= new Thread(){
            public void run(){
                try {
                    Thread.sleep(2000);
                    principlemover(rr);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        mov.start();
        if(checkforwin(p1)){
            winner.setPreserveRatio(false);
            winner.setImage(winone);
            rolldice.setDisable(true);
            rolldice.setVisible(false);
            Arrow.setVisible(false);
        }
        else if (checkforwin(p2)){
            winner.setPreserveRatio(false);
            winner.setImage(wintwo);
            rolldice.setVisible(false);
            rolldice.setDisable(true);
            Arrow.setVisible(false);
        }
    }
    private void principlemover(int diceoutput){
        if (p1.getturn()){
            if (p1.getstart()){
                if(p1.getpos()+diceoutput<=99){
                    p1.setmove(p1.getpos()+diceoutput);
                    System.out.println("P1 to move to :"+ p1.getmove());
                    Thread sbys=new Thread(){
                        public void run(){
                            try {
                                for (int i=p1.getpos();i<=p1.getmove();i++){
                                    Tile tt = board.get(i);
                                    p1.settile(tt);
                                    p1.setpos(tilecatcher(p1.gettile()));
                                    moveone(p1.gettile().getlocX(),p1.gettile().getlocY());
                                    Thread.sleep(1000);
                                }
                                int temp=gamecatcher(p1.gettile());
                                p1.settile(game.get(temp).movefinal(p1));
                                p1.setpos(tilecatcher(p1.gettile()));
                                moveone(p1.gettile().getlocX(),p1.gettile().getlocY());
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    sbys.start();
                    p1.setturn(false);
                    Playeturn.setImage(ptwo);
                    p2.setturn(true);
                }
                else{
                    p1.setturn(false);
                    Playeturn.setImage(ptwo);
                    p2.setturn(true);
                }
            }
            else{
                if(diceoutput==1){
                    p1.setstart();
                    p1.setmove(p1.getpos()+diceoutput);
                    System.out.println("P1 to move to :"+ p1.getmove());
                    Thread temp =new Thread(){
                        public void run(){
                            try {
                                Tile tt = board.get(p1.getmove());
                                p1.settile(tt);
                                p1.setpos(tilecatcher(p1.gettile()));
                                moveone(p1.gettile().getlocX(),p1.gettile().getlocY());
                                Thread.sleep(1000);
                                int temp=gamecatcher(p1.gettile());
                                p1.settile(game.get(temp).movefinal(p1));
                                p1.setpos(tilecatcher(p1.gettile()));
                                moveone(p1.gettile().getlocX(),p1.gettile().getlocY());
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    temp.start();
                    p1.setturn(false);
                    Playeturn.setImage(ptwo);
                    p2.setturn(true);
                }
                else{
                    p1.setturn(false);
                    Playeturn.setImage(ptwo);
                    p2.setturn(true);
                }
            }
        }
        else{
            if (p2.getstart()){
                if(p2.getpos()+diceoutput<=99){
                    p2.setmove(p2.getpos()+diceoutput);
                    Thread temp =new Thread(){
                        public void run(){
                            try {
                                for (int i=p2.getpos();i<=p2.getmove();i++){
                                    Tile tt = board.get(i);
                                    p2.settile(tt);
                                    p2.setpos(tilecatcher(p2.gettile()));
                                    movetwo(p2.gettile().getlocX(),p2.gettile().getlocY());
                                    Thread.sleep(1000);
                                }
                                int temp=gamecatcher(p2.gettile());
                                p2.settile(game.get(temp).movefinal(p2));
                                p2.setpos(tilecatcher(p2.gettile()));
                                movetwo(p2.gettile().getlocX(),p2.gettile().getlocY());
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    temp.start();
                    p2.setturn(false);
                    Playeturn.setImage(pone);
                    p1.setturn(true);
                }
                else{
                    p2.setturn(false);
                    Playeturn.setImage(pone);
                    p1.setturn(true);
                }
            }
            else{
                if(diceoutput==1){
                    p2.setstart();
                    p2.setmove(p2.getpos()+diceoutput);
                    System.out.println("P2 to move to :"+ p2.getmove());
                    Thread temp =new Thread(){
                        public void run(){
                            try {
                                Tile tt = board.get(p2.getmove());
                                p2.settile(tt);
                                p2.setpos(tilecatcher(p2.gettile()));
                                movetwo(p2.gettile().getlocX(),p2.gettile().getlocY());
                                Thread.sleep(1000);
                                int temp=gamecatcher(p2.gettile());
                                p2.settile(game.get(temp).movefinal(p2));
                                p2.setpos(tilecatcher(p2.gettile()));
                                movetwo(p2.gettile().getlocX(),p2.gettile().getlocY());
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    temp.start();
                    p2.setturn(false);
                    Playeturn.setImage(pone);
                    p1.setturn(true);
                }
                else{
                    p2.setturn(false);
                    Playeturn.setImage(pone);
                    p1.setturn(true);
                }
            }
        }
    }
    private void rollsetter(int d){
        if (d==1){
            img.setImage(oooo);
        }
        else if (d==2){
            img.setImage(two);
        }
        else if (d==3){
            img.setImage(three);
        }
        else if (d==4){
            img.setImage(four);
        }
        else if (d==5){
            img.setImage(five);
        }
        else if (d==6){
            img.setImage(six);
        }
    }
    public void start(ActionEvent ez){
        p1.setturn(true);
        rolldice.setGraphic(img);
        arrowtranslate();
        board =new ArrayList<Tile>();
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(i%2==0){
                board.add(new Tile(50*j+15,-100-50*i));
            }
                else{
                    board.add(new Tile(460-50*j,-100-50*i));
                }
            }
        }
        initialize();
        System.out.println("Board LOADED");
    }
    private void initialize(){
        game=new ArrayList<normal>();
        for(int i=0;i<board.size();i++){            //ladders ---8 followed by snake --7
            if(i==0){
                game.add(new ladder(board.get(1),board.get(37),board.get(0)));
            }
            else if(i==3){
                game.add(new ladder(board.get(4),board.get(13),board.get(3)));
            }
            else if(i==7){
                game.add(new ladder(board.get(8),board.get(29),board.get(7)));
            }
            else if(i==20){
                game.add(new ladder(board.get(21),board.get(41),board.get(20)));
            }
            else if(i==27){
                game.add(new ladder(board.get(28),board.get(75),board.get(27)));
            }
            else if(i==49) {
                game.add(new ladder(board.get(50), board.get(66), board.get(49)));
            }
            else if(i==70){
                game.add(new ladder(board.get(71),board.get(91),board.get(70)));
            }
            else if(i==79){
                game.add(new ladder(board.get(80),board.get(98),board.get(79)));
            }
            else if(i==31){
                game.add(new Snake(board.get(32),board.get(9),board.get(31)));
            }
            else if(i==35){
                game.add(new Snake(board.get(36),board.get(5),board.get(35)));
            }
            else if(i==61){
                game.add(new Snake(board.get(62),board.get(17),board.get(61)));
            }
            else if(i==87){
                game.add(new Snake(board.get(88),board.get(23),board.get(87)));
            }
            else if(i==94){
                game.add(new Snake(board.get(95),board.get(55),board.get(94)));
            }
            else if(i==96){
                game.add(new Snake(board.get(97),board.get(77),board.get(96)));
            }
            else if(i==47){
                game.add(new Snake(board.get(48),board.get(25),board.get(47)));
            }
            else if(i==99){
                game.add(new normal(board.get(99),board.get(99),board.get(99)));
            }
            else{
                game.add(new normal(board.get(i+1),board.get(i),board.get(i)));
            }
        }
    }
    private void moveone(double x,double y){
        translate=new TranslateTransition();
        translate.setNode(Playerone);
        translate.setToX(x);
        translate.setToY(y);
        translate.play();
    }
    private void movetwo(double x,double y){
        translate=new TranslateTransition();
        translate.setNode(Playertwo);
        translate.setToX(x);
        translate.setToY(y);
        translate.play();
    }
    private boolean checkforwin(Player p){
        if(p.gettile()==board.get(99)){
            System.out.println("GAME OVER");
            return true;
        }
        return false;
    }
    private int tilecatcher(Tile t){
        for(int i=0;i<board.size();i++){
            if (board.get(i)==t){
                return i;
            }
        }
        return 0;
    }
    private int gamecatcher(Tile t){
        for(int i=0;i<game.size();i++){
            if (game.get(i).curr==t){
                return i;
            }
        }
        return 0;
    }
    private void arrowtranslate(){
        glow.setLevel(0.9);
        Arrow.setEffect(glow);
        translate =new TranslateTransition();
        translate.setNode(Arrow);
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setAutoReverse(true);
        translate.setToX(10);
        translate.play();
    }
    public void exit(ActionEvent e){
        Platform.exit();
    }
}

