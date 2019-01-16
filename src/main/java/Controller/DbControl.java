package Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DbControl implements ISlovService{

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void Show() {

    }

    public void Delete(String Key) {
        currentSession().delete(new SlovarModel(Serch(Key).get(0),Serch(Key).get(1)));
    }

    public List<String> Serch(String Key) {
//        Query query=currentSession().createSQLQuery("from SlovarModel as model where model.key= :keyTable");
//        query.setParameter("keyTable",Key);
//        return query.getResultList();
        Session session=currentSession();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), Key))).getResultList().get(0);
        return new ArrayList<String>(Arrays.asList(slovarModel.getKey(),slovarModel.getValue()));
    }

    public void Add(String Key, String Value) {
       currentSession().save(new SlovarModel(Key,Value));
    }
}
