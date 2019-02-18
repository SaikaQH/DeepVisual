package DeepVisual.UInterface.UIactions.LayerPane.AdvancedActivationsLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AdvancedActivationsLayerButton extends LayerPaneButton {
    private LeakyReLULayer child_LeakyReLU;
    private PReLULayer child_PReLU;
    private ELULayer child_ELU;
    private ThresholdedReLULayer child_ThresholdedReLU;
    private SoftmaxLayer child_Softmax;
    private ReLULayer child_ReLU;

    private AdvancedActivationsLayerButton child_layer_List[];

    public AdvancedActivationsLayerButton() {
        this.setText("< Advanced Activations >");
        layer_type += "AdvancedActivations";
        this.getStyleClass().add("AdvancedActivations");
    }

    public AdvancedActivationsLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("AdvancedActivationsLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_LeakyReLU       = new LeakyReLULayer();
        child_PReLU           = new PReLULayer();
        child_ELU             = new ELULayer();
        child_ThresholdedReLU = new ThresholdedReLULayer();
        child_Softmax         = new SoftmaxLayer();
        child_ReLU            = new ReLULayer();

        child_layer_List = new AdvancedActivationsLayerButton[]{
                child_LeakyReLU,
                child_PReLU,
                child_ELU,
                child_ThresholdedReLU,
                child_Softmax,
                child_ReLU
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
