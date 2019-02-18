package DeepVisual.UInterface.UIactions.NNBuilderPane.PoolingNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class PoolingNN extends NNBuilderButton {
    private MaxPooling1DNN child_MaxPooling1D;
    private MaxPooling2DNN child_MaxPooling2D;
    private MaxPooling3DNN child_MaxPooling3D;
    private AveragePooling1DNN child_AveragePooling1D;
    private AveragePooling2DNN child_AveragePooling2D;
    private AveragePooling3DNN child_AveragePooling3D;
    private GlobalMaxPooling1DNN child_GlobalMaxPooling1D;
    private GlobalAveragePooling1DNN child_GlobalAveragePooling1D;
    private GlobalMaxPooling2DNN child_GlobalMaxPooling2D;
    private GlobalAveragePooling2DNN child_GlobalAveragePooling2D;
    private GlobalMaxPooling3DNN child_GlobalMaxPooling3D;
    private GlobalAveragePooling3DNN child_GlobalAveragePooling3D;

    private PoolingNN child_NN_list[];

    public PoolingNN() {
        this.setText("< Pooling >");
        this.getStyleClass().add("Pooling");
        //this.NN_type += "Core - ";
    }

    public PoolingNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {

        child_MaxPooling1D              = new MaxPooling1DNN();              // MaxPooling1D
        child_MaxPooling2D              = new MaxPooling2DNN();              // MaxPooling2D
        child_MaxPooling3D              = new MaxPooling3DNN();              // MaxPooling3D
        child_AveragePooling1D          = new AveragePooling1DNN();          // AveragePooling1D
        child_AveragePooling2D          = new AveragePooling2DNN();          // AveragePooling2D
        child_AveragePooling3D          = new AveragePooling3DNN();          // AveragePooling3D
        child_GlobalMaxPooling1D        = new GlobalMaxPooling1DNN();        // GlobalMaxPooling1D
        child_GlobalAveragePooling1D    = new GlobalAveragePooling1DNN();    // GlobalAveragePooling1D
        child_GlobalMaxPooling2D        = new GlobalMaxPooling2DNN();        // GlobalMaxPooling2D
        child_GlobalAveragePooling2D    = new GlobalAveragePooling2DNN();    // GlobalAveragePooling2D
        child_GlobalMaxPooling3D        = new GlobalMaxPooling3DNN();        // GlobalMaxPooling3D
        child_GlobalAveragePooling3D    = new GlobalAveragePooling3DNN();    // GlobalAveragePooling3D

        child_NN_list = new PoolingNN[]{
                child_MaxPooling1D,
                child_MaxPooling2D,
                child_MaxPooling3D,
                child_AveragePooling1D,
                child_AveragePooling2D,
                child_AveragePooling3D,
                child_GlobalMaxPooling1D,
                child_GlobalAveragePooling1D,
                child_GlobalMaxPooling2D,
                child_GlobalAveragePooling2D,
                child_GlobalMaxPooling3D,
                child_GlobalAveragePooling3D
        };
    }

    public PoolingNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
