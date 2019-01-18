package View;

//import Controller.ISlovService;
import Controller.*;
import Model.SlovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PropertyResourceBundle;

@Component
public class View {

    @Autowired
    @Qualifier("fileControl")
    ISlovService serviceFl;

    @Autowired
    @Qualifier("dbControl")
    ISlovService serviceDb;


    private BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    private List<SlovarModel> slovars;
    private List<ISlovService> controls=Arrays.asList(serviceFl,serviceDb);
    SlovService service;
    public View(List<SlovarModel> s, SlovService ss){
        service=ss;
        slovars= s;
    }
    public View(){
    }

    public void start() {
        PropertyResourceBundle resBun= null;
        try {
            resBun = new PropertyResourceBundle(new FileReader("src\\main\\resources\\res.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String key="";
        SlovarModel obj = null;
        ISlovService control = null;
        try {
            System.out.println(resBun.getString(CONST.menu));

            while (!(key=read.readLine()).equals("z")) {
                switch (key){

                    case "0":
                        System.out.println(resBun.getString(CONST.SelectControl));
                        while ((key=read.readLine()).matches("^\\d+$")) {
                            if(slovars.size()>Integer.parseInt(key)){
                                control=controls.get(Integer.parseInt(key));
                            }else{
                                System.out.println(resBun.getString(CONST.NoIndexSlovArr));
                            }
                            break;
                        }
                        break;

                    case "1":
                        System.out.println(resBun.getString(CONST.Slov1));
                        while ((key=read.readLine()).matches("^\\d+$")) {
                            if(slovars.size()>Integer.parseInt(key)){
                                obj=slovars.get(Integer.parseInt(key));
                            }else{
                                System.out.println(resBun.getString(CONST.NoIndexSlovArr));
                            }
                            break;
                        }
                        break;


                    case "2":
                        if(obj!=null){
                            System.out.println(service.Show(control));
                        }
                        else{
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        }
                        break;


                    case "3":
                        String Key,Value;
                        System.out.println(resBun.getString(CONST.WriteKey));
                        Key=read.readLine();
                        System.out.println(resBun.getString(CONST.WriteValue));
                        Value=read.readLine();
                        try {
                            service.Add(obj,control,Key,Value);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());//resBun.getString(CONST.ErrorKey));
                        }
//                        catch (Exception e) {
//                            System.out.println(resBun.getString(CONST.NotUniqKey));
//                        }
                        break;


                    case "4":
                        System.out.println(resBun.getString(CONST.WriteKey));
                        Key=read.readLine();
                        try {
                            service.Delete(obj,control,Key);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());//resBun.getString(CONST.ErrorKey));
                        }
                        break;


                    case "5":
                        System.out.println(resBun.getString(CONST.WriteKey));
                        Key=read.readLine();
                        try {
                            System.out.println(service.Serch(obj,control,Key));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());//System.out.println(resBun.getString(CONST.ErrorKey));
                        }
                        break;


                    case "6":
                        System.out.println(resBun.getString(CONST.WriteKey));
                        Key=read.readLine();
                        System.out.println(resBun.getString(CONST.WriteValue));
                        Value=read.readLine();
                        try {
                            service.Update(obj,control,Key,Value);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());//System.out.println(resBun.getString(CONST.ErrorKey));
                        }
                        break;
                }
                System.out.println(resBun.getString(CONST.menu));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
