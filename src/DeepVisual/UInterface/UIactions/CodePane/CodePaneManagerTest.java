package DeepVisual.UInterface.UIactions.CodePane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute.CFAttribute;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.CompileFunction;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.FitFunction;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;
import DeepVisual.UInterface.UIactions.NNBuilderPane.connectLine;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileOutputStream;

public class CodePaneManagerTest {
    private DeepVisualWindowController _parent;
    private TextArea codePane;

    private final String import_model = "from keras.models import Model\n";
    private final String init_compile_str = "# compile\n";
    private final String init_fit_str = "# fit\n";

    private CodeNodeTest code_list;
    private CodeNodeTest import_model_node;
    private CodeNodeTest import_datasets_node;
    private CodeNodeTest model_func_node;
    private CodeNodeTest compile_func_node;
    private CodeNodeTest fit_func_node;

    private CodeNodeTest end_of_import;
    private CodeNodeTest start_of_layer;
    private CodeNodeTest dataset_node;
    private CodeNodeTest end_of_layer;

    public CodePaneManagerTest() {
    }

    public CodePaneManagerTest(TextArea codePane) {
        this();
        this.codePane = codePane;
        initCodePane();
        initCodeList();
        writeCode();
    }

    public CodePaneManagerTest(TextArea codePane, DeepVisualWindowController _parent) {
        this(codePane);
        this._parent = _parent;
    }

    private void initCodePane() {
        clearCodePane();
    }

    private void initCodeList() {
        code_list = import_model_node = new CodeNodeTest(import_model);
        import_model_node.next = import_datasets_node = new CodeNodeTest("# import datasets\n");
        import_datasets_node.next = end_of_import = new CodeNodeTest("\n");
        end_of_import.next = dataset_node = new CodeNodeTest("# datasets\n");
        dataset_node.next = start_of_layer = new CodeNodeTest("\n");
        start_of_layer.next = end_of_layer = new CodeNodeTest("\n");
        end_of_layer.next = model_func_node = new CodeNodeTest("# model\n");
        model_func_node.next = compile_func_node = new CodeNodeTest(init_compile_str);
        compile_func_node.next = fit_func_node = new CodeNodeTest(init_fit_str);
    }

    public void clearAll() {
        initCodePane();
        initCodeList();
    }

    private boolean set_other_dataset = false;
    public void setDatasets(String datasets_name, String datasets_code) {
        if(datasets_name.equals("other")) {
            set_other_dataset = true;
            import_model_node.next = end_of_import;  // 去掉import_datasets_node
            end_of_import.next = start_of_layer.next;  // 去掉dataset_node和原始start_of_layer
            start_of_layer = end_of_import;
            writeCode();
            return;
        }
        if(set_other_dataset) {
            set_other_dataset = false;
            import_model_node.next = import_datasets_node;  // 连接import_datasets_node
            start_of_layer = new CodeNodeTest("\n");  // 创建新的start_of_layer并连接上去
            start_of_layer.next = end_of_import.next;
            end_of_import.next = dataset_node;  // 连接dataset_node
        }
        import_datasets_node.code_str = "from keras.datasets import " + datasets_name + "\n";
        dataset_node.code_str = "(x_train, y_train), (x_test, y_test) = " + datasets_code + "\n";
        writeCode();
    }

