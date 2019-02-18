package DeepVisual.UInterface.UIactions.NNBuilderPane.LocallyConnectedNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class LocallyConnectedNN extends NNBuilderButton {
    private LocallyConnected1DNN child_LocallyConnected1D;
    private LocallyConnected2DNN child_LocallyConnected2D;

    private LocallyConnectedNN child_NN_list[];

    public LocallyConnectedNN() {
        this.setText("< LocallyConnected >");
        this.getStyleClass().add("LocallyConnected");
        //this.NN_type += "Core - ";
    }

    public LocallyConnectedNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_LocallyConnected1D = new LocallyConnected1DNN();
        child_LocallyConnected2D = new LocallyConnected2DNN();

        child_NN_list = new LocallyConnectedNN[]{
                child_LocallyConnected1D,
                child_LocallyConnected2D
        };
    }

    public LocallyConnectedNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
