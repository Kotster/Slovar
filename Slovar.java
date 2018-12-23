import java.io.*;

public class Slovar implements ISlovar{
    private File file;
    private int KeyLength;
    private String reg;

    public Slovar(File file, int KeyLength, String reg) {
        this.file=file;
        this.KeyLength=KeyLength;
        this.reg=reg;
    }

    public File getFile() {
        return file;
    }

    public int getKeyLength() {
        return KeyLength;
    }

    public String getReg() {
        return reg;
    }
}