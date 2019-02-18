package DeepVisual.UInterface.UIactions.CompileAndFitPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute.CFAttribute;

public class CFBtn {
    DeepVisualWindowController _parent;

    protected CFAttribute attr_list[];

    public CFBtn() {}

    public CFAttribute[] getAttr_list() {
        return attr_list;
    }

    public DeepVisualWindowController get_parent() {
        return _parent;
    }
}
