package DeepVisual.UInterface.UIactions.NNBuilderPane.AdvancedActivationsNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class AdvancedActivationsNN extends NNBuilderButton {
    private LeakyReLUNN child_LeakyReLU;
    private PReLUNN child_PReLU;
    private ELUNN child_ELU;
    private ThresholdedReLUNN child_ThresholdedReLU;
    private SoftmaxNN child_Softmax;
    private ReLUNN child_ReLU;

    private AdvancedActivationsNN child_NN_list[];

    public AdvancedActivationsNN() {
        this.setText("< AdvancedActivations >");
        this.getStyleClass().add("AdvancedActivations");
        //this.NN_type += "Core - ";
    }

    public AdvancedActivationsNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_LeakyReLU       = new LeakyReLUNN();
        child_PReLU           = new PReLUNN();
        child_ELU             = new ELUNN();
        child_ThresholdedReLU = new ThresholdedReLUNN();
        child_Softmax         = new SoftmaxNN();
        child_ReLU            = new ReLUNN();

        child_NN_list = new AdvancedActivationsNN[]{
                child_LeakyReLU,
                child_PReLU,
                child_ELU,
                child_ThresholdedReLU,
                child_Softmax,
                child_ReLU
        };
    }

    public AdvancedActivationsNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
