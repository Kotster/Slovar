import Controller.ISlovService;
import Controller.SlovarModel;
import Model.SlovService;
import View.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx=new FileSystemXmlApplicationContext("config.xml");

        SlovarModel slovar1=(SlovarModel) ctx.getBean("slovarOne");//new Slovar(new File("slovar4Lat"),4,"^[a-zA-Z]+$");
        SlovarModel slovar2=(SlovarModel)ctx.getBean("slovarTwo");//new Slovar(new File("slovar5Num"),5,"^[0-9]+$");
        ArrayList<SlovarModel> arrSlov=new ArrayList<SlovarModel>();

        arrSlov.add(slovar1);
        arrSlov.add(slovar2);


        SlovService service=new SlovService();
        View view=new View(arrSlov,service);
        view.start();
    }
}

