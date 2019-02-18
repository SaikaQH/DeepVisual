package DeepVisual.UInterface.UIactions.NNBuilderPane.EmbeddingNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class EmbeddingNN extends NNBuilderButton {
    private EmbeddingLayerNN child_Embedding;

    private EmbeddingNN child_NN_list[];

    public EmbeddingNN() {
        this.setText("< Embedding >");
        this.getStyleClass().add("Embedding");
        //this.NN_type += "Core - ";
    }

    public EmbeddingNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_Embedding = new EmbeddingLayerNN();

        child_NN_list = new EmbeddingNN[]{
                child_Embedding
        };
    }

    public EmbeddingNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
