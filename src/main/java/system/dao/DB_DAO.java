package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.Model;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DB_DAO implements ISlovService {

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
    public List<String> all(Model model) {
        List<String> list;
        List<Model> slovarModels;
        CriteriaBuilder cb =currentSession().getCriteriaBuilder();
        CriteriaQuery<Model> query=cb.createQuery(Model.class);
        Root<Model> root=query.from(Model.class);
        slovarModels= currentSession().createQuery(query.where(cb.like(root.<String>get("name"),"%"+model.getName()+"%"))).getResultList();
        return Arrays.asList(slovarModels.toString());
    }

    @Override
    @Transactional
    public void update(Model model) {
        List<String> list=serch(model);
        Model slovarModel=new Model(model.getKey(),model.getValue());
        slovarModel.setId(Integer.parseInt(list.get(0)));
        slovarModel.setname(model.getName());
        Session session=sessionFactory.getCurrentSession();
        session.clear();
        session.update(slovarModel);
    }
    @Override
    @Transactional
    public void delete(Model model) {
        List<String> list=serch(model);
        model.setId(Integer.parseInt(list.get(0)));
        model.setValue(list.get(2));
        model.setname(list.get(3));
        currentSession().clear();
        currentSession().delete(model);
    }
    @Override
    public List<String> serch(Model model) {
        Session session=currentSession();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<Model> query=cb.createQuery(Model.class);
        Root<Model> root=query.from(Model.class);
        Model slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), model.getKey()))).getResultList().get(0);
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue(),slovarModel.getName()));
    }
    @Override
    @Transactional
    public void add(Model model) {
        Session session=sessionFactory.getCurrentSession();
        session.persist(new Model(model.getKey(),model.getValue(),model.getName()));
    }
}