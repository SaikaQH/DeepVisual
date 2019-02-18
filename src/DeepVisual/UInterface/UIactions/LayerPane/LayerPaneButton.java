package DeepVisual.UInterface.UIactions.LayerPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.AdvancedActivationsLayer.AdvancedActivationsLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.CoreLayer.CoreLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.ConvolutionalLayer.ConvolutionalLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.EmbeddingLayer.EmbeddingLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.LocallyConnectedLayer.LocallyConnectedLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.NoiseLayer.NoiseLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.NormalizationLayer.NormalizationLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.PoolingLayer.PoolingLayerButton;
import DeepVisual.UInterface.UIactions.LayerPane.RecurrentLayer.RecurrentLayerButton;
import javafx.scene.control.Button;
import javafx.scene.input.*;

public class LayerPaneButton extends Button {
    private CoreLayerButton coreLayBtn;
    private ConvolutionalLayerButton convolutionalLayBtn;
    private PoolingLayerButton poolingLayBtn;
    private LocallyConnectedLayerButton locallyConnectedLayBtn;
    private RecurrentLayerButton recurrentLayBtn;
    private EmbeddingLayerButton embeddingLayBtn;
    //private MergeLayerButton mergeLayBtn;
    private AdvancedActivationsLayerButton advancedActivationsLayBtn;
    private NormalizationLayerButton normalizationLayBtn;
    private NoiseLayerButton noiseLayBtn;

    private LayerPaneButton layer_btn_list[];
    private int layer_btn_num;

    protected DeepVisualWindowController _parent;
    protected int layer_btn_index;
    protected int layer_btn_index_default;
    protected boolean is_folded;
    protected boolean has_child;

    protected String layer_type;

    public LayerPaneButton() {
        this.has_child = false;
        this.is_folded = true;
        this.getStyleClass().add("LayerPaneButton");

        initLayerBtnSize();
        layer_type = "";
    }

    public LayerPaneButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;
        initLayerBtnList();
    }

    private void initLayerBtnList() {  // 初始化神经网络层按钮
        this.has_child = true;
        coreLayBtn                = new CoreLayerButton(_parent);                 // Core
        convolutionalLayBtn       = new ConvolutionalLayerButton(_parent);        // Convolutional
        poolingLayBtn             = new PoolingLayerButton(_parent);              // Pooling
        locallyConnectedLayBtn    = new LocallyConnectedLayerButton(_parent);     // Locally-Connected
        recurrentLayBtn           = new RecurrentLayerButton(_parent);            // Recurrent
        embeddingLayBtn           = new EmbeddingLayerButton(_parent);            // Embedding
        advancedActivationsLayBtn = new AdvancedActivationsLayerButton(_parent);  // AdvancedActivations
        normalizationLayBtn       = new NormalizationLayerButton(_parent);        // Normalization
        noiseLayBtn               = new NoiseLayerButton(_parent);                // Noise

        layer_btn_list = new LayerPaneButton[]{
                coreLayBtn,
                convolutionalLayBtn,
                poolingLayBtn,
                locallyConnectedLayBtn,
                recurrentLayBtn,
                embeddingLayBtn,
                advancedActivationsLayBtn,
                normalizationLayBtn,
                noiseLayBtn
        };
        layer_btn_num = layer_btn_list.length;

        // 设置每个按钮的标签号，方便之后面板中添加与删除操作
        for(int i = 0; i < layer_btn_list.length; i ++) {
            layer_btn_list[i].layer_btn_index = i;  // 此处 i 范围为 (0~layer_type-1), VBox 中第一个按钮的 index 值为 0
            layer_btn_list[i].layer_btn_index_default = i;
        }
    }

    // 子类继承函数
    protected void initLayerBtnSize() {  // 初始化按钮大小
        this.setMaxWidth(Double.MAX_VALUE);
        this.setPrefHeight(30);
        this.setMinHeight(USE_PREF_SIZE);
    }

    // 用于母层按钮
    protected void foldChildLayer(LayerPaneButton child_layer_list[]) {  // 展开或折叠子按钮
        if(has_child) {
            if(is_folded) {
                for(int i = 0; i < child_layer_list.length; i ++) {
                    child_layer_list[i].setLayBtnIndex(layer_btn_index + 1 + i);
                    _parent.addLayerBtn(child_layer_list[i].getLayBtnIndex(), child_layer_list[i]);
                }
                _parent.indexUpdate_FoldBtn(layer_btn_index_default+1, child_layer_list.length, true);
                is_folded = false;
            }
            else {
                for(int i = 0; i < child_layer_list.length; i ++) {
                    child_layer_list[i].setLayBtnIndex(-1);
                    _parent.removeLayerBtn(layer_btn_index + 1);
                }
                _parent.indexUpdate_FoldBtn(layer_btn_index_default+1, child_layer_list.length, false);
                is_folded = true;
            }
        }
    }

    // 用于子层按钮
    protected void childBtnDragDetected() {  // drag detected
        //System.out.println("drag detected");
        Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(getLayer_type());
        dragboard.setContent(content);
    }
    protected void childBtnDragDone() {  // drag done
        //System.out.println("drag done");
    }

    // 对外接口，主要用于添加与删除
    public String getLayer_type() {
        return layer_type;
    }

    public LayerPaneButton[] getLayer_btn_list() {
        return layer_btn_list;
    }

    public int getLayer_btn_num() {
        return layer_btn_num;
    }

    public void setLayBtnIndex(int index) {
        this.layer_btn_index = index;
    }

    public int getLayBtnIndex() {
        return layer_btn_index;
    }
}
