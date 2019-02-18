package DeepVisual.UInterface.UIactions.NNBuilderPane.PoolingNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class AveragePooling1DNN extends PoolingNN {
    public AveragePooling1DNN() {
        this.NN_type += "AveragePooling1D";
        this.getStyleClass().add("AveragePooling1D");
    }

    public AveragePooling1DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "AveragePooling1D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public AveragePooling1DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _pool_size = 2;  // int
    private final int _strides = 3;  // int
    private final int _padding = 4;  // 'valid' or 'same'

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("pool_size", "int",       "1",         this),
                new NNAttribute("strides",   "int",       "1",         this),
                new NNAttribute("padding",   "ChoiceBox", "\'valid\'", this)
        };
    }
}
