package Controller;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DbControl implements ISlovService{

    @PersistenceContext
    private EntityManager session;

//    Session currentSession() {
//        try {
//            return sessionFactory.getCurrentSession();
//        }
//        catch (HibernateException e){
//            return sessionFactory.openSession();
//        }
//    }

    public void Show() {

    }

    public void Delete(String Key) {
        List<String> list=Serch(Key);
        SlovarModel slovarModel=new SlovarModel(list.get(0),list.get(1));
        //Session session=currentSession();
        //Transaction transaction=session.beginTransaction();
        session.remove(slovarModel);
       //transaction.commit();
        //session.close();
    }

    public List<String> Serch(String Key) {
//        List<String> list;
//        Session session=currentSession();
//        Transaction transaction=session.beginTransaction();
//        Query query=session.createSQLQuery("select key,value from dictionar where dictionar.key= :keyTable");
//        query.setParameter("keyTable",Key);
//        list=query.getResultList();
//        transaction.commit();
//        session.close();
//        System.out.println(list.get(0));
//        return list;
        //Session session=currentSession().getSessionFactory().openSession();
        //Transaction transaction=session.beginTransaction();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), Key))).getResultList().get(0);
        //transaction.commit();
        return new ArrayList<String>(Arrays.asList(slovarModel.getKey(),slovarModel.getValue()));
    }
    @Transactional
    public void Add(String Key, String Value) {
        //Session session=currentSession();
        //EntityTransaction transaction=session.getTransaction();
        //transaction.begin();
        session.persist(new SlovarModel(Key,Value));
        //transaction.commit();
        //session.close();
    }
}

