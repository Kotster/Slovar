package Controller;

public interface ISlovService {
    void Show();
    void Delete(String key);
    String Serch(String Key);
    void Add(String Key, String Value) throws dbControl.NotUniqException;
}
