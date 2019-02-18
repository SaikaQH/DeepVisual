package DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class LSTMNN extends RecurrentNN {
    public LSTMNN() {
        this.NN_type += "LSTM";
        this.getStyleClass().add("LSTM");
    }

    public LSTMNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "LSTM" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public LSTMNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _units = 2;  // int
    private final int _activation = 3;  // Activations
    private final int _recurrent_activation = 4;
    private final int _use_bias = 5;  // boolean
    private final int _kernel_initializer = 6;  // Initializers
    private final int _recurrent_initializer = 7;  // Initializers
    private final int _bias_initializer = 8;  // Initializers
    private final int _unit_forget_bias = 9;  // boolean
    private final int _kernel_regularizer = 10;  // Regularizers
    private final int _recurrent_regularizer = 11;  // Regularizers
    private final int _bias_regularizer = 12;  // Regularizers
    private final int _activity_regularizer = 13;  // Regularizers
    private final int _kernel_constraint = 14;  // Constraints
    private final int _recurrent_constraint = 15;  // Constraints
    private final int _bias_constraint = 16;  // Constraints
    private final int _dropout = 17;  // float
    private final int _recurrent_dropout = 18;  // float
    private final int _implementation = 19;  // 1 or 2
    private final int _return_sequences = 20;  // boolean
    private final int _return_state = 21;  // boolean
    private final int _go_backwards = 22;  // boolean (false)
    private final int _stateful = 23;  // boolean (false)
    private final int _unroll = 24;  // boolean (false)

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("units",                 "int",          "1",                 this, true),
                new NNAttribute("activation",            "Activations",  "None",              this),
                new NNAttribute("recurrent_activation",  "Activations",  "\'hard_sigmoid\'",  this),
                new NNAttribute("use_bias",              "boolean",      "False",             this),
                new NNAttribute("kernel_initializer",    "Initializers", "None",              this),
                new NNAttribute("recurrent_initializer", "Initializers", "None",              this),
                new NNAttribute("bias_initializer",      "Initializers", "None",              this),
                new NNAttribute("unit_forget_bias",      "boolean",      "False",             this),
                new NNAttribute("kernel_regularizer",    "Regularizers", "None",              this),
                new NNAttribute("recurrent_regularizer", "Regularizers", "None",              this),
                new NNAttribute("bias_regularizer",      "Regularizers", "None",              this),
                new NNAttribute("activity_regularizer",  "Regularizers", "None",              this),
                new NNAttribute("kernel_constraint",     "Constraints",  "None",              this),
                new NNAttribute("recurrent_constraint",  "Constraints",  "None",              this),
                new NNAttribute("bias_constraint",       "Constraints",  "None",              this),
                new NNAttribute("dropout",               "float",        "0.0",               this),
                new NNAttribute("recurrent_dropout",     "float",        "0.0",               this),
                new NNAttribute("implementation",        "ChoiceBox",    "1",                 this),
                new NNAttribute("return_sequences",      "boolean",      "False",             this),
                new NNAttribute("return_state",          "boolean",      "False",             this),
                new NNAttribute("go_backwards",          "boolean",      "False",             this),
                new NNAttribute("stateful",              "boolean",      "False",             this),
                new NNAttribute("unroll",                "boolean",      "False",             this)
        };
    }
}
