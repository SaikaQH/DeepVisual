package DeepVisual.UInterface.UIactions.NNBuilderPane.AdvancedActivationsNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class LeakyReLUNN extends AdvancedActivationsNN {
    public LeakyReLUNN() {
        this.NN_type += "LeakyReLU";
        this.getStyleClass().add("LeakyReLU");
    }

    public LeakyReLUNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "LeakyReLU" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public LeakyReLUNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _alpha = 2;  // float

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("alpha", "float", "0.1", this)
        };
    }
}
