package DeepVisual.UInterface.UIactions.NNBuilderPane.NormalizationNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class NormalizationNN extends NNBuilderButton {
    private BatchNormalizationNN child_BatchNormalization;

    private NormalizationNN child_NN_list[];

    public NormalizationNN() {
        this.setText("< Normalization >");
        this.getStyleClass().add("Normalization");
        //this.NN_type += "Core - ";
    }

    public NormalizationNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_BatchNormalization = new BatchNormalizationNN();

        child_NN_list = new NormalizationNN[]{
                child_BatchNormalization
        };
    }

    public NormalizationNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
