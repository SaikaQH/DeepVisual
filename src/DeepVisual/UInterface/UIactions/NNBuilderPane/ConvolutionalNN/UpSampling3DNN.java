package DeepVisual.UInterface.UIactions.NNBuilderPane.ConvolutionalNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class UpSampling3DNN extends ConvolutionalNN {
    public UpSampling3DNN() {
        this.NN_type += "UpSampling3D";
        this.getStyleClass().add("UpSampling3D");
    }

    public UpSampling3DNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "UpSampling3D" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public UpSampling3DNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _size = 2;  // int
    private final int _data_format = 3;  // ChoiceBox

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("size",        "int",       "1",                 this),
                new NNAttribute("data_format", "ChoiceBox", "\'channels_last\'", this)
        };
    }
}
