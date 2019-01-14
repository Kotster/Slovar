package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbControl implements ISlovService{

    @Autowired
    private HibernateTemplate template;

    public void Show() {

    }

    public void Delete(String key) {

    }

    public List<String> Serch(String Key) {
        return template.find("from Controller.SlovarModel dictionary where dictionary=?", Key);
    }

    public void Add(String Key, String Value) {
        SlovarModel slovarModel=new SlovarModel(Key,Value);
        template.saveOrUpdate(slovarModel);
    }
}
