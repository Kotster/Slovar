package system.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import system.model.ModelDictionary;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
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
    public List<ModelDictionary> all(ModelDictionary model) {
        List<String> list;
        List<ModelDictionary> slovarModels;
        CriteriaBuilder cb =currentSession().getCriteriaBuilder();
        CriteriaQuery<ModelDictionary> query=cb.createQuery(ModelDictionary.class);
        Root<ModelDictionary> root=query.from(ModelDictionary.class);
        slovarModels= currentSession().createQuery(query.where(cb.like(root.<String>get("name"),"%"+model.getName()+"%"))).getResultList();
        return slovarModels;
    }

    @Override
    @Transactional
    public void update(ModelDictionary model) {
        List<ModelDictionary> list=serch(model);
        ModelDictionary slovarModel=new ModelDictionary(model.getKey(),model.getValue());
        slovarModel.setId(list.get(0).getId());
        slovarModel.setname(list.get(0).getName());
        Session session=sessionFactory.getCurrentSession();
        session.clear();
        session.update(slovarModel);
    }
    @Override
    @Transactional
    public void delete(ModelDictionary model) {
        List<ModelDictionary> list=serch(model);
        model.setId(list.get(0).getId());
        model.setValue(list.get(0).getValue());
        model.setname(list.get(0).getName());
        currentSession().clear();
        currentSession().delete(model);
    }
    @Override
    public List<ModelDictionary> serch(ModelDictionary model) {
        Session session=currentSession();
        CriteriaBuilder cb =session.getCriteriaBuilder();
        CriteriaQuery<ModelDictionary> query=cb.createQuery(ModelDictionary.class);
        Root<ModelDictionary> root=query.from(ModelDictionary.class);
        ModelDictionary slovarModel= session.createQuery(query.where(cb.equal(root.get("key"), model.getKey()))).getResultList().get(0);
        return Arrays.asList(slovarModel);
    }
    @Override
    @Transactional
    public void add(ModelDictionary model){
        Session session=sessionFactory.getCurrentSession();
        session.persist(new ModelDictionary(model.getKey(),model.getValue(),model.getName()));
    }
}