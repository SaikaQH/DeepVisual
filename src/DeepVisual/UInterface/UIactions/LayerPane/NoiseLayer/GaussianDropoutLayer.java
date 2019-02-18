package DeepVisual.UInterface.UIactions.LayerPane.NoiseLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

public class GaussianDropoutLayer extends NoiseLayerButton {
    public GaussianDropoutLayer() {
        this.setText("GaussianDropout");
        addDragDropEvent();

        layer_type += ".GaussianDropout";
        this.getStyleClass().add("GaussianDropout");
        this.getStyleClass().add("ChildLayer");
    }

    public GaussianDropoutLayer(DeepVisualWindowController _parent) {
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
