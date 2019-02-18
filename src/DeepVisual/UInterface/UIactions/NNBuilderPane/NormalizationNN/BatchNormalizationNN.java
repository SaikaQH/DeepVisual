package DeepVisual.UInterface.UIactions.NNBuilderPane.NormalizationNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class BatchNormalizationNN extends NormalizationNN {
    public BatchNormalizationNN() {
        this.NN_type += "BatchNormalization";
        this.getStyleClass().add("BatchNormalization");
    }

    public BatchNormalizationNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "BatchNormalization" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public BatchNormalizationNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _axis = 2;  // int
    private final int _momentum = 3;  // String?
    private final int _epsilon = 4;  // float
    private final int _center = 5;  // boolean
    private final int _scale = 6;  // boolean
    private final int _beta_initializer = 7;  // Initializers
    private final int _gamma_initializer = 8;  // Initializers
    private final int _moving_mean_initializer = 9;  // Initializers
    private final int _moving_variance_initializer = 10;  // Initializers
    private final int _beta_regularizer = 11;  // Regularizers
    private final int _gamma_regularizer = 12;  // Regularizers
    private final int _beta_constraint = 13;  // Constraints
    private final int _gamma_constraint = 14;  // Constraints

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("axis",                        "int",          "1",     this),
                new NNAttribute("momentum",                    "String",       "None",  this),
                new NNAttribute("epsilon",                     "float",        "0.1",   this),
                new NNAttribute("center",                      "boolean",      "False", this),
                new NNAttribute("scale",                       "boolean",      "False", this),
                new NNAttribute("beta_initializer",            "Initializers", "None",  this),
                new NNAttribute("gamma_initializer",           "Initializers", "None",  this),
                new NNAttribute("moving_mean_initializer",     "Initializers", "None",  this),
                new NNAttribute("moving_variance_initializer", "Initializers", "None",  this),
                new NNAttribute("beta_regularizer",            "Regularizers", "None",  this),
                new NNAttribute("gamma_regularizer",           "Regularizers", "None",  this),
                new NNAttribute("beta_constraint",             "Constraints",  "None",  this),
                new NNAttribute("gamma_constraint",            "Constraints",  "None",  this)
        };
    }
}
