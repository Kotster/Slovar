import Controller.DbControl;
import Controller.ISlovService;
import Controller.SlovarModel;
import Model.SlovService;
import View.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;

public class Main {

    @Autowired
    static View view;

    public static void main(String[] args) {
        ApplicationContext ctx=new FileSystemXmlApplicationContext("config.xml");

        SlovarModel dictionary1=(SlovarModel)ctx.getBean("dictionary1");
        SlovarModel dictionary2=(SlovarModel)ctx.getBean("dictionary2");
        ArrayList<SlovarModel> arrSlov=new ArrayList<>();

        arrSlov.add(dictionary1);
        arrSlov.add(dictionary2);

        SlovService service=new SlovService();

        view.start();
    }
}

