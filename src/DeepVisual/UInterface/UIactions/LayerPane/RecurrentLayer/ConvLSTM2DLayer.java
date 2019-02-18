package DeepVisual.UInterface.UIactions.LayerPane.RecurrentLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class ConvLSTM2DLayer extends RecurrentLayerButton {
    public ConvLSTM2DLayer() {
        this.setText("ConvLSTM2D");
        addDragDropEvent();

        layer_type += ".ConvLSTM2D";
        this.getStyleClass().add("ConvLSTM2D");
        this.getStyleClass().add("ChildLayer");
    }

    public ConvLSTM2DLayer(DeepVisualWindowController _parent) {
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
