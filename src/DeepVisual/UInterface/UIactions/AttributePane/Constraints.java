package DeepVisual.UInterface.UIactions.AttributePane;

public class Constraints {
    private String constraints_list[];

    public Constraints() {
        initConstraints();
    }

    private void initConstraints() {
        constraints_list = new String[] {
                "\'max_norm\'",
                "\'non_neg\'",
                "\'unit_norm\'",
                "\'min_max_norm\'",
                "None"
        };
    }

    public String[] getConstraints_list() {
        return constraints_list;
    }
}
