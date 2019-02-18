package DeepVisual.UInterface.UIactions.LayerPane.ConvolutionalLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class ZeroPadding2DLayer extends ConvolutionalLayerButton {
    public ZeroPadding2DLayer() {
        this.setText("ZeroPadding2D");
        addDragDropEvent();

        layer_type += ".ZeroPadding2D";
        this.getStyleClass().add("ZeroPadding2D");
        this.getStyleClass().add("ChildLayer");
    }

    public ZeroPadding2DLayer(DeepVisualWindowController _parent) {
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
