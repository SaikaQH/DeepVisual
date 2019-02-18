package DeepVisual.UInterface.UIactions.NNBuilderPane.NoiseNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class NoiseNN extends NNBuilderButton {
    private GaussianNoiseNN child_GaussianNoise;
    private GaussianDropoutNN child_GaussianDropout;
    private AlphaDropoutNN child_AlphaDropout;

    private NoiseNN child_NN_list[];

    public NoiseNN() {
        this.setText("< Noise >");
        this.getStyleClass().add("Noise");
        //this.NN_type += "Core - ";
    }

    public NoiseNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {

        child_GaussianNoise   = new GaussianNoiseNN();
        child_GaussianDropout = new GaussianDropoutNN();
        child_AlphaDropout    = new AlphaDropoutNN();

        child_NN_list = new NoiseNN[]{
                child_GaussianNoise,
                child_GaussianDropout,
                child_AlphaDropout
        };
    }

    public NoiseNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
