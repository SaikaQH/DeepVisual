package DeepVisual.UInterface.UIactions.LayerPane.LocallyConnectedLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LocallyConnectedLayerButton extends LayerPaneButton {
    private LocallyConnected1DLayer child_LocallyConnected1D;
    private LocallyConnected2DLayer child_LocallyConnected2D;

    private LocallyConnectedLayerButton child_layer_List[];

    public LocallyConnectedLayerButton() {
        this.setText("< Locally-Connected >");
        layer_type += "LocallyConnected";
        this.getStyleClass().add("LocallyConnected");
    }

    public LocallyConnectedLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("LocallyConnectedLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_LocallyConnected1D = new LocallyConnected1DLayer();  // LocallyConnected1D
        child_LocallyConnected2D = new LocallyConnected2DLayer();  // LocallyConnected2D

        child_layer_List = new LocallyConnectedLayerButton[]{
                child_LocallyConnected1D,
                child_LocallyConnected2D
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
