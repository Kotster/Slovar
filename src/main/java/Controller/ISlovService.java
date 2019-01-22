package Controller;

import java.util.List;

public interface ISlovService {
    List<String> all(SlovarModel model);
    void update(SlovarModel model);
    void delete(SlovarModel model);
    List<String> serch(SlovarModel model) throws Exception;
    void add(SlovarModel model);
}
