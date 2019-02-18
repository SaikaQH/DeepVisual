package DeepVisual.UInterface.UIactions.LayerPane.ConvolutionalLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConvolutionalLayerButton extends LayerPaneButton {
    private Conv1DLayer child_Conv1D;
    private Conv2DLayer child_Conv2D;
    private SeparableConv1DLayer child_SeparableConv1D;
    private SeparableConv2DLayer child_SeparableConv2D;
    private Conv2DTransposeLayer child_Conv2DTranspose;
    private Conv3DLayer child_Conv3D;
    private Cropping1DLayer child_Cropping1D;
    private Cropping2DLayer child_Cropping2D;
    private Cropping3DLayer child_Cropping3D;
    private UpSampling1DLayer child_UpSampling1D;
    private UpSampling2DLayer child_UpSampling2D;
    private UpSampling3DLayer child_UpSampling3D;
    private ZeroPadding1DLayer child_ZeroPadding1D;
    private ZeroPadding2DLayer child_ZeroPadding2D;
    private ZeroPadding3DLayer child_ZeroPadding3D;

    private ConvolutionalLayerButton child_layer_List[];

    public ConvolutionalLayerButton() {
        this.setText("< Convolutional >");
        layer_type += "Convolutional";
        this.getStyleClass().add("Convolutional");
    }

    public ConvolutionalLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("ConvolutionalLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_Conv1D = new Conv1DLayer();
        child_Conv2D = new Conv2DLayer();
        child_SeparableConv1D = new SeparableConv1DLayer();
        child_SeparableConv2D = new SeparableConv2DLayer();
        child_Conv2DTranspose = new Conv2DTransposeLayer();
        child_Conv3D = new Conv3DLayer();
        child_Cropping1D = new Cropping1DLayer();
        child_Cropping2D = new Cropping2DLayer();
        child_Cropping3D = new Cropping3DLayer();
        child_UpSampling1D = new UpSampling1DLayer();
        child_UpSampling2D = new UpSampling2DLayer();
        child_UpSampling3D = new UpSampling3DLayer();
        child_ZeroPadding1D = new ZeroPadding1DLayer();
        child_ZeroPadding2D = new ZeroPadding2DLayer();
        child_ZeroPadding3D = new ZeroPadding3DLayer();

        child_layer_List = new ConvolutionalLayerButton[]{
                child_Conv1D,
                child_Conv2D,
                child_SeparableConv1D,
                child_SeparableConv2D,
                child_Conv2DTranspose,
                child_Conv3D,
                child_Cropping1D,
                child_Cropping2D,
                child_Cropping3D,
                child_UpSampling1D,
                child_UpSampling2D,
                child_UpSampling3D,
                child_ZeroPadding1D,
                child_ZeroPadding2D,
                child_ZeroPadding3D
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
