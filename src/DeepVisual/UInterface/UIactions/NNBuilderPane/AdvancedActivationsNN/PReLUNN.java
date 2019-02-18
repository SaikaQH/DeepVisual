package DeepVisual.UInterface.UIactions.NNBuilderPane.AdvancedActivationsNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class PReLUNN extends AdvancedActivationsNN {
    public PReLUNN() {
        this.NN_type += "PReLU";
        this.getStyleClass().add("PReLU");
    }

    public PReLUNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "PReLU" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public PReLUNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _alpha_initializer = 2;  // Initializers
    private final int _alpha_regularizer = 3;  // Regularizers
    private final int _alpha_constraint = 4;  // Constraints
    private final int _shared_axes = 5;  // String

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("alpha_initializer", "Initializers", "None", this),
                new NNAttribute("alpha_regularizer", "Regularizers", "None", this),
                new NNAttribute("alpha_constraint",  "Constraints",  "None", this),
                new NNAttribute("shared_axes",       "String",       "None", this),
        };
    }
}
