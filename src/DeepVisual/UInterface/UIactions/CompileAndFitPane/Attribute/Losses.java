package DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute;

public class Losses {
    String losses_list[];

    public Losses() {
        initLosses();
    }

    private void initLosses() {
        losses_list = new String[] {
                "\'mean_squared_error\'",
                "\'mean_absolute_error\'",
                "\'mean_absolute_percentage_error\'",
                "\'mean_squared_logarithmic_error\'",
                "\'squared_hinge\'",
                "\'hinge\'",
                "\'categorical_hinge\'",
                "\'logcosh\'",
                "\'categorical_crossentropy\'",
                "\'sparse_categorical_crossentropy\'",
                "\'binary_crossentropy\'",
                "\'kullback_leibler_divergence\'",
                "\'poisson\'",
                "\'cosine_proximity\'"
        };
    }

    public String[] getLosses_list() {
        return losses_list;
    }
}
