package Controller;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SlovarModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    @Column(unique = true, nullable = false)
    private String key;
    @Column(nullable = false)
    private String value;

    private int KeyLength;
    private String reg;

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

    public SlovarModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SlovarModel() {
    }

    public int getId() {
        return Id;
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
}
