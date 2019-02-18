package DeepVisual.UInterface.UIactions.CompileAndFitPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute.CFAttribute;

public class CompileFunction extends CFBtn{
    private String func_name = "Compile";
    private String func_type = "Compile";

    public CompileFunction() {
        initNNAttribute();
    }

    public CompileFunction(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------
    private final int _optimizer = 1;  // Optimizer
    private final int _loss = 2;  // Loss
    private final int _metrics = 3;  // String  ???
    private final int _loss_weights = 4;  // String  ???
    private final int _sample_weight_mode = 5;  // String  ???  temporal or None  ???
    private final int _weighted_metrics = 6;  // String
    private final int _target_tensors = 7;  // String
    private final int _kwargs = 8; // String

    public void initNNAttribute() {  // 初始化compile的各项参数
        attr_list = new CFAttribute[] {
                //
                new CFAttribute("function name", "Text", func_name, this),

                new CFAttribute("optimizer",          "Optimizers", "\'sgd\'",                this),
                new CFAttribute("loss",               "Losses",     "\'mean_squared_error\'", this),
                new CFAttribute("metrics",            "String",     "None",                   this),
                new CFAttribute("loss_weights",       "String",     "None",                   this),
                new CFAttribute("sample_weight_mode", "String",     "None",                   this),
                new CFAttribute("weighted_metrics",   "String",     "None",                   this),
                new CFAttribute("target_tensors",     "String",     "None",                   this),
        };
    }

    public void change_dataset(String dataset_name) {
        if(dataset_name.equals("mnist")) {
            attr_list[_loss].setAttribute_value("\'categorical_crossentropy\'");
            attr_list[_metrics].setAttribute_value("[\'accuracy\']");
        }
    }
}
