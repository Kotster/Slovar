package Controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    public List<String> all(SlovarModel model) {
        List<String> list;
        List<SlovarModel> slovarModels;
        Session session=currentSession().getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        slovarModels= session.createQuery(query.where(cb.like(root.get("name"),"%"+model.getName()+"%"))).getResultList();
        transaction.commit();
        return Arrays.asList(slovarModels.toString());
    }

    @Override
    public void update(SlovarModel model) {
        List<String> list=serch(model);
        SlovarModel slovarModel=new SlovarModel(model.getKey(),model.getValue());
        slovarModel.setId(Integer.parseInt(list.get(0)));
        slovarModel.setname(model.getName());
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.update(slovarModel);
        transaction.commit();
        session.close();
    }
    @Override
    public void delete(SlovarModel model) {
        List<String> list=serch(model);
        SlovarModel slovarModel=new SlovarModel(list.get(1),list.get(2));
        slovarModel.setId(Integer.parseInt(list.get(0)));
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.delete(slovarModel);
        transaction.commit();
        session.close();
    }
    @Override
    public List<String> serch(SlovarModel model) {
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
        SlovarModel slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), model.getKey()))).getResultList().get(0);
        transaction.commit();
        session.close();
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue()));
    }
    @Override
    public void add(SlovarModel model) {
        Session session=currentSession();
        Transaction transaction=session.beginTransaction();
        session.save(new SlovarModel(model.getKey(),model.getValue(),model.getName()));
        transaction.commit();
        session.close();
    }
}

