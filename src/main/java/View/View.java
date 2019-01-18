package View;

import Controller.SlovarModel;
import Model.SlovService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PropertyResourceBundle;


public class View {
    private BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    private List<SlovarModel> slovars;
    SlovService service;
    public View(List<SlovarModel> s, SlovService ss){
        service=ss;
        slovars= s;
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
        try {
            System.out.println(resBun.getString(CONST.menu));

            while (!(key=read.readLine()).equals("z")) {
                switch (key){


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
                            System.out.println(service.Show());
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
                            service.Add(obj,Key,Value);
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
                            service.Delete(obj,Key);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());//resBun.getString(CONST.ErrorKey));
                        }
                        break;


                    case "5":
                        System.out.println(resBun.getString(CONST.WriteKey));
                        Key=read.readLine();
                        try {
                            System.out.println(service.Serch(obj,Key));
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
                            service.Update(obj,Key,Value);
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
