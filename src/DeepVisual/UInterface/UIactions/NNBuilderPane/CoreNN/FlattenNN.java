package DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class FlattenNN extends CoreNN {
    public FlattenNN() {
        this.NN_type += "Flatten";
        this.getStyleClass().add("Flatten");
    }

    public FlattenNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "Flatten" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public FlattenNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _data_format = 2;

    private void initNNAttribute() {  // 初始化神经网络层的各项属性
        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("data_format", "String", "None", this)
        };
    }
}
