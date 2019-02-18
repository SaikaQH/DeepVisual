package DeepVisual.UInterface.UIactions.LayerPane.CoreLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CoreLayerButton extends LayerPaneButton {
    private InputLayer child_Input;
    private DenseLayer child_Dense;
    private ActivationLayer child_Activation;
    private DropoutLayer child_Dropout;
    private FlattenLayer child_Flatten;
    private ReshapeLayer child_Reshape;

    private CoreLayerButton child_layer_List[];

    public CoreLayerButton() {
        this.setText("< Core >");
        layer_type += "Core";
        this.getStyleClass().add("Core");
    }

    public CoreLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("CoreLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_Input         = new InputLayer();         // Input
        child_Dense         = new DenseLayer();         // Dense
        child_Activation    = new ActivationLayer();    // Activation
        child_Dropout       = new DropoutLayer();       // Dropout
        child_Flatten       = new FlattenLayer();       // Flatten
        child_Reshape       = new ReshapeLayer();       // Reshape

        child_layer_List = new CoreLayerButton[]{
                child_Input,
                child_Dense,
                child_Activation,
                child_Dropout,
                child_Flatten,
                child_Reshape
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
