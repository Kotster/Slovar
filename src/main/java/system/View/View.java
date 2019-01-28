package system.View;

import system.CONST;
import system.dao.ISlovService;
import system.model.Model;
import system.service.ModelService;
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
    @Qualifier("fileDAO")
    ISlovService serviceFl;

    @Autowired
    @Qualifier("DB_DAO")
    ISlovService serviceDb;

    @Autowired
    @Qualifier("dictionary1")
    Model model1;

    @Autowired
    @Qualifier("dictionary2")
    Model model2;

    @Autowired
    @Qualifier("service")
    ModelService service;



    private BufferedReader read=new BufferedReader(new InputStreamReader(System.in));
    public View(){
    }

    public void start() {
        List<Model> slovars=Arrays.asList(model1,model2);
        List<ISlovService> controls=Arrays.asList(serviceFl,serviceDb);
        PropertyResourceBundle resBun= null;
        try {
            resBun = new PropertyResourceBundle(new FileReader("src\\main\\resources\\res.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String key="";
        Model model = null;
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
                                model=slovars.get(Integer.parseInt(key));
                            }else{
                                System.out.println(resBun.getString(CONST.NoIndexSlovArr));
                            }
                            break;
                        }
                        break;


                    case "2":
                        if(model!=null&&control!=null){
                            System.out.println(service.Show(control, model));
                        }
                        else{
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        }
                        break;


                    case "3":
                        if(model!=null&&control!=null) {
                            String Key, Value;
                            System.out.println(resBun.getString(CONST.WriteKey));
                            model.setKey(read.readLine());
                            System.out.println(resBun.getString(CONST.WriteValue));
                            model.setValue(read.readLine());
                            try {
                                service.Add(model, control);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        break;


                    case "4":
                        if(model!=null&&control!=null) {
                            System.out.println(resBun.getString(CONST.WriteKey));
                            model.setKey(read.readLine());
                            try {
                                service.Delete(model, control);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        break;


                    case "5":
                        if(model!=null&&control!=null) {
                            System.out.println(resBun.getString(CONST.WriteKey));
                            model.setKey(read.readLine());
                            try {
                                System.out.println(service.Serch(model, control));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        break;


                    case "6":
                        if(model!=null&&control!=null) {
                            System.out.println(resBun.getString(CONST.WriteKey));
                            model.setKey(read.readLine());
                            System.out.println(resBun.getString(CONST.WriteValue));
                            model.setValue(read.readLine());
                            try {
                                service.Update(model, control);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else
                            System.out.println(resBun.getString(CONST.SelectSlov));
                        break;
                }
                System.out.println(resBun.getString(CONST.menu));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
