package DeepVisual.UInterface.UIactions;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;
import DeepVisual.UInterface.UIactions.NNBuilderPane.connectLine;
import DeepVisual.UInterface.UIactions.NNBuilderPane.sideButton;
import com.intellij.execution.configurations.GeneralCommandLine;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class ImportCodeManagerWithProcess {
    private String keras_process_address = "C:\\Users\\tencon2010\\AppData\\Local\\conda\\conda\\envs\\tensorflow\\python.exe";
    //private String keras_process_address = "";

    public void setKeras_process_address(String keras_process_address) {
        this.keras_process_address = keras_process_address;
    }

    private DeepVisualWindowController _parent;

    private double default_width;
    private double default_height;
    private double pos_x, pos_y;
    private final double btn_gap = 20.0;
    public ImportCodeManagerWithProcess() {
        default_width = new NNBuilderButton().getBtn_width();
        default_height = new NNBuilderButton().getBtn_height();
        pos_x = default_width;
        pos_y = default_height;
    }

    public ImportCodeManagerWithProcess(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;
    }

    public void choosePythonFile() {
        //_parent.clearAll();

        //GeneralCommandLine cl = new GeneralCommandLine();
        //System.out.println(cl.getCharset());

        if(keras_process_address.equals("")) {
            Alert _alert = new Alert(Alert.AlertType.INFORMATION);
            _alert.setTitle("ERROR");
            _alert.setHeaderText("Error");
            _alert.setContentText("You have not set Keras Runner");
            //_alert.initOwner(new Stage());
            _alert.showAndWait();
            _parent.changeTabPane(_parent._SETTINGS_PANE_INDEX);
            return ;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Keras file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Keras Files", "*.py"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        File tempPyFile = createTempPyFile(selectedFile);
        runCodeFile(tempPyFile);
    }

    private File createTempPyFile(File file) {
        File tempPyFile = null;
        try {
            tempPyFile = File.createTempFile("tempPyFile", ".py");
            //System.out.println(tempPyFile.getAbsolutePath());

            FileReader freader = new FileReader(file);
            BufferedReader in = new BufferedReader(freader);
            FileWriter fwriter = new FileWriter(tempPyFile);
            BufferedWriter out = new BufferedWriter(fwriter);

            for(String line = in.readLine(); line != null; line = in.readLine()) {
                //System.out.println(line);
                if(line.startsWith("print")
                        || line.contains(".fit(")
                        || line.contains(".save(")
                        || line.contains(".save_weights(")
                        || line.contains(".evaluate("))
                    continue;
                out.write(line);
                out.newLine();
                out.flush();
            }
            out.write("print(model.get_config())");
            out.newLine();
            out.flush();

            in.close();
            out.close();
            freader.close();
            fwriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempPyFile;
    }

    private void runCodeFile(File file) {
        String file_address = file.getAbsolutePath();
        file_address = file_address.replace('\\', '/');
        //System.out.println(file_address);

        GeneralCommandLine gc = new GeneralCommandLine();

        //System.out.println("start");
        Process pr = null;
        try {
            //System.out.println("The file address is : " + file_address);
            String command_code = keras_process_address + " " + file_address;
            //System.out.println("Running...");
            //_parent.controlLoadingKerasFileFog(true);
            pr = Runtime.getRuntime().exec(command_code);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = "";
            String config_line = "";
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                config_line += line;
            }
            in.close();
            pr.waitFor();
            //System.out.println("end");
            //_parent.controlLoadingKerasFileFog(false);

            file.delete();
            analyzeConfiguration(config_line);
            setNodePosition();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean not_model = true;
    private void analyzeConfiguration(String layer_config) {
        //System.out.println(layer_config);
        char[] conf = layer_config.toCharArray();

        boolean flag_layer = false;

        String word = "";

        for(int i = 0; i < conf.length; i ++) {
            if((conf[i] >= 'a' && conf[i] <= 'z')
                || (conf[i] >= 'A' && conf[i] <= 'Z')
                || (conf[i] >= '0' && conf[i] <= '9')
                || conf[i] == '_'
                || conf[i] == '.')
                word += conf[i];
            else if(conf[i] == '\'') {
                for(i ++; conf[i] != '\''; i ++) {
                    word += conf[i];
                }
                continue;
            }
            else if(conf[i] == ':') {
                if(word.equals("layers")) {
                    flag_layer = true;
                    not_model = false;
                }
                word = "";
            }
            else if(conf[i] == '[') {
                flag_layer = true;
            }
            else if(conf[i] == ',') {
                word = "";
            }
            else if(conf[i] == '{') {
                if(flag_layer) {
                    int num_left_parenthesis = 1;
                    String one_layer_code = "";
                    for(i ++; ; i ++) {
                        if(conf[i] == '{') num_left_parenthesis ++;
                        else if(conf[i] == '}') num_left_parenthesis --;
                        if(num_left_parenthesis == 0) break;
                        one_layer_code += conf[i];
                    }
                    //System.out.println(one_layer_code);
                    createLayerButton(one_layer_code);
                    one_layer_code = "";
                }
            }
            else if(conf[i] == ' ')
                continue;
        }
    }

    private String last_nnbtn = "";
    private LIST l_list = new LIST();
    private void createLayerButton(String layer_code) {
        //System.out.println(layer_code);

        char[] code = layer_code.toCharArray();

        String word = "";
        String[] attr = {"", ""};
        String attr_code = "";

        NNBuilderButton nnbtn = null;

        boolean flag_config = false;

        for(int i = 0; i < code.length; i ++) {
            if (code[i] == '\'') {
                for(i ++; code[i] != '\''; i ++)
                    word += code[i];
            }
            else if(code[i] == '{') {
                if(attr[0].equals("config")) {
                    attr[0] = attr[1] = "";
                    flag_config = true;
                }
                int num_left_parenthesis = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '{') num_left_parenthesis ++;
                    else if(code[i] == '}') num_left_parenthesis --;
                    if(num_left_parenthesis == 0) break;
                    word += code[i];
                }
                attr_code = word;
                word = "";
                if(flag_config)
                    analyzeAttribute(attr_code, nnbtn);
                //System.out.println(attr_code);
            }
            else if(code[i] == '[') {
                int num_left_square = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '[') num_left_square ++;
                    else if(code[i] == ']') num_left_square --;
                    if(num_left_square == 0) break;
                    word += code[i];
                }
            }
            else if(code[i] == '(') {
                int num_left_round = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '(') num_left_round ++;
                    else if(code[i] == ')') num_left_round --;
                    if(num_left_round == 0) break;
                    word += code[i];
                }
                word = "(" + word + ")";
            }
            else if(code[i] == ':') {
                attr[0] = word;
                word = "";
            }
            else if(code[i] == ',') {
                attr[1] = word;
                word = "";
                if(attr[0].equals("class_name")) {
                    if(attr[1].equals("InputLayer"))
                        attr[1] = "Input";
                    attr[1] = get_full_layer_type(attr[1]);
                    //System.out.println(attr[1]);
                    nnbtn = _parent.createNNBtn(attr[1], pos_x, pos_y);
                }
                //System.out.println(attr[0] + ": " + attr[1]);
                attr[0] = attr[1] = "";
            }
            if(i == code.length - 1) {
                attr[1] = word;
                if(!last_nnbtn.equals("") && not_model) {
                    String end_node = nnbtn.getNN_name();
                    String start_node = last_nnbtn;
                    l_list.add(start_node, end_node);
                }
                if(attr[0].equals("inbound_nodes") && !attr[1].equals("")) {
                    String end_node = nnbtn.getNN_name();
                    String start_node = analyzeInboundNodes(attr[1]);
                    //System.out.println(start_node + " ==> " + end_node);
                    l_list.add(start_node, end_node);
                }
                last_nnbtn = nnbtn.getNN_name();
                //System.out.println(attr[0] + ": " + attr[1]);
            }
        }
        if(nnbtn != null) {
            _parent.addNNBtn(nnbtn);
            pos_x += default_width + btn_gap;
            pos_y += default_height + btn_gap;
        }
        //System.out.println("----------------------------------------------------------------------------------------------");
    }

    private void analyzeAttribute(String attr_code, NNBuilderButton nn) {
        //System.out.println(attr_code);

        char[] code = attr_code.toCharArray();

        String word = "";
        String[] attr = {"", ""};

        for(int i = 0; i < code.length; i ++) {
            if((code[i] >= 'a' && code[i] <= 'z')
                    || (code[i] >= 'A' && code[i] <= 'Z')
                    || (code[i] >= '0' && code[i] <= '9')
                    || code[i] == '_'
                    || code[i] == '.')
                word += code[i];
            else if (code[i] == '\'') {
                for(i ++; code[i] != '\''; i ++)
                    word += code[i];
            }
            else if(code[i] == '{') {
                int num_left_parenthesis = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '{') num_left_parenthesis ++;
                    else if(code[i] == '}') num_left_parenthesis --;
                    if(num_left_parenthesis == 0) break;
                    word += code[i];
                }
            }
            else if(code[i] == '[') {
                int num_left_square = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '[') num_left_square ++;
                    else if(code[i] == ']') num_left_square --;
                    if(num_left_square == 0) break;
                    word += code[i];
                }
            }
            else if(code[i] == '(') {
                int num_left_round = 1;
                for(i ++; ; i ++) {
                    if(code[i] == '(') num_left_round ++;
                    else if(code[i] == ')') num_left_round --;
                    if(num_left_round == 0) break;
                    word += code[i];
                }
                word = "(" + word + ")";
            }
            else if(code[i] == ':') {
                attr[0] = word;
                word = "";
            }
            else if(code[i] == ',') {
                attr[1] = word;
                word = "";
                if(attr[0].equals("activation") || attr[0].equals("data_format") || attr[0].equals("padding"))
                    attr[1] = "\'" + attr[1] + "\'";
                if(attr[1].startsWith("\'class_name\': "))
                    attr[1] = simplifySubClass(attr[1]);
                //System.out.println(attr[0] + ": " + attr[1]);
                nn.setAttribute(attr[0], attr[1]);
                if(attr[0].equals("name"))
                    nn.setNN_name(attr[1]);
                attr[0] = attr[1] = "";
            }
            if(i == code.length - 1) {
                attr[1] = word;
                if(attr[0].equals("activation") || attr[0].equals("data_format") || attr[0].equals("padding"))
                    attr[1] = "\'" + attr[1] + "\'";
                if(attr[1].startsWith("\'class_name\': "))
                    attr[1] = simplifySubClass(attr[1]);
                //System.out.println(attr[0] + ": " + attr[1]);
                nn.setAttribute(attr[0], attr[1]);
                if(attr[0].equals("name"))
                    nn.setNN_name(attr[1]);
            }
        }
    }

    private final String keyword_layer[] = {
            /* Core */                  "Input", "Dense", "Activation", "Dropout", "Flatten", "Reshape",
            /* Convolutional */         "Conv1D", "Conv2D", "SeparableConv1D", "SeparableConv2D", "Conv2DTranspose", "Conv3D", "Cropping1D", "Cropping2D", "Cropping3D", "UpSampling1D", "UpSampling2D", "UpSampling3D", "ZeroPadding1D", "ZeroPadding2D", "ZeroPadding3D",
            /* Pooling */               "MaxPooling1D", "MaxPooling2D", "MaxPooling3D", "AveragePooling1D", "AveragePooling2D", "AveragePooling3D", "GlobalMaxPooling1D", "GlobalAveragePooling1D", "GlobalMaxPooling2D", "GlobalAveragePooling2D", "GlobalMaxPooling3D", "GlobalAveragePooling3D",
            /* Locally-Connected */     "LocallyConnected1D", "LocallyConnected2D",
            /* Recurrent */             "SimpleRNN", "GRU", "LSTM", "ConvLSTM2D", "SimpleRNNCell", "GRUCell", "LSTMCell", "CuDNNGRU", "CuDNNLSTM",
            /* Embedding */             "Embedding",
            /* Advanced Activation */   "LeakyReLU", "PReLU", "ELU", "ThresholdedReLU", "Softmax", "ReLU",
            /* Normalization */         "BatchNormalization",
            /* Noise */                 "GaussianNoise", "GaussianDropout", "AlphaDropout"
    };
    private String get_full_layer_type(String type) {  // 获取层的所属类型，由于绘图时创建层的函数需要层所属的类型，为了复用绘图的创建函数，需要用该函数来获取所属类型
        String type_str = "";

        for(int i = 0; i < keyword_layer.length; i ++) {
            if(type.equals(keyword_layer[i])) {
                if      (i <  6) type_str = "Core";
                else if (i < 21) type_str = "Convolutional";
                else if (i < 33) type_str = "Pooling";
                else if (i < 35) type_str = "LocallyConnected";
                else if (i < 44) type_str = "Recurrent";
                else if (i < 45) type_str = "Embedding";
                else if (i < 51) type_str = "AdvancedActivations";
                else if (i < 52) type_str = "Normalization";
                else if (i < 55) type_str = "Noise";
            }
        }

        type_str += "." + type;
        return type_str;
    }

    private String simplifySubClass(String full_class) {
        char[] code = full_class.toCharArray();

        String sub_class = "";

        for(int i = 15; code[i] != '\''; i ++) {
            if(code[i] >= 'A' && code[i] <= 'Z') {
                if(i == 15)
                    code[i] += 32;
                else {
                    sub_class += '_';
                    code[i] += 32;
                }
            }
            sub_class += code[i];
        }

        sub_class = "\'" + sub_class + "\'";

        return sub_class;
    }

    private String analyzeInboundNodes(String inbound_code) {
        char[] code = inbound_code.toCharArray();

        String inbound_layer = "";

        for(int i = 3; code[i] != '\''; i ++) {
            inbound_layer += code[i];
        }

        return inbound_layer;
    }

    private final int up_btn_index = 0;
    private final int down_btn_index = 1;
    private final int left_btn_index = 2;
    private final int right_btn_index = 3;
    private void setNodePosition() {
        int[] node_level = new int[l_list.count_node];
        for(int i = 0; i < l_list.count_node; i ++) {
            node_level[i] = -1;
        }
        Queue<String> queue = new LinkedList<String>();

        for(int i = 0; i < l_list.count_node; i ++) {
            if(l_list.count_in[i] == 0) {
                node_level[i] = 0;
                queue.offer(l_list.node[i]);
            }
        }

        while(!queue.isEmpty()) {
            String t_node = queue.poll();
            int t = l_list.findNode(t_node);
            if(t != -1) {
                for(int j = l_list.head[t]; j != -1; j =l_list.line[j].next) {
                    int to_node = l_list.line[j].to;
                    if(node_level[to_node] == -1) {
                        node_level[to_node] = node_level[t] + 1;  // --------------------------------------------最短优先，可以考虑变成最长优先
                        queue.offer(l_list.node[to_node]);
                    }
                }
            }
        }

        int[] level_num = new int[l_list.count_node];
        for(int i = 0; i < l_list.count_node; i ++) {
            level_num[i] = 0;
        }
        pos_x = default_width; pos_y = default_height;
        for(int i = 0; i < l_list.count_node; i ++) {
            NNBuilderButton temp_nnbtn = _parent.findNNBtn(l_list.node[i]);
            if(temp_nnbtn != null) {
                temp_nnbtn.setBtn_position(pos_x + level_num[node_level[i]] * (default_width + btn_gap),
                        pos_y + node_level[i] * (default_height + btn_gap));
                level_num[node_level[i]] ++;
            }
        }

        for(int i = 0; i < l_list.count_node; i ++) {
            NNBuilderButton start_nnbtn = _parent.findNNBtn(l_list.node[i]);
            sideButton s_sBtn = start_nnbtn.getSideBtn(down_btn_index);
            for(int j = l_list.head[i]; j != -1; j = l_list.line[j].next) {
                NNBuilderButton end_nnbtn = _parent.findNNBtn(l_list.node[l_list.line[j].to]);
                sideButton e_sBtn = end_nnbtn.getSideBtn(up_btn_index);
                connectLine c_line = new connectLine(s_sBtn, e_sBtn);
                _parent.addConnectLine(c_line);
            }
        }
    }
}

