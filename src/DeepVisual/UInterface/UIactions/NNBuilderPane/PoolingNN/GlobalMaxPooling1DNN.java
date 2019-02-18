package DeepVisual.UInterface.UIactions.NNBuilderPane.PoolingNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class GlobalMaxPooling1DNN extends PoolingNN {
    public GlobalMaxPooling1DNN() {
        this.NN_type += "GlobalMaxPooling1D";
        this.getStyleClass().add("GlobalMaxPooling1D");
    }

    public GlobalMaxPooling1DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "GlobalMaxPooling1D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public GlobalMaxPooling1DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),
        };
    }
}
