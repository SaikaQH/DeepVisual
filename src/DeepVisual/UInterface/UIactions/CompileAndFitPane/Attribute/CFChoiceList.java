package DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute;

public class CFChoiceList {
    private String[] choice_list;

    public CFChoiceList() { }

    public CFChoiceList(String attr_name) {
        initChoiceList(attr_name);
    }

    private void initChoiceList(String attr_name) {
        if(attr_name.equals("verbose"))     choice_list = new String[]{"0", "1", "2"};
    }

    public String[] getChoice_list() {
        return choice_list;
    }
}
