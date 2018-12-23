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
            System.out.println(new String(resBun.getString("Menu").getBytes("ISO8859-1"),"UTF-8"));

            while (!(key=read.readLine()).equals("z")) {
                switch (key){
                    case "1":
                        System.out.println(new String(resBun.getString("Slov1").getBytes("ISO8859-1"),"UTF-8"));
                        System.out.println(new String(resBun.getString("Slov2").getBytes("ISO8859-1"),"UTF-8"));
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
                                    System.out.println(new String(resBun.getString("1or2").getBytes("ISO8859-1"),"UTF-8"));
                                    break;
                            }
                        }
                        break;
                    case "2":
                        if(obj!=null){
                            service.Show(obj);
                        }
                        else{
                            System.out.println(new String(resBun.getString("SelectSlov").getBytes("ISO8859-1"),"UTF-8"));
                        }
                        break;
                    case "3":
                        String Key,Value;
                        System.out.println(new String(resBun.getString("WriteKey").getBytes("ISO8859-1"),"UTF-8"));
                        Key=read.readLine();
                        System.out.println(new String(resBun.getString("WriteValue").getBytes("ISO8859-1"),"UTF-8"));
                        Value=read.readLine();
                        if(check.validKey(obj,Key)&&check.UnikalnKey(obj,Key)){
                            service.Add(obj,Key,Value);
                        }
                        else{
                            System.out.println(new String(resBun.getString("ErrorKey").getBytes("ISO8859-1"),"UTF-8"));
                        }
                        break;
                    case "4":
                        System.out.println(new String(resBun.getString("WriteKey").getBytes("ISO8859-1"),"UTF-8"));
                        Key=read.readLine();
                        service.Delete(obj,Key);
                        break;
                    case "5":
                        System.out.println(new String(resBun.getString("WriteKey").getBytes("ISO8859-1"),"UTF-8"));
                        Key=read.readLine();
                        System.out.println(service.Serch(obj,Key));
                        break;
                }
                System.out.println(new String(resBun.getString("Menu").getBytes("ISO8859-1"),"UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
