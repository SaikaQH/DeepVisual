package DeepVisual.UInterface.UIactions.NNBuilderPane.NoiseNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class AlphaDropoutNN extends NoiseNN {
    public AlphaDropoutNN() {
        this.NN_type += "AlphaDropout";
        this.getStyleClass().add("AlphaDropout");
    }

    public AlphaDropoutNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "AlphaDropout" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public AlphaDropoutNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _rate = 2;  // float
    private final int _seed = 3;  // int

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("rate", "float", "0.1", this),
                new NNAttribute("seed", "int",   "1",   this)
        };
    }
}
