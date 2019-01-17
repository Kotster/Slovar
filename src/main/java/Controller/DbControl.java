package Controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.spi.PersistenceProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class DbControl implements ISlovService{

    @PersistenceContext
    EntityManager entityManager;
//    EntityManager currentEntityManager() {
//
//    }

    public void Show() {

    }

    public void Delete(String Key) {

        List<String> list=Serch(Key);
        SlovarModel slovarModel=new SlovarModel(list.get(1),list.get(2));
        slovarModel.setId(Integer.parseInt(list.get(0)));

        //EntityManager entityManager=currentEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(slovarModel) ;
        entityManager.getTransaction().commit();
        entityManager.close();
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
        //EntityManager entityManager=currentEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder cb =entityManager.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel=entityManager.createQuery(query.where(cb.equal(root.get("key"), Key))).getResultList().get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue()));
    }

    public void Add(String Key, String Value) {
        //EntityManager entityManager=currentEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(new SlovarModel(Key,Value)) ;
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

