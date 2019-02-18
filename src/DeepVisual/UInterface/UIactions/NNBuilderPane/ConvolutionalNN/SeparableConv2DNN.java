package DeepVisual.UInterface.UIactions.NNBuilderPane.ConvolutionalNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class SeparableConv2DNN extends ConvolutionalNN {
    public SeparableConv2DNN() {
        this.NN_type += "SeparableConv2D";
        this.getStyleClass().add("SeparableConv2D");
    }

    public SeparableConv2DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "SeparableConv2D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public SeparableConv2DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _filters = 2;  // int
    private final int _kernel_size = 3;  // int
    private final int _strides = 4;  // int
    private final int _padding = 5;  // String     ?????    valid or same
    private final int _data_format = 6;  // String    ?????    channels_last or channels_first
    private final int _dilation_rate = 7;  // int
    private final int _depth_multiplier = 8;  // int  // +++
    private final int _activation = 9;  // Activations
    private final int _use_bias = 10;  // boolean
    private final int _depthwise_initializer = 11;  // Initializers
    private final int _pointwise_initializer = 12;  // Initializers
    private final int _bias_initializer = 13;  // Initializers
    private final int _depthwise_regularizer = 14;  // Regularizers
    private final int _pointwise_regularizer = 15;  // Regularizers
    private final int _bias_regularizer = 16;  // Regularizers
    private final int _activity_regularizer = 17;  // Regularizers
    private final int _depthwise_constraint = 18;  // Constraints
    private final int _pointwise_constraints = 19;  // Constraints
    private final int _bias_constraints = 20;  // Constraints

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("filters",               "int",          "1",                 this, true),
                new NNAttribute("kernel_size",           "int",          "1",                 this, true),
                new NNAttribute("strides",               "String",       "(1, 1)",            this),
                new NNAttribute("padding",               "ChoiceBox",    "\'valid\'",         this),
                new NNAttribute("data_format",           "ChoiceBox",    "\'channels_last\'", this),
                new NNAttribute("dilation_rate",         "String",       "(1, 1)",            this),
                new NNAttribute("depth_multiplier",      "int",          "1",                 this),
                new NNAttribute("activation",            "Activations",  "None",              this),
                new NNAttribute("use_bias",              "boolean",      "True",              this),
                new NNAttribute("depthwise_initializer", "Initializers", "None",              this),
                new NNAttribute("pointwise_initializer", "Initializers", "None",              this),
                new NNAttribute("bias_initializer",      "Initializers", "None",              this),
                new NNAttribute("depthwise_regularizer", "Regularizers", "None",              this),
                new NNAttribute("pointwise_regularizer", "Regularizers", "None",              this),
                new NNAttribute("bias_regularizer",      "Regularizers", "None",              this),
                new NNAttribute("activity_regularizer",  "Regularizers", "None",              this),
                new NNAttribute("depthwise_constraints", "Constraints",  "None",              this),
                new NNAttribute("pointwise_constraints", "Constraints",  "None",              this),
                new NNAttribute("bias_constraints",      "Constraints",  "None",              this)
        };
    }
}
