package DeepVisual.UInterface.UIactions.LayerPane.NoiseLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class AlphaDropoutLayer extends NoiseLayerButton {
    public AlphaDropoutLayer() {
        this.setText("AlphaDropout");
        addDragDropEvent();

        layer_type += ".AlphaDropout";
        this.getStyleClass().add("AlphaDropout");
        this.getStyleClass().add("ChildLayer");
    }

    public AlphaDropoutLayer(DeepVisualWindowController _parent) {
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
