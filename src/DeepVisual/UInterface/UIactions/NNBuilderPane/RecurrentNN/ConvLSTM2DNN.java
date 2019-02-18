package DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class ConvLSTM2DNN extends RecurrentNN {
    public ConvLSTM2DNN() {
        this.NN_type += "ConvLSTM2D";
        this.getStyleClass().add("ConvLSTM2D");
    }

    public ConvLSTM2DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "ConvLSTM2D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public ConvLSTM2DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _filters = 2;  // int
    private final int _kernel_size = 3;  // String
    private final int _strides = 4;  // String
    private final int _padding = 5;  // 'valid' or 'same'
    private final int _data_format = 6;  // String, channels_last or channels_first
    private final int _dilation_rate = 7;  // String
    private final int _activation = 8;  // Activations
    private final int _recurrent_activation = 9;  // Activations
    private final int _use_bias = 10;  // boolean
    private final int _kernel_initializer = 11;  // Initializers
    private final int _recurrent_initializer = 12;  // Initializers
    private final int _bias_initializer = 13;  // Initializers
    private final int _unit_forget_bias = 14;  // boolean
    private final int _kernel_regularizer = 15;  // Regularizers
    private final int _recurrent_regularizer = 16;  // Regularizers
    private final int _bias_regularizer = 17;  // Regularizers
    private final int _activity_regularizer = 18;  // Regularizers
    private final int _kernel_constraint = 19;  // Constraints
    private final int _recurrent_constraint = 20;  // Constraints
    private final int _bias_constraint = 21;  // Constraints
    private final int _return_sequences = 22;  // boolean
    private final int _go_backwards = 23;  // boolean (false)
    private final int _stateful = 24;  // boolean (false)
    private final int _dropout = 25;  // float
    private final int _recurrent_dropout = 26;  // float

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("filters",               "int",          "1",                 this, true),
                new NNAttribute("kernel_size",           "String",       "1",                 this, true),
                new NNAttribute("strides",               "String",       "1",                 this),
                new NNAttribute("padding",               "ChoiceBox",    "\'valid\'",         this),
                new NNAttribute("data_format",           "String",       "channels_last",     this),
                new NNAttribute("dilation_rate",         "String",       "1",                 this),
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
                new NNAttribute("implementation",        "ChoiceBox",    "1",                 this),
                new NNAttribute("return_sequences",      "boolean",      "False",             this),
                new NNAttribute("go_backwards",          "boolean",      "False",             this),
                new NNAttribute("stateful",              "boolean",      "False",             this),
                new NNAttribute("dropout",               "float",        "0.0",               this),
                new NNAttribute("recurrent_dropout",     "float",        "0.0",               this),
        };
    }
}
