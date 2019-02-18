package DeepVisual.UInterface.UIactions.AttributePane;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;

public class NNAttribute {
    private String attribute_name;
    private String attribute_type;
    private String attribute_value;
    private Control attribute_control;
    private boolean flag_not_show_name;

    private NNBuilderButton parent_btn;

    public NNAttribute() {
        flag_not_show_name = false;
    }

    public NNAttribute(String attribute_name, String attribute_type) {
        this();
        this.attribute_name = attribute_name;
        this.attribute_type = attribute_type;
    }

    public NNAttribute(String attribute_name, String attribute_type, String attribute_value) {
        this(attribute_name, attribute_type);
        this.attribute_value = attribute_value;
        this.attribute_control = createAttrValue(attribute_type, attribute_value);
    }

    public NNAttribute(String attribute_name, String attribute_type, String attribute_value, NNBuilderButton parent_btn) {
        this(attribute_name, attribute_type, attribute_value);
        this.parent_btn = parent_btn;
    }

    public NNAttribute(String attribute_name, String attribute_type, String attribute_value, boolean flag_not_show_name) {
        this(attribute_name, attribute_type, attribute_value);
        this.flag_not_show_name = flag_not_show_name;
    }

    public NNAttribute(String attribute_name, String attribute_type, String attribute_value, NNBuilderButton parent_btn, boolean flag_not_show_name) {
        this(attribute_name, attribute_type, attribute_value, parent_btn);
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
        attribute_control = createAttrValue(attribute_type, attribute_value);
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
        if     (attr_type.equals("NNName"))       {return valueType_NNName       (attr_value);}  // TextField - NNName
        else if(attr_type.equals("Text"))         {return valueType_Text         (attr_value);}  // Label
        else if(attr_type.equals("int"))          {return valueType_Int          (attr_value);}  // TextField
        else if(attr_type.equals("float"))        {return valueType_Float        (attr_value);}  // TextField
        else if(attr_type.equals("String"))       {return valueType_String       (attr_value);}  // TextField
        else if(attr_type.equals("boolean"))      {return valueType_boolean      (attr_value);}  // CheckBox
        else if(attr_type.equals("Activations"))  {return valueType_Activations  (attr_value);}  // ChoiceBox
        else if(attr_type.equals("Initializers")) {return valueType_Initializers (attr_value);}  // ChoiceBox
        else if(attr_type.equals("Regularizers")) {return valueType_Regularizers (attr_value);}  // TextField
        else if(attr_type.equals("Constraints"))  {return valueType_Constraints  (attr_value);}  // ChoiceBox
        else if(attr_type.equals("ChoiceBox"))    {return valueType_ChoiceBox    (attr_value);}
        return new Label(attr_value);
    }

    private TextField valueType_NNName(String attr_value) {
        TextField attr_node = new TextField(attr_value);
        addListener_TextField_NNName(attr_node);
        return attr_node;
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

    private ChoiceBox valueType_Activations(String attr_value) {
        Activations attr_activation = new Activations();
        ChoiceBox attr_node = new ChoiceBox();
        String[] activation_list = attr_activation.getActivations_list();
        attr_node.getItems().addAll(activation_list);

        int index = -1;
        for(int i = 0; i < activation_list.length; i ++) {  // 判断当前值是否属于 Activations
            if(activation_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, activation_list);
        return attr_node;
    }

    private ChoiceBox valueType_Initializers(String attr_value) {
        Initializers attr_initializer = new Initializers();
        ChoiceBox attr_node = new ChoiceBox();
        String[] initializer_list = attr_initializer.getInitializers_list();
        attr_node.getItems().addAll(initializer_list);

        int index = -1;
        for(int i = 0; i < initializer_list.length; i ++) {  // 判断当前值是否属于 Initializers
            if(initializer_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, initializer_list);
        return attr_node;
    }

    private TextField valueType_Regularizers(String attr_value) {
        // Regularizers 比较特殊，没有做完
        TextField attr_node = new TextField(attr_value);
        addListener_TextField(attr_node);
        return attr_node;
    }

    private ChoiceBox valueType_Constraints(String attr_value) {
        Constraints attr_constraint = new Constraints();
        ChoiceBox attr_node = new ChoiceBox();
        String[] constraint_list = attr_constraint.getConstraints_list();
        attr_node.getItems().addAll(constraint_list);

        int index = -1;
        for(int i = 0; i < constraint_list.length; i ++) {  // 判断当前值是否属于 Constraints
            if(constraint_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, constraint_list);
        return attr_node;
    }

    private ChoiceBox valueType_ChoiceBox(String attr_value) {
        //System.out.println(attribute_name + ", " + parent_btn.getNN_type());
        NNChoiceList attr_choiceList = new NNChoiceList(attribute_name);
        /*NNChoiceList attr_choiceList;
        if(attribute_name.equals("padding") && parent_btn.getNN_type().equals("Conv1D"))
            attr_choiceList = new NNChoiceList("padding3");
        else
            attr_choiceList = new NNChoiceList(attribute_name);*/
        ChoiceBox attr_node = new ChoiceBox();
        String[] choice_list = attr_choiceList.getChoice_list();
        attr_node.getItems().addAll(choice_list);

        int index = -1;
        for(int i = 0; i < choice_list.length; i ++) {  // 判断当前值是否属于 ChoiceBox
            if(choice_list[i].equals(attr_value)) {
                attr_node.setValue(attr_value);
                break;
            }
        }
        if(index == -1) attr_node.setValue("(Wrong Value)");

        addListener_ChoiceBox(attr_node, choice_list);
        return attr_node;
    }

    private NNAttribute getself() {
        return this;
    }

    // -------------------------------------------------------------- Listener ------------------------------------------------------------------------

    private void addListener_TextField(TextField attr_node) {  // 为文本框添加事件，在文本发生改变时修改属性内容
        attr_node.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                setAttribute_value(newValue);
                parent_btn.get_parent().changeNNBtnAttribute(parent_btn, getself(), oldValue, newValue);
            }
        });
    }

    private void addListener_TextField_NNName(TextField attr_node) {  // 特殊文本框，在文本发生改变时修改属性内容，同时修改按钮显示文本
        attr_node.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //System.out.println("name change to: " + newValue);
                setAttribute_value(newValue);
                parent_btn.setNN_name(newValue);
                parent_btn.get_parent().changeNNBtnName(parent_btn, getself(), oldValue, newValue);
            }
        });
    }

    private void addListener_ChoiceBox(ChoiceBox attr_node, String[] attr_list) {  // 为选择框添加事件，在选项发生改变时修改属性内容
        attr_node.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setAttribute_value(attr_list[newValue.intValue()]);
                parent_btn.get_parent().changeNNBtnAttribute(parent_btn, getself(), attr_list[newValue.intValue()], attr_list[oldValue.intValue()]);
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
                parent_btn.get_parent().changeNNBtnAttribute(parent_btn, getself(), ""+oldValue, ""+newValue);
            }
        });
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------
}
