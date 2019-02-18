package DeepVisual.UInterface.UIactions.NNBuilderPane.LocallyConnectedNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class LocallyConnected1DNN extends LocallyConnectedNN {
    public LocallyConnected1DNN() {
        this.NN_type += "LocallyConnected1D";
        this.getStyleClass().add("LocallyConnected1D");
    }

    public LocallyConnected1DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "LocallyConnected1D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public LocallyConnected1DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _filters = 2;  // int
    private final int _kernel_size = 3;  // int
    private final int _strides = 4;  // int
    private final int _padding = 5;  // 'valid'   // 'same' is not support
    private final int _activation = 6;  // Activations
    private final int _use_bias = 7;  // boolean
    private final int _kernel_initializer = 8;  // Initializers
    private final int _bias_initializer = 9;  // Initializers
    private final int _kernel_regularizer = 10;  // Regularizers
    private final int _bias_regularizer = 11;  // Regularizers
    private final int _activity_regularizer = 12;  // Regularizers
    private final int _kernel_constraints = 13;  // Constraints
    private final int _bias_constraints = 14;  // Constraints

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("filters",              "int",          "1",         this, true),
                new NNAttribute("kernel_size",          "int",          "1",         this, true),
                new NNAttribute("strides",              "int",          "1",         this),
                new NNAttribute("padding",              "ChoiceBox",    "\'valid\'", this),
                new NNAttribute("activation",           "Activations",  "None",      this),
                new NNAttribute("use_bias",             "boolean",      "False",     this),
                new NNAttribute("kernel_initializer",   "Initializers", "None",      this),
                new NNAttribute("bias_initializer",     "Initializers", "None",      this),
                new NNAttribute("kernel_regularizer",   "Regularizers", "None",      this),
                new NNAttribute("bias_regularizer",     "Regularizers", "None",      this),
                new NNAttribute("activity_regularizer", "Regularizers", "None",      this),
                new NNAttribute("kernel_constraints",   "Constraints",  "None",      this),
                new NNAttribute("bias_constraints",     "Constraints",  "None",      this)
        };
    }
}
