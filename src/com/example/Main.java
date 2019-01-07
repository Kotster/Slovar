package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.example.Controller.ISlovService;
import com.example.Controller.Slovar;
import com.example.Model.SlovService;
import com.example.View.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
		ApplicationContext ctx=new FileSystemXmlApplicationContext("config.xml");
		
        ISlovService slovar1=(ISlovService)ctx.getBean("slovarOne");//new Slovar(new File("slovar4Lat"),4,"^[a-zA-Z]+$");
        ISlovService slovar2=(ISlovService)ctx.getBean("slovarTwo");//new Slovar(new File("slovar5Num"),5,"^[0-9]+$");
        ArrayList<ISlovService> arrSlov=new ArrayList<>();

        arrSlov.add(slovar1);
        arrSlov.add(slovar2);


        SlovService service=new SlovService();
        View view=new View(arrSlov,service);
        view.start();
    }
}