    public void addNNLayer(NNBuilderButton layer) {
        // 添加import
        if(code_list.code_str.equals(import_model)) {
            CodeNodeTest tempNode = new CodeNodeTest("from keras.layers import ");
            tempNode.next = new CodeNodeTest(layer.getNN_type());
            tempNode.next.next = new CodeNodeTest("\n");
            tempNode.next.next.next = code_list;
            code_list = tempNode;
        }
        else {
            CodeNodeTest i = code_list;
            boolean is_found = false;
            for(; ! i.next.code_str.equals("\n"); i = i.next) {
                if(i.next.code_str.endsWith(layer.getNN_type())) {
                    is_found = true;
                    break;
                }
            }
            if(! is_found) {
                CodeNodeTest tempNode = new CodeNodeTest(", " + layer.getNN_type());
                tempNode.next = i.next;
                i.next = tempNode;
            }
        }

        // 生成代码
        String code_str;
        code_str = layer.getNN_name() + " = " + layer.getNN_type() + "(";

        NNAttribute layer_attr[] = layer.getAttr_list();
        boolean not_first_attr = false;
        for(int i = 0; i < layer_attr.length; i ++) {
            if(layer_attr[i].getAttribute_name().equals("name") || layer_attr[i].getAttribute_name().equals("type")) continue;
            if(layer_attr[i].getAttribute_value().equals("None") || layer_attr[i].getAttribute_value().equals("False")) continue;
            if(not_first_attr) code_str += ", ";
            if(layer_attr[i].getFlag_not_show_name()) code_str += layer_attr[i].getAttribute_value();
            else code_str += layer_attr[i].getAttribute_name() + "=" + layer_attr[i].getAttribute_value();
            not_first_attr = true;
        }
        code_str += ")\n";

        // 添加到整体代码中
        CodeNodeTest temp = new CodeNodeTest("\n");
        temp.next = end_of_layer.next;
        end_of_layer.code_str = code_str;
        end_of_layer.nodeBtn = layer;
        end_of_layer = end_of_layer.next = temp;  // 这样可以保证end_of_layer后面是model_func_node

        writeCode();
    }

