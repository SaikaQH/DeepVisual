package DeepVisual.UInterface.UIactions.LayerPane.NormalizationLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NormalizationLayerButton extends LayerPaneButton {
    private BatchNormalizationLayer child_BatchNormalization;

    private NormalizationLayerButton child_layer_List[];

    public NormalizationLayerButton() {
        this.setText("< Normalization >");
        layer_type += "Normalization";
        this.getStyleClass().add("Normalization");
    }

    public NormalizationLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("NormalizationLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_BatchNormalization = new BatchNormalizationLayer();

        child_layer_List = new NormalizationLayerButton[]{
                child_BatchNormalization
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
