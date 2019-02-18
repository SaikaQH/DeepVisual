package DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class CoreNN extends NNBuilderButton {
    private DenseNN child_Dense;
    private ActivationNN child_Activation;
    private DropoutNN child_Dropout;
    private FlattenNN child_Flatten;
    private ReshapeNN child_Reshape;

    private CoreNN child_NN_list[];

    public CoreNN() {
        this.setText("< Core >");
        this.getStyleClass().add("Core");
        //this.NN_type += "Core - ";
    }

    public CoreNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_Dense = new DenseNN();
        child_Activation = new ActivationNN();
        child_Dropout = new DropoutNN();
        child_Flatten = new FlattenNN();
        child_Reshape = new ReshapeNN();

        child_NN_list = new CoreNN[]{
                child_Dense,
                child_Activation,
                child_Dropout,
                child_Flatten,
                child_Reshape
        };
    }

    public CoreNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
