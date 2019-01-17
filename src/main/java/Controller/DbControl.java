package Controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<String> All() {
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

    public void Update(String Key, String Value) {
        List<String> list=Serch(Key);
        SlovarModel slovarModel=new SlovarModel(Key,Value);
        slovarModel.setId(Integer.parseInt(list.get(0)));
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.update(slovarModel);
        transaction.commit();
        session.close();
    }

    public void Delete(String Key) {
        List<String> list=Serch(Key);
        SlovarModel slovarModel=new SlovarModel(list.get(1),list.get(2));
        slovarModel.setId(Integer.parseInt(list.get(0)));
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete(slovarModel);
        transaction.commit();
        session.close();
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

    public void Add(String Key, String Value) {
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.save(new SlovarModel(Key,Value));
        transaction.commit();
        session.close();
    }
}

