package DeepVisual.UInterface.UIactions.LayerPane.PoolingLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class GlobalMaxPooling3DLayer extends PoolingLayerButton {
    public GlobalMaxPooling3DLayer() {
        this.setText("GlobalMaxPooling3D");
        addDragDropEvent();

        layer_type += ".GlobalMaxPooling3D";
        this.getStyleClass().add("GlobalMaxPooling3D");
        this.getStyleClass().add("ChildLayer");
    }

    public GlobalMaxPooling3DLayer(DeepVisualWindowController _parent) {
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
