package DeepVisual.UInterface.UIactions.LayerPane.PoolingLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PoolingLayerButton extends LayerPaneButton {
    private MaxPooling1DLayer child_MaxPooling1D;
    private MaxPooling2DLayer child_MaxPooling2D;
    private MaxPooling3DLayer child_MaxPooling3D;
    private AveragePooling1DLayer child_AveragePooling1D;
    private AveragePooling2DLayer child_AveragePooling2D;
    private AveragePooling3DLayer child_AveragePooling3D;
    private GlobalMaxPooling1DLayer child_GlobalMaxPooling1D;
    private GlobalAveragePooling1DLayer child_GlobalAveragePooling1D;
    private GlobalMaxPooling2DLayer child_GlobalMaxPooling2D;
    private GlobalAveragePooling2DLayer child_GlobalAveragePooling2D;
    private GlobalMaxPooling3DLayer child_GlobalMaxPooling3D;
    private GlobalAveragePooling3DLayer child_GlobalAveragePooling3D;

    private PoolingLayerButton child_layer_List[];

    public PoolingLayerButton() {
        this.setText("< Pooling >");
        layer_type += "Pooling";
        this.getStyleClass().add("Pooling");
    }

    public PoolingLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("PoolingLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_MaxPooling1D              = new MaxPooling1DLayer();              // MaxPooling1D
        child_MaxPooling2D              = new MaxPooling2DLayer();              // MaxPooling2D
        child_MaxPooling3D              = new MaxPooling3DLayer();              // MaxPooling3D
        child_AveragePooling1D          = new AveragePooling1DLayer();          // AveragePooling1D
        child_AveragePooling2D          = new AveragePooling2DLayer();          // AveragePooling2D
        child_AveragePooling3D          = new AveragePooling3DLayer();          // AveragePooling3D
        child_GlobalMaxPooling1D        = new GlobalMaxPooling1DLayer();        // GlobalMaxPooling1D
        child_GlobalAveragePooling1D    = new GlobalAveragePooling1DLayer();    // GlobalAveragePooling1D
        child_GlobalMaxPooling2D        = new GlobalMaxPooling2DLayer();        // GlobalMaxPooling2D
        child_GlobalAveragePooling2D    = new GlobalAveragePooling2DLayer();    // GlobalAveragePooling2D
        child_GlobalMaxPooling3D        = new GlobalMaxPooling3DLayer();        // GlobalMaxPooling3D
        child_GlobalAveragePooling3D    = new GlobalAveragePooling3DLayer();    // GlobalAveragePooling3D

        child_layer_List = new PoolingLayerButton[]{
                child_MaxPooling1D,
                child_MaxPooling2D,
                child_MaxPooling3D,
                child_AveragePooling1D,
                child_AveragePooling2D,
                child_AveragePooling3D,
                child_GlobalMaxPooling1D,
                child_GlobalAveragePooling1D,
                child_GlobalMaxPooling2D,
                child_GlobalAveragePooling2D,
                child_GlobalMaxPooling3D,
                child_GlobalAveragePooling3D
        };
    }

    private void addLayBtnEvent() {
        // 鼠标点击事件，点击展开/折叠子层
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                foldChildLayer(child_layer_List);  // 继承自LayerPaneButton
            }
        });
    }
}
