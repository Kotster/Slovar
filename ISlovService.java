public interface ISlovService {
    void Show(ISlovar slovar);
    void Delete(ISlovar slovar, String key);
    String Serch(ISlovar slovar, String Key);
    void Add(ISlovar slovar, String Key, String Value);
}
