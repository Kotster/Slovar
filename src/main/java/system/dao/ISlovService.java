package system.dao;

import system.model.ModelDictionary;

import java.util.List;

public interface ISlovService {
    List<ModelDictionary> all(ModelDictionary model);
    void update(ModelDictionary model);
    void delete(ModelDictionary model);
    List<ModelDictionary> serch(ModelDictionary model) throws Exception;
    void add(ModelDictionary model) throws Exception;
}
