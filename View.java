import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    private BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    private ISlovar sl;
    private ISlovar sl1;
    private File file1;
    private File file2;
    private Check check;
    ISlovService service;
    public View(Check ch, ISlovar s, ISlovar s1, ISlovService ss){
        service=ss;
        sl= s;
        sl1=s1;
        check=ch;
        file1=sl.getFile();
        file2=sl1.getFile();
    }

    public void main() {

        ResourceBundle resBun= ResourceBundle.getBundle("res", Locale.getDefault());

        String key="";
        ISlovar obj=null;
        try {
            System.out.println(resBun.getString("Menu"));

            while (!(key=read.readLine()).equals("z")) {
                switch (key){
                    case "1":
                        System.out.println(resBun.getString("Slov1"));
                        System.out.println(resBun.getString("Slov2"));
                        boolean flag=true;
                        while (flag&&!(key=read.readLine()).equals("z")) {
                            switch (key){
                                case "1":
                                    obj=sl;
                                    flag=false;
                                    break;
                                case "2":
                                    obj=sl1;
                                    flag=false;
                                    break;
                                default:
                                    System.out.println(resBun.getString("1or2"));
                                    break;
                            }
                        }
                        break;
                    case "2":
                        if(obj!=null){
                            service.Show(obj);
                        }
                        else{
                            System.out.println(resBun.getString("SelectSlov"));
                        }
                        break;
                    case "3":
                        String Key,Value;
                        System.out.println(resBun.getString("WriteKey"));
                        Key=read.readLine();
                        System.out.println(resBun.getString("WriteValue"));
                        Value=read.readLine();
                        if(check.validKey(obj,Key)&&check.UnikalnKey(obj,Key)){
                            service.Add(obj,Key,Value);
                        }
                        else{
                            System.out.println(resBun.getString("ErrorKey"));
                        }
                        break;
                    case "4":
                        System.out.println(resBun.getString("WriteKey"));
                        Key=read.readLine();
                        service.Delete(obj,Key);
                        break;
                    case "5":
                        System.out.println(resBun.getString("WriteKey"));
                        Key=read.readLine();
                        System.out.println(service.Serch(obj,Key));
                        break;
                }
                System.out.println(resBun.getString("Menu"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
