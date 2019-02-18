package DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class DenseNN extends CoreNN {
    public DenseNN() {
        this.NN_type += "Dense";
        this.getStyleClass().add("Dense");
    }

    public DenseNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "Dense" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public DenseNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _units = 2;  // int
    private final int _activation = 3;  // Activations
    private final int _use_bias = 4;  // boolean
    private final int _kernel_initializer = 5;  // Initializers
    private final int _bias_initializer = 6;  // Initializers
    private final int _kernel_regularizer = 7;  // Regularizers
    private final int _bias_regularizer = 8;  // Regularizers
    private final int _activity_regularizer = 9;  // Regularizers
    private final int _kernel_constraints = 10;  // Constraints
    private final int _bias_constraints = 11;  // Constraints

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("units",                "int",          "1",     this, true),
                new NNAttribute("activation",           "Activations",  "None",  this),
                new NNAttribute("use_bias",             "boolean",      "False", this),
                new NNAttribute("kernel_initializer",   "Initializers", "None",  this),
                new NNAttribute("bias_initializer",     "Initializers", "None",  this),
                new NNAttribute("kernel_regularizer",   "Regularizers", "None",  this),
                new NNAttribute("bias_regularizer",     "Regularizers", "None",  this),
                new NNAttribute("activity_regularizer", "Regularizers", "None",  this),
                new NNAttribute("kernel_constraints",   "Constraints",  "None",  this),
                new NNAttribute("bias_constraints",     "Constraints",  "None",  this)
        };
    }
}
