package DeepVisual.UInterface.UIactions.LayerPane.NoiseLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NoiseLayerButton extends LayerPaneButton {
    private GaussianNoiseLayer child_GaussianNoise;
    private GaussianDropoutLayer child_GaussianDropout;
    private AlphaDropoutLayer child_AlphaDropout;

    private NoiseLayerButton child_layer_List[];

    public NoiseLayerButton() {
        this.setText("< Noise >");
        layer_type += "Noise";
        this.getStyleClass().add("Noise");
    }

    public NoiseLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("NoiseLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_GaussianNoise   = new GaussianNoiseLayer();
        child_GaussianDropout = new GaussianDropoutLayer();
        child_AlphaDropout    = new AlphaDropoutLayer();

        child_layer_List = new NoiseLayerButton[]{
                child_GaussianNoise,
                child_GaussianDropout,
                child_AlphaDropout
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
