package Controller;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public class DbControl implements ISlovService{

    @PersistenceContext
    EntityManager entityManager;

    public void Show() {

    }

    public void Delete(String Key) {

        List<String> list=Serch(Key);
        SlovarModel slovarModel=new SlovarModel(list.get(1),list.get(2));
        slovarModel.setId(Integer.parseInt(list.get(0)));
        entityManager.remove(slovarModel) ;
    }

    public List<String> Serch(String Key) {
        entityManager.getTransaction().begin();
        CriteriaBuilder cb =entityManager.getCriteriaBuilder();
        CriteriaQuery<SlovarModel> query=cb.createQuery(SlovarModel.class);
        Root<SlovarModel> root=query.from(SlovarModel.class);
        SlovarModel slovarModel=entityManager.createQuery(query.where(cb.equal(root.get("key"), Key))).getResultList().get(0);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new ArrayList<String>(Arrays.asList(slovarModel.getId()+"",slovarModel.getKey(),slovarModel.getValue()));
    }
    @SuppressWarnings("unchecked")
    public void Add(String Key, String Value) {
        entityManager.merge(new SlovarModel(Key,Value)) ;
    }
}

