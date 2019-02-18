package DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class InputNN extends CoreNN {
    public InputNN() {
        this.NN_type += "Input";
        this.getStyleClass().add("Input");
    }

    public InputNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "Input" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public InputNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _shape = 2;  // shape
    private final int _batch_shape = 3;  // batch_shape
    private final int _name = 4;  // name
    private final int _dtype = 5;  // dtype
    private final int _sparse = 6;  // sparse
    private final int _tensor = 7;  // tensor

    private void initNNAttribute() {  // 初始化神经网络层的各项属性
        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("shape",       "String",  "(32,)", this),
                new NNAttribute("batch_shape", "String",  "None",  this),
                new NNAttribute("name(layer)", "String",  "None",  this),
                new NNAttribute("dtype",       "String",  "None",  this),
                new NNAttribute("sparse",      "boolean", "False", this),
                new NNAttribute("tensor",      "Text",    "None",  this)
        };
    }
}
