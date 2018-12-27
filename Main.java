package Slov;

import Controller.*;
import View.*;
import Model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BufferedReader read=new BufferedReader(new InputStreamReader(System.in));

        ISlovService slovar1=new Slovar(new File("slovar4Lat"),4,"^[a-zA-Z]+$");
        ISlovService slovar2=new Slovar(new File("slovar5Num"),5,"^[0-9]+$");
        ArrayList<ISlovService> arrSlov=new ArrayList<>();

        arrSlov.add(slovar1);
        arrSlov.add(slovar2);


        SlovService service=new SlovService();
        View view=new View(arrSlov,service);
        view.start();
    }
}