package Controller;

import java.util.List;

public interface ISlovService {
    List<String> All();
    void Update(String Key, String Value);
    void Delete(String key);
    List<String> Serch(String Key);
    void Add(String Key, String Value);
}
