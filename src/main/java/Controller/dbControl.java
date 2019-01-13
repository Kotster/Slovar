package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class dbControl implements ISlovService{

    @Autowired
    private HibernateTemplate template;

    public void Show() {

    }

    public void Delete(String key) {

    }

    public String Serch(String Key) {

        return template.get(SlovarModel.class,Key);
    }

    public void Add(String Key, String Value) throws NotUniqException {
        SlovarModel slovarModel=new SlovarModel(Key,Value);
        template.saveOrUpdate(slovarModel);
    }

    public class NotUniqException extends Exception{
        public NotUniqException() {
            super("The key is not uniq");
        }
    }
}
