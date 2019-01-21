package Controller;

import java.util.List;

public interface ISlovService {
    List<String> all();
    void update(String Key, String Value);
    void delete(String key);
    List<String> serch(String Key) throws Exception;
    void add(String Key, String Value);
}
