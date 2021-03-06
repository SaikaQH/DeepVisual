package DeepVisual.UInterface.UIactions.NNBuilderPane.PoolingNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class GlobalMaxPooling2DNN extends PoolingNN {
    public GlobalMaxPooling2DNN() {
        this.NN_type += "GlobalMaxPooling2D";
        this.getStyleClass().add("GlobalMaxPooling2D");
    }

    public GlobalMaxPooling2DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "GlobalMaxPooling2D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public GlobalMaxPooling2DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _data_format = 2;  // String, channels_last or channels_first

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("data_format", "String", "channels_last", this),
        };
    }
}
