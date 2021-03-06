package DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class SimpleRNNCellNN extends RecurrentNN {
    public SimpleRNNCellNN() {
        this.NN_type += "SimpleRNNCell";
        this.getStyleClass().add("SimpleRNNCell");
    }

    public SimpleRNNCellNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "SimpleRNNCell" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public SimpleRNNCellNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _units = 2;  // int
    private final int _activation = 3;  // Activations
    private final int _use_bias = 4;  // boolean
    private final int _kernel_initializer = 5;  // Initializers
    private final int _recurrent_initializer = 6;  // Initializers
    private final int _bias_initializer = 7;  // Initializers
    private final int _kernel_regularizer = 8;  // Regularizers
    private final int _recurrent_regularizer = 9;  // Regularizers
    private final int _bias_regularizer = 10;  // Regularizers
    private final int _activity_regularizer = 11;  // Regularizers
    private final int _kernel_constraint = 12;  // Constraints
    private final int _recurrent_constraint = 13;  // Constraints
    private final int _bias_constraint = 14;  // Constraints
    private final int _dropout = 15;  // float
    private final int _recurrent_dropout = 16;  // float

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("units",                 "int",          "1",     this, true),
                new NNAttribute("activation",            "Activations",  "None",  this),
                new NNAttribute("use_bias",              "boolean",      "False", this),
                new NNAttribute("kernel_initializer",    "Initializers", "None",  this),
                new NNAttribute("recurrent_initializer", "Initializers", "None",  this),
                new NNAttribute("bias_initializer",      "Initializers", "None",  this),
                new NNAttribute("kernel_regularizer",    "Regularizers", "None",  this),
                new NNAttribute("recurrent_regularizer", "Regularizers", "None",  this),
                new NNAttribute("bias_regularizer",      "Regularizers", "None",  this),
                new NNAttribute("activity_regularizer",  "Regularizers", "None",  this),
                new NNAttribute("kernel_constraint",     "Constraints",  "None",  this),
                new NNAttribute("recurrent_constraint",  "Constraints",  "None",  this),
                new NNAttribute("bias_constraint",       "Constraints",  "None",  this),
                new NNAttribute("dropout",               "float",        "0.0",   this),
                new NNAttribute("recurrent_dropout",     "float",        "0.0",   this)
        };
    }
}
