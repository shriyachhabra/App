package com.example.shriyachhabra.myapplication.Modals;

public class Lexeme {
    private int row1;
    private int row2;
    private int row3;
    private char c;

    public Lexeme(int r1, int r2, int r3, char c){
        this.row1=r1;
        this.row2=r2;
        this.row3=r3;
        this.c=c;
    }

    public Lexeme(){

    }

    public int getRow1() {
        return row1;
    }

    public int getRow2() {
        return row2;
    }

    public int getRow3() {
        return row3;
    }

    public char getCharacter() {
        return c;
    }

    public void setRow1(int row1) {
        this.row1 = row1;
    }

    public void setRow2(int row2) {
        this.row2 = row2;
    }

    public void setRow3(int row3) {
        this.row3 = row3;
    }

    public void setC(char c) {
        this.c = c;
    }
}
