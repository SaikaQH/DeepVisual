package DeepVisual.UInterface.UIactions.NNBuilderPane.ConvolutionalNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class ConvolutionalNN extends NNBuilderButton {
    private Conv1DNN child_Conv1D;
    private Conv2DNN child_Conv2D;
    private SeparableConv1DNN child_SeparableConv1D;
    private SeparableConv2DNN child_SeparableConv2D;
    private Conv2DTransposeNN child_Conv2DTranspose;
    private Conv3DNN child_Conv3D;
    private Cropping1DNN child_Cropping1D;
    private Cropping2DNN child_Cropping2D;
    private Cropping3DNN child_Cropping3D;
    private UpSampling1DNN child_UpSampling1D;
    private UpSampling2DNN child_UpSampling2D;
    private UpSampling3DNN child_UpSampling3D;
    private ZeroPadding1DNN child_ZeroPadding1D;
    private ZeroPadding2DNN child_ZeroPadding2D;
    private ZeroPadding3DNN child_ZeroPadding3D;

    private ConvolutionalNN child_NN_list[];

    public ConvolutionalNN() {
        this.setText("< Convolutional >");
        this.getStyleClass().add("Convolutional");
        //this.NN_type += "Convolutional - ";
    }

    public ConvolutionalNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {

        child_Conv1D = new Conv1DNN();
        child_Conv2D = new Conv2DNN();
        child_SeparableConv1D = new SeparableConv1DNN();
        child_SeparableConv2D = new SeparableConv2DNN();
        child_Conv2DTranspose = new Conv2DTransposeNN();
        child_Conv3D = new Conv3DNN();
        child_Cropping1D = new Cropping1DNN();
        child_Cropping2D = new Cropping2DNN();
        child_Cropping3D = new Cropping3DNN();
        child_UpSampling1D = new UpSampling1DNN();
        child_UpSampling2D = new UpSampling2DNN();
        child_UpSampling3D = new UpSampling3DNN();
        child_ZeroPadding1D = new ZeroPadding1DNN();
        child_ZeroPadding2D = new ZeroPadding2DNN();
        child_ZeroPadding3D = new ZeroPadding3DNN();

        child_NN_list = new ConvolutionalNN[]{
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

    public ConvolutionalNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
