package DeepVisual.UInterface.UIactions.LayerPane.ConvolutionalLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class Cropping3DLayer extends ConvolutionalLayerButton {
    public Cropping3DLayer() {
        this.setText("Cropping3D");
        addDragDropEvent();

        layer_type += ".Cropping3D";
        this.getStyleClass().add("Cropping3D");
        this.getStyleClass().add("ChildLayer");
    }

    public Cropping3DLayer(DeepVisualWindowController _parent) {
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
