package DeepVisual.UInterface.UIactions.AttributePane;

public class Activations {
    String activations_list[];

    public Activations() {
        initActivations();
    }

    private void initActivations() {
        activations_list = new String[] {
                "\'softmax\'",
                "\'elu\'",
                "\'selu\'",
                "\'softsign\'",
                "\'relu\'",
                "\'tanh\'",
                "\'sigmoid\'",
                "\'hard_sigmoid\'",
                "\'exponential\'",
                "\'linear\'",
                "None"
        };
    }

    public String[] getActivations_list() {
        return activations_list;
    }
}
