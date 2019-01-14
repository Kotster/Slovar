package Controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SlovarModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    @Column(name="key", unique = true, nullable = false)
    private String key;
    @Column(name="key", nullable = false)
    private String value;

    private int KeyLength;
    private String reg;
    private String nameTable;

    @Autowired
    dbControl control;

    public SlovarModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SlovarModel() {
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

        public dbControl getControl() {
        return control;
    }
//
//    public void setControl(dbControl control) {
//        this.control = control;
//    }

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
