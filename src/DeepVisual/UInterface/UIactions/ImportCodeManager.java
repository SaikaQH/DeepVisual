package DeepVisual.UInterface.UIactions;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class ImportCodeManager {
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
    private final String keyword_keras[] = {
            "import", "from", "def", "if", "else", "print"
    };

    private DeepVisualWindowController _parent;

    private double default_width;
    private double default_height;
    private double pos_x, pos_y;
    private final double btn_gap = 20.0;

    public ImportCodeManager() {
        default_width = new NNBuilderButton().getBtn_width();
        default_height = new NNBuilderButton().getBtn_height();
        pos_x = default_width;
        pos_y = default_height;
    }

    public ImportCodeManager(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;
    }
}
/*
    public void choosePythonFile() {
        _parent.clearAll();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Keras file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Keras Files", "*.py"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        readCodeFile(selectedFile);
    }

    private void readCodeFile(File file) {
        try {
            FileReader freader= new FileReader(file);
            BufferedReader in = new BufferedReader(freader);

            int left_parenthesis_num = 0;
            String one_layer = "";
            for(String line = in.readLine(); line != null; line = in.readLine()) {
                if(left_parenthesis_num != 0) {
                    left_parenthesis_num += countLeftParenthesis(line);
                    one_layer += line;
                    continue;
                }

                if(! one_layer.equals("")) {
                    createNewLayer(one_layer);
                    one_layer = "";
                }

                if(line.startsWith(" ")) {
                    continue;
                }

                char[] codeLine = line.toCharArray();

                boolean start_next_line = false;

                //System.out.println(line);
                String word = "";
                for(int i = 0; i < codeLine.length; i ++) {
                    if ((codeLine[i] >= 'a' && codeLine[i] <= 'z')
                        || (codeLine[i] >= 'A' && codeLine[i] <= 'Z')
                        || (codeLine[i] >= '0' && codeLine[i] <= '9')
                        || codeLine[i] == '_') {
                        word += codeLine[i];
                    }
                    else if (codeLine[i] == '=') {
                        word = "";
                    }
                    else if (codeLine[i] == ' '
                            || codeLine[i] == '(') {
                        //System.out.println(word + " :  " + line);
                        if(in_keyword_list(word, keyword_keras)) {
                            start_next_line = true;
                            break;
                        }
                        if(in_keyword_list(word, keyword_layer)) {
                            left_parenthesis_num += countLeftParenthesis(line);
                            one_layer += line;
                            start_next_line = true;
                            break;
                        }
                        if(codeLine[i] == '(') {
                            start_next_line = true;
                            break;
                        }
                    }
                    else {
                        start_next_line = true;
                        break;
                    }
                }

                if(start_next_line) continue;
            }

            setNodePosition();

            in.close();
            freader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countLeftParenthesis(String line) {  // 数不匹配的左括号数，当返回值不为0时，表示该行中左括号与右括号不配对，该行并未结束
        char[] code = line.toCharArray();
        int num_left_parenthesis = 0;
        for(int i = 0; i < code.length; i ++) {
            if(code[i] == '(') num_left_parenthesis ++;
            else if(code[i] == ')') num_left_parenthesis --;
        }
        return num_left_parenthesis;
    }

    private boolean in_keyword_list(String word, String[] word_list) {  // 检测关键字
        for(int i = 0; i < word_list.length; i ++)
            if(word_list[i].equals(word))
                return true;
        return false;
    }

    private LIST l_list = new LIST();
    private void createNewLayer(String layer_code) {  // 创建新的神经网络层
        char[] code = layer_code.toCharArray();

        //System.out.println(layer_code);

        String layer_name = "";
        String layer_type = "";

        String attr[] = {"", ""};

        String word = "";
        int index_attribute = 0;
        int first_equal_sign = -1;
        int first_left_parenthesis = -1;
        int count_left_parenthesis = 0;

        NNBuilderButton nnbtn = null;

        for(int i = 0; i < code.length; i ++) {
            if ((code[i] >= 'a' && code[i] <= 'z')
                    || (code[i] >= 'A' && code[i] <= 'Z')
                    || (code[i] >= '0' && code[i] <= '9')
                    || code[i] == '_'
                    || code[i] == '[' || code[i] == ']'
                    || code[i] == '\'') {
                word += code[i];
            }
            else if (code[i] == '=') {
                if(first_equal_sign == -1) {
                    first_equal_sign = i;
                    layer_name = word;
                }
                else {
                    attr[0] = word;
                }
                word = "";
            }
            else if (code[i] == '(') {
                if(first_left_parenthesis == -1) {
                    first_left_parenthesis = i;
                    count_left_parenthesis ++;
                    layer_type = word;
                    layer_type = get_full_layer_type(layer_type);

                    //System.out.println(layer_type);
                    nnbtn = _parent.createNNBtn(layer_type, pos_x, pos_y);
                    //System.out.println(nnbtn.getNN_name());
                    nnbtn.setNN_name(layer_name);
                    nnbtn.setAttribute("name", layer_name);

                    word = "";
                }
                else if(count_left_parenthesis >= 1) {
                    word += code[i];
                    count_left_parenthesis ++;
                }
                else if(count_left_parenthesis == 0) {
                    // create connect line
                    String end_node = nnbtn.getNN_name();
                    String start_node = "";
                    for(i = i+1; code[i] != ')'; i ++) {
                        start_node += code[i];
                    }
                    //System.out.println(start_node + " ==> " + end_node);
                    l_list.add(start_node, end_node);
                }
            }
            else if (code[i] == ')') {
                if(count_left_parenthesis > 1) {
                    word += code[i];
                    count_left_parenthesis --;
                }
                else if(count_left_parenthesis == 1) {
                    count_left_parenthesis --;
                    if(word.equals("")) word = "None";
                    attr[1] = word;
                    word = "";
                    //System.out.println(attr[0] + ": " + attr[1]);
                    if(attr[0].equals(""))
                        nnbtn.setAttribute(index_attribute, attr[1]);
                    else
                        nnbtn.setAttribute(attr[0], attr[1]);
                    attr[0] = ""; attr[1] = "";
                }
            }
            else if (code[i] == ',') {
                if(count_left_parenthesis >= 2) {
                    word += code[i];
                }
                else if(count_left_parenthesis == 1) {
                    attr[1] = word;
                    word = "";
                    //System.out.println(attr[0] + ": " + attr[1]);
                    if(attr[0].equals(""))
                        nnbtn.setAttribute(index_attribute, attr[1]);
                    else
                        nnbtn.setAttribute(attr[0], attr[1]);
                    attr[0] = ""; attr[1] = "";
                }
            }
            else if (code[i] == ' ') {
                continue;
            }
        }
        if(nnbtn != null) {
            _parent.addNNBtn(nnbtn);
            pos_x += default_width + btn_gap;
            pos_y += default_height + btn_gap;
        }
    }

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
*/