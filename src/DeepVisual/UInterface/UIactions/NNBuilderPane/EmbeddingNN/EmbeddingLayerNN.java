package DeepVisual.UInterface.UIactions.NNBuilderPane.EmbeddingNN;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;

public class EmbeddingLayerNN extends EmbeddingNN {
    public EmbeddingLayerNN() {
        this.NN_type += "Embedding";
        this.getStyleClass().add("Embedding");
    }

    public EmbeddingLayerNN(double layout_pos_x, double layout_pos_y, int btn_id) {
        this();
        this.NN_id = btn_id;
        this.NN_name = "Embedding" + "_" + NN_id;
        this.setText(this.NN_name);
        initNNBtnPosition(layout_pos_x, layout_pos_y);
        initNNAttribute();
    }

    public EmbeddingLayerNN(double layout_pos_x, double layout_pos_y, int btn_id, DeepVisualWindowController _parent) {
        this(layout_pos_x, layout_pos_y, btn_id);
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------

    private final int _input_dim = 2;  // int
    private final int _output_dim = 3;  // int
    private final int _embeddings_initializer = 4;  // Initializers
    private final int _embeddings_regularizer = 5;  // Regularizers
    private final int _embeddings_constraint = 6;  // Constraints
    private final int _mask_zero = 7;  // boolean
    private final int _input_length = 8;  // int

    private void initNNAttribute() {  // 初始化神经网络层的各项属性

        attr_list = new NNAttribute[]{
                new NNAttribute("name", "NNName", NN_name, this),
                new NNAttribute("type", "Text", NN_type,   this),

                new NNAttribute("input_dim",              "int",          "1",     this, true),
                new NNAttribute("output_dim",             "int",          "0",     this, true),
                new NNAttribute("embeddings_initializer", "Initializers", "None",  this),
                new NNAttribute("embeddings_regularizer", "Regularizers", "None",  this),
                new NNAttribute("embeddings_constraint",  "Constraints",  "None",  this),
                new NNAttribute("mask_zero",              "boolean",      "False", this),
                new NNAttribute("input_length",           "int",          "1",     this)
        };
    }
}
