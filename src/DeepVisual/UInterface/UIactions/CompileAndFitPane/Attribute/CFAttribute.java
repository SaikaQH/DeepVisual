package DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute;

import DeepVisual.UInterface.UIactions.CompileAndFitPane.CFBtn;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

public class CFAttribute {
    private String attribute_name;
    private String attribute_type;
    private String attribute_value;
    private Control attribute_control;
    private boolean flag_not_show_name;

    private CFBtn p_btn;

    public CFAttribute() {
        flag_not_show_name = false;
    }

    public CFAttribute(String attribute_name, String attribute_type) {
        this();
        this.attribute_name = attribute_name;
        this.attribute_type = attribute_type;
    }

    public CFAttribute(String attribute_name, String attribute_type, String attribute_value) {
        this(attribute_name, attribute_type);
        this.attribute_value = attribute_value;
        this.attribute_control = createAttrValue(attribute_type, attribute_value);
    }

    public CFAttribute(String attribute_name, String attribute_type, String attribute_value, CFBtn p_btn) {
        this(attribute_name, attribute_type, attribute_value);
        this.p_btn = p_btn;
    }

    public CFAttribute(String attribute_name, String attribute_type, String attribute_value, CFBtn p_btn, boolean flag_not_show_name) {
        this(attribute_name, attribute_type, attribute_value, p_btn);
        this.flag_not_show_name = flag_not_show_name;
    }

    // ------------------------------------------------------ set & get ------------------------------------------------------------------

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getAttribute_name() {
        return this.attribute_name;
    }

    public void setAttribute_type(String attribute_type) {
        this.attribute_type = attribute_type;
    }

    public String getAttribute_type() {
        return this.attribute_type;
    }

    public void setAttribute_value(String attribute_value) {
        this.attribute_value = attribute_value;
        this.attribute_control = createAttrValue(attribute_type, attribute_value);
    }

    public String getAttribute_value() {
        return this.attribute_value;
    }

    public void setAttribute_control(Control attribute_control) {
        this.attribute_control = attribute_control;
    }

    public Control getAttribute_control() {
        return this.attribute_control;
    }

    public void setFlag_not_show_name(boolean flag_not_show_name) {
        this.flag_not_show_name = flag_not_show_name;
    }

    public boolean getFlag_not_show_name() {
        return this.flag_not_show_name;
    }

    // ------------------------------------------------------- function ------------------------------------------------------------------

    private Control createAttrValue(String attr_type, String attr_value) {
        if     (attr_type.equals("Text"))         {return valueType_Text         (attr_value);}  // Label
        else if(attr_type.equals("int"))          {return valueType_Int          (attr_value);}  // TextField
        else if(attr_type.equals("float"))        {return valueType_Float        (attr_value);}  // TextField
        else if(attr_type.equals("String"))       {return valueType_String       (attr_value);}  // TextField
        else if(attr_type.equals("boolean"))      {return valueType_boolean      (attr_value);}  // CheckBox
        else if(attr_type.equals("Optimizers"))   {return valueType_Optimizers   (attr_value);}  // ChoiceBox
        else if(attr_type.equals("Losses"))       {return valueType_Losses       (attr_value);}  // ChoiceBox
        else if(attr_type.equals("Callbacks"))    {return valueType_Callbacks    (attr_value);}  // ChoiceBox
        else if(attr_type.equals("ChoiceBox"))    {return valueType_ChoiceBox    (attr_value);}
        return new Label(attr_value);
    }

    private Label valueType_Text(String attr_value) {
        Label attr_node = new Label(attr_value);
        return attr_node;
    }

    private TextField valueType_Int(String attr_value) {
        TextField attr_node = new TextField(attr_value);
        addListener_TextField(attr_node);
        return attr_node;
    }

    private TextField valueType_Float(String attr_value) {
        TextField attr_node = new TextField(attr_value);
        addListener_TextField(attr_node);
        return attr_node;
    }

    private TextField valueType_String(String attr_value) {
        TextField attr_node = new TextField(attr_value);
        addListener_TextField(attr_node);
        return attr_node;
    }

    private CheckBox valueType_boolean(String attr_value) {
        CheckBox attr_node = new CheckBox();
        if(attr_value.equals("True"))
            attr_node.setSelected(true);
        else if(attr_value.equals("False"))
            attr_node.setSelected(false);

        addListener_CheckBox(attr_node);
        return attr_node;
    }

    private ChoiceBox valueType_Optimizers(String attr_value) {
        Optimizers attr_Optimizers = new Optimizers();
        ChoiceBox attr_node = new ChoiceBox();
        String[] optimizer_list = attr_Optimizers.getInitializers_list();
        attr_node.getItems().addAll(optimizer_list);

        int index = -1;
        for(int i = 0; i < optimizer_list.length; i ++) {  // 判断当前值是否属于 Optimizers
            if(optimizer_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, optimizer_list);
        return attr_node;
    }

    private ChoiceBox valueType_Losses(String attr_value) {
        Losses attr_losses = new Losses();
        ChoiceBox attr_node = new ChoiceBox();
        String[] losses_list = attr_losses.getLosses_list();
        attr_node.getItems().addAll(losses_list);

        int index = -1;
        for(int i = 0; i < losses_list.length; i ++) {  // 判断当前值是否属于 Optimizers
            if(losses_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, losses_list);
        return attr_node;
    }

    private ChoiceBox valueType_Callbacks(String attr_value) {
        Callbacks attr_callbacks = new Callbacks();
        ChoiceBox attr_node = new ChoiceBox();
        String[] callbacks_list = attr_callbacks.getCallbacks_list();
        attr_node.getItems().addAll(callbacks_list);

        int index = -1;
        for(int i = 0; i < callbacks_list.length; i ++) {  // 判断当前值是否属于 Callbacks
            if(callbacks_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, callbacks_list);
        return attr_node;
    }

    private ChoiceBox valueType_ChoiceBox(String attr_value) {
        CFChoiceList attr_choiceList = new CFChoiceList(attribute_name);
        ChoiceBox attr_node = new ChoiceBox();
        String[] choice_list = attr_choiceList.getChoice_list();
        attr_node.getItems().addAll(choice_list);

        int index = -1;
        for(int i = 0; i < choice_list.length; i ++) {  // 判断当前值是否属于 Constraints
            if(choice_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, choice_list);
        return attr_node;
    }

    private CFAttribute getself() {
        return this;
    }

    // -------------------------------------------------------------- Listener ------------------------------------------------------------------------

    private void addListener_TextField(TextField attr_node) {  // 为文本框添加事件，在文本发生改变时修改属性内容
        attr_node.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setAttribute_value(newValue);
                p_btn.get_parent().changeCFAttribute(getself(), oldValue, newValue);
            }
        });
    }

    private void addListener_ChoiceBox(ChoiceBox attr_node, String[] attr_list) {  // 为选择框添加事件，在选项发生改变时修改属性内容
        attr_node.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setAttribute_value(attr_list[newValue.intValue()]);
                p_btn.get_parent().changeCFAttribute(getself(), attr_list[newValue.intValue()], attr_list[oldValue.intValue()]);
            }
        });
    }

    private void addListener_CheckBox(CheckBox attr_node) {
        attr_node.selectedProperty().addListener(new ChangeListener<Boolean>() {  // 添加事件，在发生改变时修改属性内容
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue.booleanValue())
                    setAttribute_value("True");
                else
                    setAttribute_value("False");
                p_btn.get_parent().changeCFAttribute(getself(), ""+oldValue, ""+newValue);
            }
        });
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------
}
