package system.dao;

import system.model.Model;

import java.util.List;

public interface ISlovService {
    List<String> all(Model model);
    void update(Model model);
    void delete(Model model);
    List<String> serch(Model model) throws Exception;
    void add(Model model) throws Exception;
}
