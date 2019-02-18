package DeepVisual.UInterface.UIactions.AttributePane;

public class Initializers {
    String initializers_list[];

    public Initializers() {
        initIinitializers();
    }

    private void initIinitializers() {
        initializers_list = new String[] {
                "\'zeros\'",
                "\'ones\'",
                "\'constant\'",
                "\'random_normal\'",
                "\'random_uniform\'",
                "\'truncated_normal\'",
                "\'variance_scaling\'",
                "\'orthogonal\'",
                "\'identity\'",
                "\'lecun_uniform\'",
                "\'glorot_normal\'",
                "\'glorot_uniform\'",
                "\'he_normal\'",
                "\'lecun_normal\'",
                "\'he_uniform\'",
                "None"
        };
    }

    public String[] getInitializers_list() {
        return initializers_list;
    }
}
