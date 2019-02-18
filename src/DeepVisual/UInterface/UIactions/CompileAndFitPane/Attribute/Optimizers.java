package DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute;

public class Optimizers {
    String optimizers_list[];

    public Optimizers() {
        initOptimizers();
    }

    private void initOptimizers() {
        optimizers_list = new String[] {
                "\'sgd\'",
                "\'RMSprop\'",
                "\'Adagrad\'",
                "\'Adadelta\'",
                "\'Adam\'",
                "\'Adamax\'",
                "\'Nadam\'",
                "\'TFOptimizer\'"
        };
    }

    public String[] getInitializers_list() {
        return optimizers_list;
    }
}
