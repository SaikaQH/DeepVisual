package DeepVisual.UInterface.UIactions.LayerPane.PoolingLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class MaxPooling3DLayer extends PoolingLayerButton {
    public MaxPooling3DLayer() {
        this.setText("MaxPooling3D");
        addDragDropEvent();

        layer_type += ".MaxPooling3D";
        this.getStyleClass().add("MaxPooling3D");
        this.getStyleClass().add("ChildLayer");
    }

    public MaxPooling3DLayer(DeepVisualWindowController _parent) {
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
