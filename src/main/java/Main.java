import system.View.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx=new FileSystemXmlApplicationContext("config.xml");
        View view=(View)ctx.getBean("view");
        view.start();
    }
}

