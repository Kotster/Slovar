package system.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;

@Entity
@Table(name="dictionar")
@JsonRootName("modelDictionary")
public class ModelDictionary {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="key", unique = true, nullable = false)
    private String key;
    @Column(name="value", nullable = false)
    private String value;
    @Column(name="name", nullable = false)
    private String name;

    private int KeyLength;
    private String reg;

    public ModelDictionary(String key, String value, String name) {
        this.key = key;
        this.value = value;
        this.name=name;
    }
    public ModelDictionary(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public ModelDictionary(String reg, int KeyLength, String name) {
        this.reg = reg;
        this.KeyLength = KeyLength;
        this.name=name;
    }

    public ModelDictionary() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getKeyLength() {
        return KeyLength;
    }

    public void setKeyLength(int keyLength) {
        KeyLength = keyLength;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{\"id\":\""+this.getId()+"\",\"key\":\""+this.getKey()+"\",\"value\":\""+this.getValue()+"\",\"name\":\""+this.getName()+"\"}";
    }
}
