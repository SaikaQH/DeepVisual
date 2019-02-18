package DeepVisual.UInterface.UIactions.LayerPane.LocallyConnectedLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class LocallyConnected1DLayer extends LocallyConnectedLayerButton {
    public LocallyConnected1DLayer() {
        this.setText("LocallyConnected1D");
        addDragDropEvent();

        layer_type += ".LocallyConnected1D";
        this.getStyleClass().add("LocallyConnected1D");
        this.getStyleClass().add("ChildLayer");
    }

    public LocallyConnected1DLayer(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
    }

    private void addDragDropEvent() {  // 子层按钮的鼠标拖拽事件
        // 鼠标拖拽事件
        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                childBtnDragDetected();  // 继承自LayerPaneButton
            }
        });

        this.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                childBtnDragDone();  // 继承自LayerPaneButton
            }
        });
    }
}
