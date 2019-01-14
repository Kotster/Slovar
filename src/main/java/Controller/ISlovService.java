package Controller;

import java.util.List;

public interface ISlovService {
    void Show();
    void Delete(String key);
    List<String> Serch(String Key);
    void Add(String Key, String Value);
}
