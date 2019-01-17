package Controller;

import javax.persistence.*;

@Entity
@Table(name="dictionar")
public class SlovarModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="key", unique = true, nullable = false)
    private String key;
    @Column(name="value", nullable = false)
    private String value;

    private int KeyLength;
    private String reg;
    private String nameTable;

    public SlovarModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SlovarModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
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
        return "id:"+this.getId()+" | key:"+this.getKey()+" | value:"+this.getValue()+"\n\r";
    }
}
