package DeepVisual.UInterface.UIactions.LayerPane.EmbeddingLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EmbeddingLayerButton extends LayerPaneButton {
    private EmbeddingLayer child_Embedding;

    private EmbeddingLayerButton child_layer_List[];

    public EmbeddingLayerButton() {
        this.setText("< Embedding >");
        layer_type += "Embedding";
        this.getStyleClass().add("Embedding");
    }

    public EmbeddingLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("EmbeddingLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_Embedding = new EmbeddingLayer();  // Embedding

        child_layer_List = new EmbeddingLayerButton[]{
                child_Embedding
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
