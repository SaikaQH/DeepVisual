package DeepVisual.UInterface.UIactions.CompileAndFitPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute.CFAttribute;

public class FitFunction extends CFBtn {
    private String func_name = "Fit";
    private String func_type = "Fit";

    public FitFunction() {
        initNNAttribute();
    }

    public FitFunction(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;
    }

    // ----------------------属性---------------------------------
    private final int _x = 1;  // String
    private final int _y = 2;  // String
    private final int _batch_size = 3;  //
    private final int _epochs = 4;  //
    private final int _verbose = 5;  //
    private final int _callbacks = 6;  // callbacks
    private final int _validation_split = 7;  // 0~1
    private final int _validation_data = 8;  //
    private final int _shuffle = 9;  //  boolean or string
    private final int _class_weight = 10;  //
    private final int _sample_weight = 11;  //
    private final int _initial_epoch = 12;  //
    private final int _steps_per_epoch = 13;  //
    private final int _validation_steps = 14;  //

    public void initNNAttribute() {  // 初始化compile的各项参数
        attr_list = new CFAttribute[] {
                //
                new CFAttribute("function name", "Text", func_name, this),

                new CFAttribute("x(Training data)",  "String",    "None", this, true),
                new CFAttribute("y(Training label)", "String",    "None", this, true),
                new CFAttribute("batch_size",        "int",       "32",   this),
                new CFAttribute("epochs",             "int",       "10",   this),
                new CFAttribute("verbose",           "ChoiceBox", "0",    this),
                new CFAttribute("callbacks",         "Callbacks", "None", this),
                new CFAttribute("validation_split",  "float",     "None", this),
                new CFAttribute("validation_data",   "String",    "None", this),
                new CFAttribute("shuffle",           "String",    "None", this),
                new CFAttribute("class_weight",      "String",    "None", this),
                new CFAttribute("sample_weight",     "String",    "None", this),
                new CFAttribute("initial_epoch",     "int",       "None", this),
                new CFAttribute("steps_per_epoch",   "String",    "None", this),
                new CFAttribute("validation_steps",  "String",    "None", this),
        };
    }

    public void change_dataset(String dataset_name) {
        if      (dataset_name.equals("cifar10")) {
            //
        }
        else if (dataset_name.equals("cifar100")) {
            //
        }
        else if (dataset_name.equals("imdb")) {
            //
        }
        else if (dataset_name.equals("reuters")) {
            //
        }
        else if (dataset_name.equals("mnist")) {
            attr_list[_x].setAttribute_value("x_train");
            attr_list[_y].setAttribute_value("y_train");
        }
        else if (dataset_name.equals("fashion_mnist")) {
            attr_list[_x].setAttribute_value("x_train");
            attr_list[_y].setAttribute_value("y_train");
        }
        else if (dataset_name.equals("boston_housing")) {
            //
        }
    }
}