    public void removeNNLayer(NNBuilderButton layer) {
        CodeNodeTest before_target = null;
        String target_name = layer.getNN_name();
        boolean is_found = false;
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            is_found = i.next.isSame(layer);
            if(is_found) {
                i.next = i.next.next;
                break;
            }

            /*if(i.next.code_str.startsWith(target_name+" =")) {
                before_target = i;
                is_found = true;
            }
            if(i.next.code_str.endsWith("("+target_name+")\n")) {
                i.next.code_str = i.next.code_str.replace("("+target_name+")", "");
            }*/
        }
        /*if(is_found) {
            before_target.next = before_target.next.next;
        }*/
        refreshInputAndOutput();
        writeCode();
    }

    public void addCLine(connectLine c_line) {
        NNBuilderButton s_btn = c_line.getStartPoint().get_host();
        NNBuilderButton e_btn = c_line.getEndPoint().get_host();
        CodeNodeTest startLayer = null, endLayer = null, before_endLayer = null;
        boolean s_found = false, e_found = false;
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.isSame(s_btn)) {
                s_found = true;
                startLayer = i.next;
            }
            if(i.next.isSame(e_btn)) {
                e_found = true;
                before_endLayer = i;
                endLayer = i.next;
            }
            if(s_found && e_found) break;
        }
        /*String s_name = c_line.getStartPoint().get_host().getNN_name();
        String e_name = c_line.getEndPoint().get_host().getNN_name();
        CodeNodeTest startLayer = null, endLayer = null, before_endLayer = null;
        boolean s_found = false, e_found = false;
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.code_str.startsWith(s_name+" ")) {
                startLayer = i.next;
                s_found = true;
            }
            if(i.next.code_str.startsWith(e_name+" ")) {
                before_endLayer = i;
                endLayer = i.next;
                e_found = true;
            }
            if(s_found && e_found) break;
        }*/
        if(s_found && e_found) {
            endLayer.code_str = endLayer.code_str.replace("\n", "("+s_btn.getNN_name()+")\n");
            before_endLayer.next = endLayer.next;
            endLayer.next = startLayer.next;
            startLayer.next = endLayer;
        }
        refreshInputAndOutput();
        writeCode();
    }

    public void removeCLine(connectLine c_line) {
        NNBuilderButton s_btn = c_line.getStartPoint().get_host();
        NNBuilderButton e_btn = c_line.getEndPoint().get_host();
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.isSame(e_btn)) {
                i.next.code_str = i.next.code_str.replace("("+s_btn.getNN_name()+")", "");
                break;
            }
        }
        /*String s_name = c_line.getStartPoint().get_host().getNN_name();
        String e_name = c_line.getEndPoint().get_host().getNN_name();
        CodeNodeTest targetLayer = null;
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.code_str.startsWith(e_name+" =")) {
                targetLayer = i.next;
                break;
            }
        }
        if(targetLayer.code_str.endsWith("("+s_name+")\n")) {
            targetLayer.code_str = targetLayer.code_str.replace("("+s_name+")", "");
        }*/
        refreshInputAndOutput();
        writeCode();
    }

    public void changeName(NNBuilderButton layer, NNAttribute layerAttr, String oldName, String newName) {  // 有待修改
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            i.next.code_str = i.next.code_str.replace(oldName+" =", newName+" =");
            i.next.code_str = i.next.code_str.replace("("+oldName+")", "("+newName+")");
        }
        writeCode();
    }

    public void changeAttribute(NNBuilderButton layer, NNAttribute layerAttr, String oldValue, String newValue) {
        // System.out.println("" + layer.getNN_name() + ", " + layerAttr.getAttribute_name() + ", " + oldValue + ", " + newValue);
        CodeNodeTest targetLayer = null;
        for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.isSame(layer)) {
                targetLayer = i.next;
                break;
            }
        }
        /*for(CodeNodeTest i = start_of_layer; i.next != null; i = i.next) {
            if(i.next.code_str.startsWith(layer.getNN_name()+" ")) {
                targetLayer = i.next;
                break;
            }
        }*/

        int index_s = -1, index_e = -1;
        int num_left = 0;
        char[] codestr = targetLayer.code_str.toCharArray();
        for(int i = 0; i < codestr.length; i ++) {
            if(codestr[i] == '(') {
                if(index_s == -1 && num_left == 0) index_s = i;
                else num_left ++;
            }
            else if(codestr[i] == ')') {
                if(index_e == -1 && num_left == 0) index_e = i+1;
                else num_left --;
            }
        }

        String code_str;
        //code_str = layer.getNN_name() + " = " + layer.getNN_type() + "(";
        code_str = "(";

        NNAttribute layer_attr[] = layer.getAttr_list();
        boolean not_first_attr = false;
        for(int i = 0; i < layer_attr.length; i ++) {
            if(layer_attr[i].getAttribute_name().equals("name") || layer_attr[i].getAttribute_name().equals("type")) continue;
            if(layer_attr[i].getAttribute_value().equals("None") || layer_attr[i].getAttribute_value().equals("False")) continue;
            if(not_first_attr) code_str += ", ";
            if(layer_attr[i].getFlag_not_show_name()) code_str += layer_attr[i].getAttribute_value();
            else code_str += layer_attr[i].getAttribute_name() + "=" + layer_attr[i].getAttribute_value();
            not_first_attr = true;
        }
        code_str += ")";

        //System.out.println(targetLayer.code_str.substring(index_s, index_e));
        targetLayer.code_str = targetLayer.code_str.replace(targetLayer.code_str.substring(index_s, index_e), code_str);

        writeCode();
    }

    private void refreshInputAndOutput() {
        String input_str = ""; int input_num = 0;
        String output_str = ""; int output_num = 0;

        NNBuilderButton NN_list[] = _parent.getNN_list();
        int NN_num = _parent.getTotal_NN();

        for(int i = 0; i < NN_num; i ++) {
            if(NN_list[i].getIn_source_num() == 0 && NN_list[i].getOut_target_num() == 0) continue;
            if(NN_list[i].getIn_source_num() == 0) {
                if(input_num != 0) input_str += ",";
                input_str += NN_list[i].getNN_name();
                input_num ++;
            }
            if(NN_list[i].getOut_target_num() == 0) {
                if(output_num != 0) output_str += ",";
                output_str += NN_list[i].getNN_name();
                output_num ++;
            }
        }

        //System.out.println(input_str + ", " + input_num);
        //System.out.println(output_str + ", " + output_num);

        String model_str = "model = Model(";
        if(input_num > 1) model_str += "inputs=[" + input_str + "], ";
        else if(input_num == 1) model_str += "inputs=" + input_str + ", ";
        else if(input_num == 0) return ;
        if(output_num > 1) model_str += "outputs=[" + output_str + "])\n";
        else if(output_num == 1) model_str += "outputs=" + output_str + ")\n";
        else if(output_num == 0) return ;

        //System.out.println(model_str);

        model_func_node.code_str = model_str;

        /*
        CodeNodeTest i = start_of_layer;
        boolean has_found = false;
        for(; i.next != null; i = i.next) {
            if(i.next.code_str.startsWith("model = Model(")) {
                i.next.code_str = model_str;
                has_found = true;
            }
        }
        if(! has_found) {
            i.next = new CodeNodeTest("\n");
            i.next.next = new CodeNodeTest(model_str);
        }*/

        if(compile_func_node.code_str.equals(init_compile_str))
            updateCompileAndFit();
    }

    private void updateCompileAndFit() {
        updateCompileFunction();
        updateFitFunction();

        writeCode();
    }

    private void updateCompileFunction() {
        CompileFunction func = _parent.getCompileFunc();

        String func_str = "model.compile(";

        CFAttribute func_attr[] = func.getAttr_list();
        boolean not_first_attr = false;
        for(int i = 0; i < func_attr.length; i ++) {
            if(func_attr[i].getAttribute_name().equals("function name") || func_attr[i].getAttribute_name().equals("type")) continue;
            if(func_attr[i].getAttribute_value().equals("None") || func_attr[i].getAttribute_value().equals("False")) continue;
            if(not_first_attr) func_str += ", ";
            if(func_attr[i].getFlag_not_show_name()) func_str += func_attr[i].getAttribute_value();
            else func_str += func_attr[i].getAttribute_name() + "=" + func_attr[i].getAttribute_value();
            not_first_attr = true;
        }
        func_str += ")\n";

        compile_func_node.code_str = func_str;
    }

    private void updateFitFunction() {
        FitFunction func = _parent.getFitFunc();

        String func_str = "model.fit(";

        CFAttribute func_attr[] = func.getAttr_list();
        boolean not_first_attr = false;
        for(int i = 0; i < func_attr.length; i ++) {
            if(func_attr[i].getAttribute_name().equals("function name") || func_attr[i].getAttribute_name().equals("type")) continue;
            if(func_attr[i].getAttribute_value().equals("None") || func_attr[i].getAttribute_value().equals("False")) continue;
            if(not_first_attr) func_str += ", ";
            if(func_attr[i].getFlag_not_show_name()) func_str += func_attr[i].getAttribute_value();
            else func_str += func_attr[i].getAttribute_name() + "=" + func_attr[i].getAttribute_value();
            not_first_attr = true;
        }
        func_str += ")\n";

        fit_func_node.code_str = func_str;
    }

    public void changeCFAttribute(CFAttribute CFAttr, String oldValue, String newValue) {
        updateCompileAndFit();

        writeCode();
    }

    private void clearCodePane() {
        codePane.setText("# This is for test\n");
    }

    private void writeCode() {
        clearCodePane();
        for(CodeNodeTest i = code_list; i != null; i = i.next) {
            codePane.appendText(i.code_str);
        }
    }

    public void export(File file) throws Exception {
        FileOutputStream fstream = new FileOutputStream(file);
        for(CodeNodeTest i = code_list; i != null; i = i.next) {
            fstream.write(i.code_str.getBytes());
        }
        fstream.close();
    }

}

class CodeNodeTest{
    NNBuilderButton nodeBtn;
    CodeNodeTest next;
    String code_str;

    CodeNodeTest() {
        nodeBtn = null;
        next = null;
        code_str = "";
    }

    CodeNodeTest(String code_str) {
        this();
        this.code_str = code_str;
    }

    CodeNodeTest(String code_str, NNBuilderButton nodeBtn) {
        this(code_str);
        this.nodeBtn = nodeBtn;
    }

    boolean isSame(NNBuilderButton target) {
        return nodeBtn == target;
    }

    /* abandoned
    void addNode(CodeNodeTest new_node) {
        if(next == null)
            next = new_node;
        else
            next.addNode(new_node);
    }*/
}
