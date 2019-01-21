package Controller;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypeTemplate;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Repository
@Transactional
public class DbControl implements ISlovService{

    @Autowired
    private SessionFactory sessionFactory;

    Session currentSession() {
        try {
            return sessionFactory.getCurrentSession();
        }
        catch (HibernateException e){
            return sessionFactory.openSession();
        }
    }

    public List<String> all() {
        List<String> list;
        List<SlovarModel> slovarModels;
        Session session=currentSession().getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        slovarModels= session.createQuery(query).getResultList();
        transaction.commit();
        return Arrays.asList(slovarModels.toString());
    }

    public void update(String Key, String Value) {
        List<String> list=serch(Key);
        SlovarModel slovarModel=new SlovarModel(Key,Value);
        slovarModel.setId(Integer.parseInt(list.get(0)));
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.update(slovarModel);
        transaction.commit();
        session.close();
    }

    public void delete(String Key) {
        List<String> list=serch(Key);
        SlovarModel slovarModel=new SlovarModel(list.get(1),list.get(2));
        slovarModel.setId(Integer.parseInt(list.get(0)));
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete(slovarModel);
        transaction.commit();
        session.close();
    }

    public List<String> serch(String Key) {
//        List<String> list;
//        Session session=currentSession();
////        Transaction transaction=session.beginTransaction();
//        Query query=session.createSQLQuery("select test.public.dictionar.key,test.public.dictionar.value from dictionar as d where d.key=:keyTable");
//        query.setParameter("",Key);
//        list=query.getResultList();
////        transaction.commit();
//        session.close();
//        System.out.println(list.get(0));
//        return list;
        Session session=currentSession().getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), Key))).getResultList().get(0);
        transaction.commit();
        session.close();
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue()));
    }

    public void add(String Key, String Value) {
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.save("dictionar2", new SlovarModel(Key,Value));
        transaction.commit();
        session.close();
    }
}

