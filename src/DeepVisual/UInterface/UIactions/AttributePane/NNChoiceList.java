package DeepVisual.UInterface.UIactions.AttributePane;

public class NNChoiceList {
    private String[] choice_list;

    public NNChoiceList() {

    }

    public NNChoiceList(String attr_name) {
        initChoiceList(attr_name);
    }

    private void initChoiceList(String attr_name) {
        if      (attr_name.equals("padding"))        choice_list = new String[]{"\'valid\'", "\'same\'"};
        else if (attr_name.equals("padding3"))       choice_list = new String[]{"\'valid\'", "\'causal\'", "\'same\'"};
        else if (attr_name.equals("data_format"))    choice_list = new String[]{"\'channels_last\'", "\'channels_first\'"};
        else if (attr_name.equals("implementation")) choice_list = new String[]{"1", "2"};
    }

    public String[] getChoice_list() {
        return choice_list;
    }
}
