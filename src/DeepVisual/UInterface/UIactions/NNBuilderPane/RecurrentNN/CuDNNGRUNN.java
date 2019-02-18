package DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class CuDNNGRUNN extends RecurrentNN {
    public CuDNNGRUNN() {
        this.NN_type += "CuDNNGRU";
        this.getStyleClass().add("CuDNNGRU");
    }

    public CuDNNGRUNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "CuDNNGRU" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public CuDNNGRUNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _units = 2;  // int
    private final int _kernel_initializer = 3;  // Initializers
    private final int _recurrent_initializer = 4;  // Initializers
    private final int _bias_initializer = 5;  // Initializers
    private final int _kernel_regularizer = 6;  // Regularizers
    private final int _recurrent_regularizer = 7;  // Regularizers
    private final int _bias_regularizer = 8;  // Regularizers
    private final int _activaty_regularizer = 9;  // Regularizers
    private final int _kernel_constraint = 10;  // Constraints
    private final int _recurrent_constraint = 11;  // Constraints
    private final int _bias_constraint = 12;  // Constraints
    private final int _return_sequences = 13;  // boolean
    private final int _return_state = 14;  // boolean
    private final int stateful = 15;  // boolean (false)

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("units",                 "int",          "1",     this, true),
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
                new NNAttribute("return_sequences",      "boolean",      "False", this),
                new NNAttribute("return_state",          "boolean",      "False", this),
                new NNAttribute("stateful",              "boolean",      "False", this)
        };
    }
}
