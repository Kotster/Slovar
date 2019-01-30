package system.model;

public class ModelCont {
    ModelDictionary modelDictionary;
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public ModelCont() {
    }

    public ModelCont(ModelDictionary modelDictionary) {
        this.modelDictionary = modelDictionary;
    }

    public ModelDictionary getModelDictionary() {
        return modelDictionary;
    }

    public void setModelDictionary(ModelDictionary modelDictionary) {
        this.modelDictionary = modelDictionary;
    }
}
