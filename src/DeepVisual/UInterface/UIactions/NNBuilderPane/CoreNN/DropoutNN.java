package DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class DropoutNN extends CoreNN {
    public DropoutNN() {
        this.NN_type += "Dropout";
        this.getStyleClass().add("Dropout");
    }

    public DropoutNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "Dropout" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public DropoutNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _rate = 2;  //
    private final int _noise_shape = 3;  //
    private final int _seed = 4;  //

    private void initNNAttribute() {  // 初始化神经网络层的各项属性
        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("rate",        "float",  "0.1",    this, true),
                new NNAttribute("noise_shape", "String", "None", this),
                new NNAttribute("seed",        "String", "None", this)
        };
    }
}