class LIST {
    LINE[] line = new LINE[5];
    int count_line = 0;

    int[] head = {-1, -1, -1, -1, -1};
    String[] node = {"", "", "", "", ""};
    int[] count_in = {0, 0, 0, 0, 0};
    int count_node = 0;

    LIST() {
        for(int i = 0; i < line.length; i ++) {
            line[i] = new LINE();
        }
    }

    void add(String s, String e) {
        int index_s = addNode(s), index_e = addNode(e);
        addLine(index_s, index_e);
    }

    int addNode(String n) {
        for(int i = 0; i < count_node; i ++) {
            if(node[i].equals(n))
                return i;
        }

        if(count_node >= node.length) {
            int[] temp_head = new int[2 * count_node + 1];
            String[] temp_node = new String[2 * count_node + 1];
            int[] temp_count_in = new int[2 * count_node + 1];
            for(int i = 0; i < temp_node.length; i ++) {
                temp_head[i] = -1;
                temp_node[i] = "";
                temp_count_in[i] = 0;
            }
            for(int i = 0; i < count_node; i ++) {
                temp_head[i] = head[i];
                temp_node[i] = node[i];
                temp_count_in[i] = count_in[i];
            }
            head = temp_head;
            node = temp_node;
            count_in = temp_count_in;
        }
        node[count_node] = n;
        count_node ++;
        return count_node-1;
    }

    void addLine(int s, int e) {
        if(count_line >= line.length) {
            LINE[] temp_line = new LINE[2 * count_line + 1];
            for(int i = 0; i < temp_line.length; i ++) {
                temp_line[i] = new LINE();
            }
            for(int i = 0; i < count_line; i ++) {
                temp_line[i] = line[i];
            }
            line = temp_line;
        }
        line[count_line].to = e;
        line[count_line].next = head[s];
        head[s] = count_line;
        count_in[e] ++;
        count_line ++;
    }

    int findNode(String n) {
        for(int i = 0; i < count_node; i ++) {
            if(node[i].equals(n))
                return i;
        }
        return -1;
    }
}

class LINE {
    int to, next;

    LINE() {
        to = -1;
        next = -1;
    }
}
