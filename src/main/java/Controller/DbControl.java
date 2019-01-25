package Controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
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
    @Transactional
    public List<String> all(SlovarModel model) {
        List<String> list;
        List<SlovarModel> slovarModels;
        CriteriaBuilder cb =currentSession().getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        slovarModels= currentSession().createQuery(query.where(cb.like(root.get("name"),"%"+model.getName()+"%"))).getResultList();
        return Arrays.asList(slovarModels.toString());
    }

    @Override
    @Transactional
    public void update(SlovarModel model) {
        List<String> list=serch(model);
        SlovarModel slovarModel=new SlovarModel(model.getKey(),model.getValue());
        slovarModel.setId(Integer.parseInt(list.get(0)));
        slovarModel.setname(model.getName());
        Session session=sessionFactory.getCurrentSession();
        session.clear();
        session.update(slovarModel);
    }
    @Override
    @Transactional
    public void delete(SlovarModel model) {
        List<String> list=serch(model);
        model.setId(Integer.parseInt(list.get(0)));
        model.setValue(list.get(2));
        model.setname(list.get(3));
        currentSession().clear();
        currentSession().delete(model);
    }
    @Override
    public List<String> serch(SlovarModel model) {
        Session session=currentSession();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), model.getKey()))).getResultList().get(0);
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue(),slovarModel.getName()));
    }
    @Override
    @Transactional
    public void add(SlovarModel model) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(new SlovarModel(model.getKey(),model.getValue(),model.getName()));
    }
}