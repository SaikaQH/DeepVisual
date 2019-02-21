package DeepVisual.UInterface;

import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;
import DeepVisual.UInterface.UIactions.CodePane.CodePaneManager;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute.CFAttribute;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.CompileFunction;
import DeepVisual.UInterface.UIactions.CompileAndFitPane.FitFunction;
import DeepVisual.UInterface.UIactions.ImportCodeManager;
import DeepVisual.UInterface.UIactions.ImportCodeManagerWithProcess;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import DeepVisual.UInterface.UIactions.NNBuilderPane.AdvancedActivationsNN.*;
import DeepVisual.UInterface.UIactions.NNBuilderPane.ConvolutionalNN.*;
import DeepVisual.UInterface.UIactions.NNBuilderPane.CoreNN.*;
import DeepVisual.UInterface.UIactions.NNBuilderPane.EmbeddingNN.EmbeddingLayerNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.LocallyConnectedNN.LocallyConnected1DNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.LocallyConnectedNN.LocallyConnected2DNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NoiseNN.AlphaDropoutNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NoiseNN.GaussianDropoutNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NoiseNN.GaussianNoiseNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.NormalizationNN.BatchNormalizationNN;
import DeepVisual.UInterface.UIactions.NNBuilderPane.PoolingNN.*;
import DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN.*;
import DeepVisual.UInterface.UIactions.NNBuilderPane.connectLine;
import DeepVisual.UInterface.UIactions.NNBuilderPane.sideButton;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;

public class DeepVisualWindowController {
    @FXML
    private TabPane mainTabPane;

    // 初始化窗口的各项元素
    public void initNNDrawer() {
        initImport();

        initDatasetsButton();
        initNeuralNetworkDrawerPane();
        initCompileAndFitPane();
        initCodePane();
        initSettingsPane();
    }

    // --------------------------------------------------- data sets 窗口（Tab 1）--------------------------------------------------
    @FXML
    private Button cifar10_dataset_btn;
    @FXML
    private Button cifar100_dataset_btn;
    @FXML
    private Button imdb_dataset_btn;
    @FXML
    private Button reuters_dataset_btn;
    @FXML
    private Button mnist_dataset_btn;
    @FXML
    private Button fashion_mnist_dataset_btn;
    @FXML
    private Button boston_housing_dataset_btn;
    @FXML
    private Button other_dataset_btn;

    private void initDatasetsButton() {
        cifar10_dataset_btn.getStyleClass().addAll("DatasetButton");
        cifar10_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "" +
                        "if K.image_data_format() == 'channels_first':\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 3, 32, 32)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 3, 32, 32)\n" +
                        "    input_shape = (3, 32, 32) #front\n" +
                        "else:\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 32, 32, 3)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 32, 32, 3)\n" +
                        "    input_shape = (32, 32, 3)  #last\n" +
                        "\n" +
                        "x_train = x_train.astype('float32')\n" +
                        "x_test = x_test.astype('float32')\n" +
                        "x_train /= 255\n" +
                        "x_test /= 255\n" +
                        "\n" +
                        "y_train = to_categorical(y_train, 10)\n" +
                        "y_test = to_categorical(y_test, 10)";

                setDatasets("cifar10", "cifar10.load_data()\n\n" + pre_processing_code);
                addCodePaneImportCode("from keras.utils import to_categorical\n");
                addCodePaneImportCode("from keras import backend as K\n");
                setSelectedDataset(cifar10_dataset_btn.getLayoutX(), cifar10_dataset_btn.getLayoutY());
            }
        });

        cifar100_dataset_btn.getStyleClass().addAll("DatasetButton");
        cifar100_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "" +
                        "if K.image_data_format() == 'channels_first':\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 3, 32, 32)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 3, 32, 32)\n" +
                        "    input_shape = (3, 32, 32) #front\n" +
                        "else:\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 32, 32, 3)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 32, 32, 3)\n" +
                        "    input_shape = (32, 32, 3)  #last\n" +
                        "\n" +
                        "x_train = x_train.astype('float32')\n" +
                        "x_test = x_test.astype('float32')\n" +
                        "x_train /= 255\n" +
                        "x_test /= 255\n" +
                        "\n" +
                        "y_train = to_categorical(y_train, 100)\n" +
                        "y_test = to_categorical(y_test, 100)";

                setDatasets("cifar100", "cifar100.load_data(label_mode=\'fine\')" + pre_processing_code);
                addCodePaneImportCode("from keras.utils import to_categorical\n");
                addCodePaneImportCode("from keras import backend as K\n");
                setSelectedDataset(cifar100_dataset_btn.getLayoutX(), cifar100_dataset_btn.getLayoutY());
            }
        });

        imdb_dataset_btn.getStyleClass().addAll("DatasetButton");
        imdb_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "" +
                        "max_features = 20000\n" +
                        "maxlen = 80\n" +
                        //"(x_train,y_train),(x_test,y_test) = imdb.load_data(num_words= max_features )   # max_features = 20000\n" +
                        "\n" +
                        "x_train = sequence .pad_sequences(x_train ,maxlen= maxlen )  # maxlen = 80\n" +
                        "x_test = sequence .pad_sequences(x_test ,maxlen= maxlen )\n";

                setDatasets("imdb", "imdb.load_data(num_words= max_features )" + pre_processing_code);// "imdb.load_data(path=\"imdb\", num_words=None, skip_top=0, maxlen=None, seed=113, start_char=1, oov_char=2, index_from=3)"
                addCodePaneImportCode("from keras.preprocessing import sequence\n");
                setSelectedDataset(imdb_dataset_btn.getLayoutX(), imdb_dataset_btn.getLayoutY());
            }
        });

        reuters_dataset_btn.getStyleClass().addAll("DatasetButton");
        reuters_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "" +
                        "max_features = 10000\n" +
                        "maxlen = 150\n" +
                        //"(x_train,y_train),(x_test,y_test) = reuters.load_data(num_words= max_features )   # max_features = 10000\n" +
                        "\n" +
                        "x_train = sequence .pad_sequences(x_train ,maxlen= maxlen )  # maxlen = 150\n" +
                        "x_test = sequence .pad_sequences(x_test ,maxlen= maxlen )   # maxlen = 150\n" +
                        "\n" +
                        "y_train = to_categorical(y_train,46)\n" +
                        "y_test = to_categorical(y_test,46)";

                setDatasets("reuters", "reuters.load_data(num_words=max_features)\n\n" + pre_processing_code); // reuters.load_data(path="reuters.npz", num_words=None, skip_top=0, maxlen=None, test_split=0.2, seed=113, start_char=1, oov_char=2, index_from=3)
                addCodePaneImportCode("from keras.preprocessing import sequence\n");
                addCodePaneImportCode("from keras.utils import to_categorical\n");
                setSelectedDataset(reuters_dataset_btn.getLayoutX(), reuters_dataset_btn.getLayoutY());
            }
        });

        mnist_dataset_btn.getStyleClass().addAll("DatasetButton");
        mnist_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "if K.image_data_format() == \'channels_first\':\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 1, 28, 28)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 1, 28, 28)\n" +
                        "    input_shape = (1, 28, 28)\n" +
                        "else:\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 28, 28, 1)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 28, 28, 1)\n" +
                        "    input_shape = (28, 28, 1)\n" +
                        "\n" +
                        "x_train = x_train.astype(\'float32\')\n" +
                        "x_test = x_test.astype(\'float32\')\n" +
                        "x_train /= 255\n" +
                        "x_test /= 255\n" +
                        "\n" +
                        "y_train = np_utils.to_categorical(y_train, 10)\n" +
                        "y_test = np_utils.to_categorical(y_test, 10)";

                setDatasets("mnist", "mnist.load_data()\n\n" + pre_processing_code);
                addCodePaneImportCode("from keras.utils import np_utils\n");
                addCodePaneImportCode("from keras import backend as K\n");
                setSelectedDataset(mnist_dataset_btn.getLayoutX(), mnist_dataset_btn.getLayoutY());
            }
        });

        fashion_mnist_dataset_btn.getStyleClass().addAll("DatasetButton");
        fashion_mnist_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "if K.image_data_format() == \'channels_first\':\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 1, 28, 28)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 1, 28, 28)\n" +
                        "    input_shape = (1, 28, 28)\n" +
                        "else:\n" +
                        "    x_train = x_train.reshape(x_train.shape[0], 28, 28, 1)\n" +
                        "    x_test = x_test.reshape(x_test.shape[0], 28, 28, 1)\n" +
                        "    input_shape = (28, 28, 1)\n" +
                        "\n" +
                        "x_train = x_train.astype(\'float32\')\n" +
                        "x_test = x_test.astype(\'float32\')\n" +
                        "x_train /= 255\n" +
                        "x_test /= 255\n" +
                        "\n" +
                        "y_train = np_utils.to_categorical(y_train, 10)\n" +
                        "y_test = np_utils.to_categorical(y_test, 10)";
                setDatasets("fashion_mnist", "fashion_mnist.load_data()" + pre_processing_code);
                addCodePaneImportCode("from keras.utils import np_utils\n");
                addCodePaneImportCode("from keras import backend as K\n");
                setSelectedDataset(fashion_mnist_dataset_btn.getLayoutX(), fashion_mnist_dataset_btn.getLayoutY());
            }
        });

        boston_housing_dataset_btn.getStyleClass().addAll("DatasetButton");
        boston_housing_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String pre_processing_code = "\n" +
                        "mean = x_train.mean(axis=0)  # 求均值\n" +
                        "x_train -= mean\n" +
                        "\n" +
                        "std = x_train.std(axis=0)  # 求标准差\n" +
                        "x_train /= std\n" +
                        "\n" +
                        "x_test -= mean\n" +
                        "x_test /= std";

                setDatasets("boston_housing", "boston_housing.load_data()" + pre_processing_code);
                setSelectedDataset(boston_housing_dataset_btn.getLayoutX(), boston_housing_dataset_btn.getLayoutY());
            }
        });

        other_dataset_btn.getStyleClass().addAll("DatasetButton");
        other_dataset_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setDatasets("other", "");
                setSelectedDataset(other_dataset_btn.getLayoutX(), other_dataset_btn.getLayoutY());
            }
        });
    }

    @FXML
    private Rectangle selectRec;
    @FXML
    private Rectangle selectBackgroundColor;
    private void setSelectedDataset(double layoutX, double layoutY) {
        selectRec.setVisible(true);
        selectRec.setLayoutX(layoutX - 10);
        selectRec.setLayoutY(layoutY - 10);

        selectBackgroundColor.setVisible(true);
        selectBackgroundColor.setLayoutX(layoutX);
        selectBackgroundColor.setLayoutY(layoutY);
    }

    private String dataset_name = "";

    private void setDatasets(String dataset_name, String dataset_code) {
        setDataset_name(dataset_name);
        setCodePaneDatasets(dataset_name, dataset_code);
    }

    private void setDataset_name(String d_name) {
        dataset_name = d_name;

        createInputOutputBtn(d_name);

        compileFunc.change_dataset(d_name);
        fitFunc.change_dataset(d_name);
        showCFBtnAttribute(null);
    }

    public String getDataset_name() {
        return dataset_name;
    }

    private NNBuilderButton data_input_btn = null;
    private NNBuilderButton data_output_btn = null;
    private NNBuilderButton data_embedding_btn = null;
    private connectLine line_input_embedding = null;
    private void createInputOutputBtn(String dataset_name) {
        if(line_input_embedding != null) {
            removeConnectLine(line_input_embedding);
            line_input_embedding = null;
        }
        if(data_input_btn != null) {
            removeNNBtn(data_input_btn);
            data_input_btn = null;
        }
        if(data_output_btn != null) {
            removeNNBtn(data_output_btn);
            data_output_btn = null;
        }
        if(data_embedding_btn != null) {
            removeNNBtn(data_embedding_btn);
            data_output_btn = null;
        }

        if      (dataset_name.equals("cifar10")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "input_shape");
            data_input_btn.setFlag_no_input(true);

            data_output_btn = createNNBtn("Core.Dense", 100, 150);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "10");
            data_output_btn.setAttribute("activation", "\'softmax\'");
            data_output_btn.setFlag_no_output(true);

            addNNBtn(data_input_btn);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("cifar100")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "input_shape");
            data_input_btn.setFlag_no_input(true);

            data_output_btn = createNNBtn("Core.Dense", 100, 150);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "100");
            data_output_btn.setAttribute("activation", "\'softmax\'");
            data_output_btn.setFlag_no_output(true);

            addNNBtn(data_input_btn);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("imdb")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "(maxlen,)");
            data_input_btn.setFlag_no_input(true);

            data_embedding_btn = createNNBtn("Embedding.Embedding", 100, 150);
            data_embedding_btn.setNN_name("embeddingLayer");
            data_embedding_btn.setAttribute("name", "embeddingLayer");
            data_embedding_btn.setAttribute("input_dim", "max_features");
            data_embedding_btn.setAttribute("output_dim", "128");

            line_input_embedding = new connectLine(data_input_btn.getSideBtn(data_input_btn.down_btn_index), data_embedding_btn.getSideBtn(data_embedding_btn.up_btn_index));

            data_output_btn = createNNBtn("Core.Dense", 100, 250);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "1");
            data_output_btn.setAttribute("activation", "\'sigmoid\'");
            data_output_btn.setFlag_no_output(true);

            addNNBtn(data_input_btn);
            addNNBtn(data_embedding_btn);
            addConnectLine(line_input_embedding);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("reuters")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "(maxlen,)");
            data_input_btn.setFlag_no_input(true);

            data_embedding_btn = createNNBtn("Embedding.Embedding", 100, 150);
            data_embedding_btn.setNN_name("embeddingLayer");
            data_embedding_btn.setAttribute("name", "embeddingLayer");
            data_embedding_btn.setAttribute("input_dim", "max_features");
            data_embedding_btn.setAttribute("output_dim", "128");

            line_input_embedding = new connectLine(data_input_btn.getSideBtn(data_input_btn.down_btn_index), data_embedding_btn.getSideBtn(data_embedding_btn.up_btn_index));

            data_output_btn = createNNBtn("Core.Dense", 100, 250);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "46");
            data_output_btn.setAttribute("activation", "\'softmax\'");
            data_output_btn.setFlag_no_output(true);

            addNNBtn(data_input_btn);
            addNNBtn(data_embedding_btn);
            addConnectLine(line_input_embedding);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("mnist")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "input_shape");
            data_input_btn.setFlag_no_input(true);

            data_output_btn = createNNBtn("Core.Dense", 100, 150);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "10");
            data_output_btn.setAttribute("activation", "\'softmax\'");
            data_output_btn.setFlag_no_output(true);
            //for(NNAttribute a:data_output_btn.getAttr_list()) {
            //    System.out.println(a.getAttribute_name() + ": " + a.getAttribute_value());
            //}

            addNNBtn(data_input_btn);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("fashion_mnist")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "input_shape");
            data_input_btn.setFlag_no_input(true);

            data_output_btn = createNNBtn("Core.Dense", 100, 150);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "10");
            data_output_btn.setAttribute("activation", "\'softmax\'");
            data_output_btn.setFlag_no_output(true);
            //for(NNAttribute a:data_output_btn.getAttr_list()) {
            //    System.out.println(a.getAttribute_name() + ": " + a.getAttribute_value());
            //}

            addNNBtn(data_input_btn);
            addNNBtn(data_output_btn);
        }
        else if (dataset_name.equals("boston_housing")) {

            data_input_btn = createNNBtn("Core.Input", 100, 50);
            data_input_btn.setNN_name("inputLayer");
            data_input_btn.setAttribute("name", "inputLayer");
            data_input_btn.setAttribute("shape", "(13,)");
            data_input_btn.setFlag_no_input(true);

            data_output_btn = createNNBtn("Core.Dense", 100, 150);
            data_output_btn.setNN_name("outputLayer");
            data_output_btn.setAttribute("name", "outputLayer");
            data_output_btn.setAttribute("units", "1");
            data_output_btn.setFlag_no_output(true);
            //for(NNAttribute a:data_output_btn.getAttr_list()) {
            //    System.out.println(a.getAttribute_name() + ": " + a.getAttribute_value());
            //}

            addNNBtn(data_input_btn);
            addNNBtn(data_output_btn);
        }
    }

    public void clearDatasetPane() {
        dataset_name = "";
        setSelectedDataset(other_dataset_btn.getLayoutX(), other_dataset_btn.getLayoutY());
        data_input_btn = null;
        data_output_btn = null;
    }

    // --------------------------------------------------- 网络绘制窗口（Tab 2）----------------------------------------------------
    private void initNeuralNetworkDrawerPane() {
        initLayerPane();
        initNNDrawerPane();
        initAttributePane();
    }
    // --------------------------------------------------- 按钮窗口（位置右上）-----------------------------------------------------
    @FXML
    private VBox LayerPane;

    private LayerPaneButton layer_pane_btn;
    private LayerPaneButton layer_btn_list[];
    private int layer_btn_num;

    private void initLayerPane() {
        layer_pane_btn = new LayerPaneButton(this);
        layer_btn_list = layer_pane_btn.getLayer_btn_list();
        layer_btn_num = layer_pane_btn.getLayer_btn_num();
        LayerPane.getChildren().addAll(layer_btn_list);
    }

    public void addLayerBtn(int index, LayerPaneButton addBtn) {  // 添加按钮
        LayerPane.getChildren().add(index, addBtn);
    }

    public void removeLayerBtn(int index) {  // 删除按钮
        LayerPane.getChildren().remove(index);
    }

    public void indexUpdate_FoldBtn(int start_index, int child_btn_num, boolean add_btn) {  // 更新因添加了子层按钮而变化的母层按钮序号
        if(add_btn) {   // 按钮页展开（添加了按钮）
            for(int i = start_index; i < layer_btn_num; i ++) {
                layer_btn_list[i].setLayBtnIndex(layer_btn_list[i].getLayBtnIndex() + child_btn_num);
            }
        }
        else {          // 按钮页折叠（删除了按钮）
            for(int i = start_index; i < layer_btn_num; i ++) {
                layer_btn_list[i].setLayBtnIndex(layer_btn_list[i].getLayBtnIndex() - child_btn_num);
            }
        }
    }

    // ------------------------------------------------- 绘图窗口（位置左侧）-------------------------------------------------------
    @FXML
    private Pane NNDrawerPane;
    @FXML
    private Rectangle NNDbackground;

    // -------- Core --------
    private int total_Input;
    private int total_Dense;
    private int total_Activation;
    private int total_Dropout;
    private int total_Flatten;
    private int total_Reshape;

    // -------- Convolutional --------
    private int total_Conv1D;
    private int total_Conv2D;
    private int total_SeparableConv1D;
    private int total_SeparableConv2D;
    private int total_Conv2DTranspose;
    private int total_Conv3D;
    private int total_Cropping1D;
    private int total_Cropping2D;
    private int total_Cropping3D;
    private int total_UpSampling1D;
    private int total_UpSampling2D;
    private int total_UpSampling3D;
    private int total_ZeroPadding1D;
    private int total_ZeroPadding2D;
    private int total_ZeroPadding3D;

    // -------- Pooling --------
    private int total_MaxPooling1D;
    private int total_MaxPooling2D;
    private int total_MaxPooling3D;
    private int total_AveragePooling1D;
    private int total_AveragePooling2D;
    private int total_AveragePooling3D;
    private int total_GlobalMaxPooling1D;
    private int total_GlobalAveragePooling1D;
    private int total_GlobalMaxPooling2D;
    private int total_GlobalAveragePooling2D;
    private int total_GlobalMaxPooling3D;
    private int total_GlobalAveragePooling3D;

    // -------- Locally-Connected --------
    private int total_LocallyConnected1D;
    private int total_LocallyConnected2D;

    // -------- Recurrent --------
    private int total_SimpleRNN;
    private int total_GRU;
    private int total_LSTM;
    private int total_ConvLSTM2D;
    private int total_SimpleRNNCell;
    private int total_GRUCell;
    private int total_LSTMCell;
    private int total_CuDNNGRU;
    private int total_CuDNNLSTM;

    // -------- Embedding --------
    private int total_Embedding;

    // -------- Advanced Activations --------
    private int total_LeakyReLU;
    private int total_PReLU;
    private int total_ELU;
    private int total_ThresholdedReLU;
    private int total_Softmax;
    private int total_ReLU;

    // -------- Normalization --------
    private int total_BatchNormalization;

    // -------- Noise --------
    private int total_GaussianNoise;
    private int total_GaussianDropout;
    private int total_AlphaDropout;

    private int total_NN;
    private NNBuilderButton[] NN_list;  // 记录所有神经网络，便于后续检索  // 个人偏爱数组，可用ArrayList代替

    private int NNBtn_id_num;  // 用于给NNBtn编号

    private NNBuilderButton drag_button;  // 影子按钮，用于拖拽按钮时置于鼠标下方以显示安放位置
    @FXML
    private Button shadowBtn;  // 影子按钮，用于拖拽按钮时置于鼠标下方以显示安放位置

    private final int nnd_btn_gap = 10;

    private connectLine drag_cline;  // 影子连线，用于拖拽连线时置于鼠标下方以显示安放位置
    private double temp_cline_s_x;
    private double temp_cline_s_y;
    private double temp_cline_e_x;
    private double temp_cline_e_y;

    protected ContextMenu NNDpane_cmenu;
    protected MenuItem NNclear_btn;
    protected MenuItem NNimport_btn;
    protected final double cmenu_width = 150.0;

    private void initNNDrawerPane() {
        addNNDrawerPaneDragDropEvent();

        total_Input = 0;
        total_Dense = 0;
        total_Activation = 0;
        total_Dropout = 0;
        total_Flatten = 0;
        total_Reshape = 0;

        total_Conv1D = 0;
        total_Conv2D = 0;
        total_SeparableConv1D = 0;
        total_SeparableConv2D = 0;
        total_Conv2DTranspose = 0;
        total_Conv3D = 0;
        total_Cropping1D = 0;
        total_Cropping2D = 0;
        total_Cropping3D = 0;
        total_UpSampling1D = 0;
        total_UpSampling2D = 0;
        total_UpSampling3D = 0;
        total_ZeroPadding1D = 0;
        total_ZeroPadding2D = 0;
        total_ZeroPadding3D = 0;

        total_MaxPooling1D = 0;
        total_MaxPooling2D = 0;
        total_MaxPooling3D = 0;
        total_AveragePooling1D = 0;
        total_AveragePooling2D = 0;
        total_AveragePooling3D = 0;
        total_GlobalMaxPooling1D = 0;
        total_GlobalAveragePooling1D = 0;
        total_GlobalMaxPooling2D = 0;
        total_GlobalAveragePooling2D = 0;
        total_GlobalMaxPooling3D = 0;
        total_GlobalAveragePooling3D = 0;

        total_LocallyConnected1D = 0;
        total_LocallyConnected2D = 0;

        total_SimpleRNN = 0;
        total_GRU = 0;
        total_LSTM = 0;
        total_ConvLSTM2D = 0;
        total_SimpleRNNCell = 0;
        total_GRUCell = 0;
        total_LSTMCell = 0;
        total_CuDNNGRU = 0;
        total_CuDNNLSTM = 0;

        total_Embedding = 0;

        total_LeakyReLU = 0;
        total_PReLU = 0;
        total_ELU = 0;
        total_ThresholdedReLU = 0;
        total_Softmax = 0;
        total_ReLU = 0;

        total_BatchNormalization = 0;

        total_GaussianNoise = 0;
        total_GaussianDropout = 0;
        total_AlphaDropout = 0;

        total_NN = 0;
        NN_list = new NNBuilderButton[5];

        NNBtn_id_num = 0;

        drag_button = new NNBuilderButton(0.5);
        drag_button.setVisible(false);
        NNDrawerPane.getChildren().add(drag_button);
        drag_button.getStyleClass().add("dragBtn");

        shadowBtn.setVisible(false);
        shadowBtn.getStyleClass().add("dragBtn");
        shadowBtn.getStyleClass().add("NNBuilderButton");

        drag_cline = new connectLine(0.5);
        drag_cline.setCLineVisible(false);
        NNDrawerPane.getChildren().add(drag_cline);
        NNDrawerPane.getChildren().add(drag_cline.getCLineArrow());

        initNNDrawerPaneContextMenu();
    }

    private void initNNDrawerPaneContextMenu() {  // 初始化右键菜单
        NNDpane_cmenu = new ContextMenu();
        NNDpane_cmenu.setPrefWidth(cmenu_width);
        NNDpane_cmenu.setMinWidth(USE_PREF_SIZE);
        NNDpane_cmenu.setMaxWidth(USE_PREF_SIZE);

        NNclear_btn = new MenuItem("Clear(Remove all)");
        NNimport_btn = new MenuItem("import from keras file(.py)");
        NNDpane_cmenu.getItems().addAll(NNclear_btn, NNimport_btn);

        NNclear_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAll();
            }
        });

        NNimport_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importNNFromKerasFile();
            }
        });

        NNDbackground.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                NNDpane_cmenu.show(NNDrawerPane, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void addNNDrawerPaneDragDropEvent() {
        NNDrawerPane.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag entered");
            }
        });

        NNDrawerPane.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag exited");
            }
        });

        NNDrawerPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println(event.getGestureSource().getClass());
                if(event.getGestureSource() != NNDrawerPane  /* 拖拽 不来自自己 */
                        && event.getGestureSource().getClass().getSuperclass().getSuperclass() == new LayerPaneButton().getClass()  /* 拖拽 来自LayerPaneButton */
                        && event.getDragboard().hasString()) {  /* 剪贴板中有String */
                    //System.out.println("drag over");
                    //System.out.println(event.getGestureSource().getClass().getSuperclass().getSuperclass());
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    //drag_button.setVisible(true);  // 显示影子按钮，投影按钮安放位置
                    //drag_button.setBtn_position(event.getX(), event.getY());
                    shadowBtn.setVisible(true);  // 显示影子按钮，投影按钮安放位置
                    shadowBtn.setLayoutX((int)(event.getX() / nnd_btn_gap) * nnd_btn_gap - 50);
                    shadowBtn.setLayoutY((int)(event.getY() / nnd_btn_gap) * nnd_btn_gap - 25);
                }
                else if(event.getGestureSource() != NNDrawerPane  /* 拖拽 不来自自己 */
                        && event.getGestureSource().getClass() == new sideButton().getClass()  /* 拖拽 来自边界按钮 */
                        && event.getDragboard().hasString()) {  /* 剪贴板中有String */
                    //System.out.println("drag over");
                    //System.out.println(event.getGestureSource().getClass().getSuperclass().getSuperclass());
                    event.acceptTransferModes(TransferMode.MOVE);
                    drag_cline.setCLineVisible(true);  // 显示影子连线，投影连线安放位置
                    temp_cline_e_x = event.getX();
                    temp_cline_e_y = event.getY();
                    drag_cline.drawDragConnectLine(temp_cline_s_x, temp_cline_s_y, temp_cline_e_x, temp_cline_e_y);
                }
                else if(event.getGestureSource() != NNDrawerPane  /* 拖拽 不来自自己 */
                        && event.getGestureSource().getClass().getSuperclass().getSuperclass() == new NNBuilderButton().getClass()  /* 拖拽 来自绘图页面的按钮 */
                        && event.getDragboard().hasString()) {  /* 剪贴板中有String */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    Dragboard dragboard = event.getDragboard();
                    String NNBtn[] = dragboard.getString().split("\\.", 0);
                    int NNBtn_index = Integer.valueOf(NNBtn[0]).intValue();
                    NN_list[NNBtn_index].DraggingTo((int)(event.getX() / nnd_btn_gap) * nnd_btn_gap, (int)(event.getY() / nnd_btn_gap) * nnd_btn_gap, 0.5);
                }
            }
        });

        NNDrawerPane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag dropped");
                if(event.getGestureSource().getClass().getSuperclass().getSuperclass() == new LayerPaneButton().getClass()) {
                    //drag_button.setVisible(false);
                    shadowBtn.setVisible(false);

                    Dragboard dragboard = event.getDragboard();  // 创建按钮
                    String layer_type = dragboard.getString();
                    NNBuilderButton dragBtn = createNNBtn(layer_type, (int)(event.getX() / nnd_btn_gap) * nnd_btn_gap, (int)(event.getY() / nnd_btn_gap) * nnd_btn_gap);
                    addNNBtn(dragBtn);
                    showNNBtnAttribute(dragBtn);
                }
                else if(event.getGestureSource().getClass() == new sideButton().getClass()  /* 拖拽 来自边界按钮 */) {
                    drag_cline.setCLineVisible(false);  // 拖拽来自边界按钮，则表示连线未创建，取消显示影子连线
                }
                else if(event.getGestureSource().getClass().getSuperclass().getSuperclass() == new NNBuilderButton().getClass()  /* 拖拽 来自绘图页面的按钮 */) {

                    Dragboard dragboard = event.getDragboard();
                    String NNBtn[] = dragboard.getString().split("\\.", 0);
                    int NNBtn_index = Integer.valueOf(NNBtn[0]).intValue();
                    NN_list[NNBtn_index].DraggingTo((int)(event.getX() / nnd_btn_gap) * nnd_btn_gap, (int)(event.getY() / nnd_btn_gap) * nnd_btn_gap, 1);
                }
            }
        });
    }

    public NNBuilderButton createNNBtn(String layer_type, double posX, double posY) {  // 判断拖拽的是什么层
        String[] new_layer = layer_type.split("\\.", 0);  // 此处注意，不同语言环境下这里可能会有变化
        if (new_layer[0].equals("Core")) {
            if      (new_layer[1].equals("Input"))      { total_Input ++;      return new InputNN      (posX, posY, total_Input,      this); }  // Core.Input
            else if (new_layer[1].equals("Dense"))      { total_Dense ++;      return new DenseNN      (posX, posY, total_Dense,      this); }  // Core.Dense
            else if (new_layer[1].equals("Activation")) { total_Activation ++; return new ActivationNN (posX, posY, total_Activation, this); }  // Core.Activation
            else if (new_layer[1].equals("Dropout"))    { total_Dropout ++;    return new DropoutNN    (posX, posY, total_Dropout,    this); }  // Core.Dropout
            else if (new_layer[1].equals("Flatten"))    { total_Flatten ++;    return new FlattenNN    (posX, posY, total_Flatten,    this); }  // Core.Flatten
            else if (new_layer[1].equals("Reshape"))    { total_Reshape ++;    return new ReshapeNN    (posX, posY, total_Reshape,    this); }  // Core.Reshape
        }
        else if (new_layer[0].equals("Convolutional")) {
            if      (new_layer[1].equals("Conv1D"))          { total_Conv1D ++;          return new Conv1DNN          (posX, posY, total_Conv1D,          this); }  // Convolutional.Conv1D
            else if (new_layer[1].equals("Conv2D"))          { total_Conv2D ++;          return new Conv2DNN          (posX, posY, total_Conv2D,          this); }  // Convolutional.Conv2D
            else if (new_layer[1].equals("SeparableConv1D")) { total_SeparableConv1D ++; return new SeparableConv1DNN (posX, posY, total_SeparableConv1D, this); }  // Convolutional.SeparableConv1D
            else if (new_layer[1].equals("SeparableConv2D")) { total_SeparableConv2D ++; return new SeparableConv2DNN (posX, posY, total_SeparableConv2D, this); }  // Convolutional.SeparableConv2D
            else if (new_layer[1].equals("Conv2DTranspose")) { total_Conv2DTranspose ++; return new Conv2DTransposeNN (posX, posY, total_Conv2DTranspose, this); }  // Convolutional.Conv2DTranspose
            else if (new_layer[1].equals("Conv3D"))          { total_Conv3D ++;          return new Conv3DNN          (posX, posY, total_Conv3D,          this); }  // Convolutional.Conv3D
            else if (new_layer[1].equals("Cropping1D"))      { total_Cropping1D ++;      return new Cropping1DNN      (posX, posY, total_Cropping1D,      this); }  // Convolutional.Cropping1D
            else if (new_layer[1].equals("Cropping2D"))      { total_Cropping2D ++;      return new Cropping2DNN      (posX, posY, total_Cropping2D,      this); }  // Convolutional.Cropping2D
            else if (new_layer[1].equals("Cropping3D"))      { total_Cropping3D ++;      return new Cropping3DNN      (posX, posY, total_Cropping3D,      this); }  // Convolutional.Cropping3D
            else if (new_layer[1].equals("UpSampling1D"))    { total_UpSampling1D ++;    return new UpSampling1DNN    (posX, posY, total_UpSampling1D,    this); }  // Convolutional.UpSampling1D
            else if (new_layer[1].equals("UpSampling2D"))    { total_UpSampling2D ++;    return new UpSampling2DNN    (posX, posY, total_UpSampling2D,    this); }  // Convolutional.UpSampling2D
            else if (new_layer[1].equals("UpSampling3D"))    { total_UpSampling3D ++;    return new UpSampling3DNN    (posX, posY, total_UpSampling3D,    this); }  // Convolutional.UpSampling3D
            else if (new_layer[1].equals("ZeroPadding1D"))   { total_ZeroPadding1D ++;   return new ZeroPadding1DNN   (posX, posY, total_ZeroPadding1D,   this); }  // Convolutional.ZeroPadding1D
            else if (new_layer[1].equals("ZeroPadding2D"))   { total_ZeroPadding2D ++;   return new ZeroPadding2DNN   (posX, posY, total_ZeroPadding2D,   this); }  // Convolutional.ZeroPadding2D
            else if (new_layer[1].equals("ZeroPadding3D"))   { total_ZeroPadding3D ++;   return new ZeroPadding3DNN   (posX, posY, total_ZeroPadding3D,   this); }  // Convolutional.ZeroPadding3D
        }
        else if (new_layer[0].equals("Pooling")) {
            if      (new_layer[1].equals("MaxPooling1D"))           { total_MaxPooling1D ++;           return new MaxPooling1DNN           (posX, posY, total_MaxPooling1D,           this); }  // Pooling.MaxPooling1D
            else if (new_layer[1].equals("MaxPooling2D"))           { total_MaxPooling2D ++;           return new MaxPooling2DNN           (posX, posY, total_MaxPooling2D,           this); }  // Pooling.MaxPooling2D
            else if (new_layer[1].equals("MaxPooling3D"))           { total_MaxPooling3D ++;           return new MaxPooling3DNN           (posX, posY, total_MaxPooling3D,           this); }  // Pooling.MaxPooling3D
            else if (new_layer[1].equals("AveragePooling1D"))       { total_AveragePooling1D ++;       return new AveragePooling1DNN       (posX, posY, total_AveragePooling1D,       this); }  // Pooling.AveragePooling1D
            else if (new_layer[1].equals("AveragePooling2D"))       { total_AveragePooling2D ++;       return new AveragePooling2DNN       (posX, posY, total_AveragePooling2D,       this); }  // Pooling.AveragePooling2D
            else if (new_layer[1].equals("AveragePooling3D"))       { total_AveragePooling3D ++;       return new AveragePooling3DNN       (posX, posY, total_AveragePooling3D,       this); }  // Pooling.AveragePooling3D
            else if (new_layer[1].equals("GlobalMaxPooling1D"))     { total_GlobalMaxPooling1D ++;     return new GlobalMaxPooling1DNN     (posX, posY, total_GlobalMaxPooling1D,     this); }  // Pooling.GlobalMaxPooling1D
            else if (new_layer[1].equals("GlobalAveragePooling1D")) { total_GlobalAveragePooling1D ++; return new GlobalAveragePooling1DNN (posX, posY, total_GlobalAveragePooling1D, this); }  // Pooling.GlobalAveragePooling1D
            else if (new_layer[1].equals("GlobalMaxPooling2D"))     { total_GlobalMaxPooling1D ++;     return new GlobalMaxPooling2DNN     (posX, posY, total_GlobalMaxPooling2D,     this); }  // Pooling.GlobalMaxPooling2D
            else if (new_layer[1].equals("GlobalAveragePooling2D")) { total_GlobalAveragePooling1D ++; return new GlobalAveragePooling2DNN (posX, posY, total_GlobalAveragePooling2D, this); }  // Pooling.GlobalAveragePooling2D
            else if (new_layer[1].equals("GlobalMaxPooling3D"))     { total_GlobalMaxPooling1D ++;     return new GlobalMaxPooling3DNN     (posX, posY, total_GlobalMaxPooling3D,     this); }  // Pooling.GlobalMaxPooling3D
            else if (new_layer[1].equals("GlobalAveragePooling3D")) { total_GlobalAveragePooling1D ++; return new GlobalAveragePooling3DNN (posX, posY, total_GlobalAveragePooling3D, this); }  // Pooling.GlobalAveragePooling3D
        }
        else if (new_layer[0].equals("LocallyConnected")) {
            if      (new_layer[1].equals("LocallyConnected1D"))     { total_LocallyConnected1D ++;     return new LocallyConnected1DNN     (posX, posY, total_LocallyConnected1D,     this); }  // LocallyConnected.LocallyConnected1D
            else if (new_layer[1].equals("LocallyConnected2D"))     { total_LocallyConnected2D ++;     return new LocallyConnected2DNN     (posX, posY, total_LocallyConnected2D,     this); }  // LocallyConnected.LocallyConnected2D
        }
        else if (new_layer[0].equals("Recurrent")) {
            if      (new_layer[1].equals("SimpleRNN"))     { total_SimpleRNN ++;     return new SimpleRNNNN     (posX, posY, total_SimpleRNN,     this); }  // Recurrent.SimpleRNN
            else if (new_layer[1].equals("GRU"))           { total_GRU ++;           return new GRUNN           (posX, posY, total_GRU,           this); }  // Recurrent.GRU
            else if (new_layer[1].equals("LSTM"))          { total_LSTM ++;          return new LSTMNN          (posX, posY, total_LSTM,          this); }  // Recurrent.LSTM
            else if (new_layer[1].equals("ConvLSTM2D"))    { total_ConvLSTM2D ++;    return new ConvLSTM2DNN    (posX, posY, total_ConvLSTM2D,    this); }  // Recurrent.ConvLSTM2D
            else if (new_layer[1].equals("SimpleRNNCell")) { total_SimpleRNNCell ++; return new SimpleRNNCellNN (posX, posY, total_SimpleRNNCell, this); }  // Recurrent.SimpleRNNCell
            else if (new_layer[1].equals("GRUCell"))       { total_GRUCell ++;       return new GRUCellNN       (posX, posY, total_GRUCell,       this); }  // Recurrent.GRUCell
            else if (new_layer[1].equals("LSTMCell"))      { total_LSTMCell ++;      return new LSTMCellNN      (posX, posY, total_LSTMCell,      this); }  // Recurrent.LSTMCell
            else if (new_layer[1].equals("CuDNNGRU"))      { total_CuDNNGRU ++;      return new CuDNNGRUNN      (posX, posY, total_CuDNNGRU,      this); }  // Recurrent.CuDNNGRU
            else if (new_layer[1].equals("CuDNNLSTM"))     { total_CuDNNLSTM ++;     return new CuDNNLSTMNN     (posX, posY, total_CuDNNLSTM,     this); }  // Recurrent.CuDNNLSTM
        }
        else if (new_layer[0].equals("Embedding")) {
            if      (new_layer[1].equals("Embedding")) { total_Embedding ++; return new EmbeddingLayerNN (posX, posY, total_Embedding, this); }  // Embedding.Embedding
        }
        else if (new_layer[0].equals("AdvancedActivations")) {
            if      (new_layer[1].equals("LeakyReLU"))       { total_LeakyReLU ++;       return new LeakyReLUNN       (posX, posY, total_LeakyReLU,       this); }  // Advanced Activation.LeakyReLU
            else if (new_layer[1].equals("PReLU"))           { total_PReLU ++;           return new PReLUNN           (posX, posY, total_PReLU,           this); }  // Advanced Activation.PReLU
            else if (new_layer[1].equals("ELU"))             { total_ELU ++;             return new ELUNN             (posX, posY, total_ELU,             this); }  // Advanced Activation.ELU
            else if (new_layer[1].equals("ThresholdedReLU")) { total_ThresholdedReLU ++; return new ThresholdedReLUNN (posX, posY, total_ThresholdedReLU, this); }  // Advanced Activation.ThresholdedReLU
            else if (new_layer[1].equals("Softmax"))         { total_Softmax ++;         return new SoftmaxNN         (posX, posY, total_Softmax,         this); }  // Advanced Activation.Softmax
            else if (new_layer[1].equals("ReLU"))            { total_ReLU ++;            return new ReLUNN            (posX, posY, total_ReLU,            this); }  // Advanced Activation.ReLU
        }
        else if (new_layer[0].equals("Normalization")) {
            if      (new_layer[1].equals("BatchNormalization")) { total_BatchNormalization ++; return new BatchNormalizationNN (posX, posY, total_BatchNormalization, this); }  // Normalization.BatchNormalization
        }
        else if (new_layer[0].equals("Noise")) {
            if      (new_layer[1].equals("GaussianNoise"))   { total_GaussianNoise ++;   return new GaussianNoiseNN   (posX, posY, total_GaussianNoise,   this); }  // Noise.GaussianNoise
            else if (new_layer[1].equals("GaussianDropout")) { total_GaussianDropout ++; return new GaussianDropoutNN (posX, posY, total_GaussianDropout, this); }  // Noise.GaussianDropout
            else if (new_layer[1].equals("AlphaDropout"))    { total_AlphaDropout ++;    return new AlphaDropoutNN    (posX, posY, total_AlphaDropout,    this); }  // Noise.AlphaDropout
        }
        return new NNBuilderButton();
    }

    public NNBuilderButton findNNBtn(String layer_name) {
        for(int i = 0; i < total_NN; i ++)
            if(NN_list[i].getNN_name().equals(layer_name))
                return NN_list[i];
        return null;
    }

    public void addNNBtn(NNBuilderButton NNBtn) {
        NNDrawerPane.getChildren().add(1, NNBtn);  // 将按钮置于根节点下最上方，在显示时就会置于最底层
        NNDrawerPane.getChildren().addAll(NNBtn.getSideBtnList());  // 自然添加边界按钮，自动置于根节点最下方，在显示时会置于最顶层，不会被其他部件遮挡
                                                                    // 注意之后的连线的放置位置
        addNNBtnToList(NNBtn);
        addNNBtnToCode(NNBtn);
    }

    public void removeNNBtn(NNBuilderButton NNBtn) {
        boolean has_nnbtn = false;
        NNDrawerPane.getChildren().removeAll(NNBtn.getSideBtnList());
        has_nnbtn = NNDrawerPane.getChildren().remove(NNBtn);

        if(has_nnbtn)
            removeNNBtnFromList(NNBtn);
        removeNNBtnFromCode(NNBtn);
    }

    private void addNNBtnToList(NNBuilderButton NNBtn) {  // 把按钮添加到数组中记录下来
        if(total_NN >= NN_list.length-1) {
            NNBuilderButton[] temp_list = new NNBuilderButton[total_NN * 2 + 1];
            for(int i = 0; i < NN_list.length; i ++) {
                temp_list[i] = NN_list[i];
            }
            NN_list = temp_list;
        }

        NN_list[total_NN] = NNBtn;
        NNBtn.setNN_index(total_NN);
        //System.out.println(NNBtn.getNN_type() + "," + NNBtn.getNN_index());
        total_NN ++;

        NNBtn.setNN_id(NNBtn_id_num);
        NNBtn_id_num ++;
    }

    private void removeNNBtnFromList(NNBuilderButton NNBtn) {
        for(int i = NNBtn.getNN_index(); i < total_NN; i ++) {
            NN_list[i] = NN_list[i+1];
        }
        total_NN --;
    }

    public NNBuilderButton getNNbtn(int NN_index) {
        return NN_list[NN_index];
    }

    public int getTotal_NN() {
        return total_NN;
    }

    public NNBuilderButton[] getNN_list() {
        return NN_list;
    }

    public void setAllSideBtnVisible(boolean is_visible) {  // 在拖拽添加连接线条时显示所有边界按钮
        for(int i = 0; i < total_NN; i ++) {
            NN_list[i].setSideBtnVisible(is_visible);
        }
    }

    public void addConnectLine(connectLine c_line) {
        NNDrawerPane.getChildren().add(total_NN+1, c_line.getCLineDeleteBtn());
        NNDrawerPane.getChildren().add(total_NN+1, c_line);
        NNDrawerPane.getChildren().add(total_NN+1, c_line.getCLineArrow());

        addCLineToCode(c_line);
    }

    public void removeConnectLine(connectLine c_line) {
        boolean has_cline = false;
        NNDrawerPane.getChildren().remove(c_line.getCLineArrow());
        NNDrawerPane.getChildren().remove(c_line.getCLineDeleteBtn());
        has_cline = NNDrawerPane.getChildren().remove(c_line);

        if(has_cline)
            removeCLineFromCode(c_line);
    }

    public void setDragCLineStart(double s_x, double s_y) {  // 用于设置影子连线的起点
        this.temp_cline_s_x = s_x;
        this.temp_cline_s_y = s_y;
    }

    public void setDragCLineVisible(boolean is_visible) {
        drag_cline.setCLineVisible(is_visible);
    }

    // ---------------------------------------------------- 属性窗口（位置右下）---------------------------------------------------------
    @FXML
    private TableView<NNAttribute> AttributePane;
    @FXML
    private TableColumn<NNAttribute, String> AttributeColumn;
    @FXML
    private TableColumn<NNAttribute, Object> ValueColumn;

    private ObservableList<NNAttribute> NNAttr_list;

    private void initAttributePane() {
        NNAttr_list = FXCollections.observableArrayList();
        AttributeColumn.setCellValueFactory(new PropertyValueFactory("attribute_name"));
        ValueColumn.setCellValueFactory(new PropertyValueFactory("attribute_control"));
        AttributePane.setItems(NNAttr_list);
    }

    public void showNNBtnAttribute(NNBuilderButton NNBtn) {  // 向属性面板中添加属性
        NNAttr_list.setAll(NNBtn.getAttr_list());
    }

    // ---------------------------------------------------- Compile & Fit 窗口（ Tab 3 ）------------------------------------------------------------
    @FXML
    private TableView<CFAttribute> CFAttrPane;
    @FXML
    private TableColumn<CFAttribute, String> CFAttrColumn;
    @FXML
    private TableColumn<CFAttribute, Object> CFValueColumn;

    private ObservableList<CFAttribute> CFAttr_list;

    @FXML
    private TableView<CFAttribute> CFAAttrPane;
    @FXML
    private TableColumn<CFAttribute, String> CFAAttrColumn;
    @FXML
    private TableColumn<CFAttribute, Object> CFAValueColumn;

    private ObservableList<CFAttribute> CFAAttr_list;


    private void initCompileAndFitPane() {
        initCFAttributePane();
        initCompileFitBtn();
    }

    private void initCFAttributePane() {
        CFAttr_list = FXCollections.observableArrayList();
        CFAttrColumn.setCellValueFactory(new PropertyValueFactory("attribute_name"));
        CFValueColumn.setCellValueFactory(new PropertyValueFactory("attribute_control"));
        CFAttrPane.setItems(CFAttr_list);

        CFAAttr_list = FXCollections.observableArrayList();
        CFAAttrColumn.setCellValueFactory(new PropertyValueFactory("attribute_name"));
        CFAValueColumn.setCellValueFactory(new PropertyValueFactory("attribute_control"));
        CFAAttrPane.setItems(CFAAttr_list);
    }

    public void showCFBtnAttribute(CFAttribute[] attr_list) {
        CFAttr_list.setAll(compileFunc.getAttr_list());
        CFAAttr_list.setAll(fitFunc.getAttr_list());
    }

    @FXML
    private Rectangle toNNPaneBtn_1;
    @FXML
    private Rectangle toNNPaneBtn_2;
    @FXML
    private Text toNNPaneText_1;
    @FXML
    private Text toNNPaneText_2;

    @FXML
    private Rectangle CompileFuncBtn;
    @FXML
    private Text CompileFuncText;
    private CompileFunction compileFunc;

    @FXML
    private Circle FitFuncBtn;
    @FXML
    private Text FitFuncText;
    private FitFunction fitFunc;

    private void initCompileFitBtn() {
        compileFunc = new CompileFunction(this);
        CFAttr_list.setAll(compileFunc.getAttr_list());
        fitFunc = new FitFunction(this);
        CFAAttr_list.setAll(fitFunc.getAttr_list());

        toNNPaneBtn_1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeTabPane(_DRAWER_PANE_INDEX);
            }
        });

        toNNPaneBtn_2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeTabPane(_DRAWER_PANE_INDEX);
            }
        });

        toNNPaneText_1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeTabPane(_DRAWER_PANE_INDEX);
            }
        });

        toNNPaneText_2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                changeTabPane(_DRAWER_PANE_INDEX);
            }
        });

        CompileFuncBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCFBtnAttribute(compileFunc.getAttr_list());
            }
        });

        CompileFuncText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCFBtnAttribute(compileFunc.getAttr_list());
            }
        });

        FitFuncBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCFBtnAttribute(fitFunc.getAttr_list());
            }
        });

        FitFuncText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showCFBtnAttribute(fitFunc.getAttr_list());
            }
        });
    }

    public CompileFunction getCompileFunc() {
        return compileFunc;
    }

    public FitFunction getFitFunc() {
        return fitFunc;
    }

    public final int _DRAWER_PANE_INDEX = 1;
    public final int _SETTINGS_PANE_INDEX = 4;
    public void changeTabPane(int tab_number) {
        mainTabPane.getSelectionModel().select(tab_number);
    }

    // ---------------------------------------------------- 代码窗口（ Tab 4 ）------------------------------------------------------------
    @FXML
    private TextArea CodePane;
    private CodePaneManager codeManager;
    //private CodePaneManagerTest codeManager;

    protected ContextMenu codePane_cmenu;
    protected MenuItem CPexport_btn;
    protected MenuItem CPclear_btn;
    protected MenuItem CPimport_btn;

    private void initCodePane() {
        codeManager = new CodePaneManager(CodePane, this);
        //codeManager = new CodePaneManagerTest(CodePane, this);

        initCodePaneMenuItem();
        initCodePaneContextMenu();
        CodePane.setContextMenu(codePane_cmenu);
    }

    private void addCodePaneImportCode(String import_code) {
        codeManager.addImportCode(import_code);
    }

    private void setCodePaneDatasets(String datasets_name, String datasets_code) {
        codeManager.setDatasets(datasets_name, datasets_code);
    }

    private void addNNBtnToCode(NNBuilderButton NNBtn) {
        codeManager.addNNLayer(NNBtn);
    }

    private void removeNNBtnFromCode(NNBuilderButton NNBtn) {
        codeManager.removeNNLayer(NNBtn);
    }

    private void addCLineToCode(connectLine c_line) {
        codeManager.addCLine(c_line);
    }

    private void removeCLineFromCode(connectLine c_line) {
        codeManager.removeCLine(c_line);
    }

    public void changeNNBtnAttribute(NNBuilderButton NNBtn, NNAttribute NNAttr, String oldValue, String newValue) {
        codeManager.changeAttribute(NNBtn, NNAttr, oldValue, newValue);
    }

    public void changeNNBtnName(NNBuilderButton NNBtn, NNAttribute NNAttr, String oldName, String newName) {
        codeManager.changeName(NNBtn, NNAttr, oldName, newName);
    }

    public void changeCFAttribute(CFAttribute CFAttr, String oldValue, String newValue) {
        codeManager.changeCFAttribute(CFAttr, oldValue, newValue);
    }

    @FXML
    private MenuItem ExportMenuItem;
    @FXML
    private MenuItem RemoveAllMenuItem;
    @FXML
    private MenuItem ImportMenuItem;

    private void initCodePaneMenuItem() {
        ExportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exportAndSave();
            }
        });

        RemoveAllMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAll();
            }
        });

        ImportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importNNFromKerasFile();
            }
        });
    }

    private void initCodePaneContextMenu() {  // 会与TextEdit自带的右键菜单重合
        codePane_cmenu = new ContextMenu();
        codePane_cmenu.setPrefWidth(cmenu_width);
        codePane_cmenu.setMinWidth(USE_PREF_SIZE);
        codePane_cmenu.setMaxWidth(USE_PREF_SIZE);

        CPexport_btn = new MenuItem("Export as Python file");
        CPclear_btn = new MenuItem("Clear(Remove all)");
        CPimport_btn = new MenuItem("import from keras file(.py)");
        codePane_cmenu.getItems().addAll(CPexport_btn, CPclear_btn);

        CPclear_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearAll();
            }
        });

        CPexport_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exportAndSave();
            }
        });

        CPimport_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                importNNFromKerasFile();
            }
        });

        CodePane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                codePane_cmenu.show(CodePane, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void exportAndSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export as Python file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Python Files", "*.py"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        try {
            codeManager.export(selectedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        clearDatasetPane();
        clearNNDrawerPane();
        clearAttributePane();
        clearCFPane();
        clearCodePane();
    }

    public void clearNNDrawerPane() {
        total_Input = 0;
        total_Dense = 0;
        total_Activation = 0;
        total_Dropout = 0;
        total_Flatten = 0;
        total_Reshape = 0;

        total_Conv1D = 0;
        total_Conv2D = 0;
        total_SeparableConv1D = 0;
        total_SeparableConv2D = 0;
        total_Conv2DTranspose = 0;
        total_Conv3D = 0;
        total_Cropping1D = 0;
        total_Cropping2D = 0;
        total_Cropping3D = 0;
        total_UpSampling1D = 0;
        total_UpSampling2D = 0;
        total_UpSampling3D = 0;
        total_ZeroPadding1D = 0;
        total_ZeroPadding2D = 0;
        total_ZeroPadding3D = 0;

        total_MaxPooling1D = 0;
        total_MaxPooling2D = 0;
        total_MaxPooling3D = 0;
        total_AveragePooling1D = 0;
        total_AveragePooling2D = 0;
        total_AveragePooling3D = 0;
        total_GlobalMaxPooling1D = 0;
        total_GlobalAveragePooling1D = 0;
        total_GlobalMaxPooling2D = 0;
        total_GlobalAveragePooling2D = 0;
        total_GlobalMaxPooling3D = 0;
        total_GlobalAveragePooling3D = 0;

        total_LocallyConnected1D = 0;
        total_LocallyConnected2D = 0;

        total_SimpleRNN = 0;
        total_GRU = 0;
        total_LSTM = 0;
        total_ConvLSTM2D = 0;
        total_SimpleRNNCell = 0;
        total_GRUCell = 0;
        total_LSTMCell = 0;
        total_CuDNNGRU = 0;
        total_CuDNNLSTM = 0;

        total_Embedding = 0;

        total_LeakyReLU = 0;
        total_PReLU = 0;
        total_ELU = 0;
        total_ThresholdedReLU = 0;
        total_Softmax = 0;
        total_ReLU = 0;

        total_BatchNormalization = 0;

        total_GaussianNoise = 0;
        total_GaussianDropout = 0;
        total_AlphaDropout = 0;

        total_NN = 0;
        NN_list = new NNBuilderButton[5];

        NNBtn_id_num = 0;

        // 绘图窗口
        NNDrawerPane.getChildren().retainAll(drag_button, drag_cline, drag_cline.getCLineArrow());
    }

    public void clearAttributePane() {
        NNAttr_list.removeAll();
    }

    public void clearCFPane() {
        CFAttr_list.removeAll();
        compileFunc.initNNAttribute();
        fitFunc.initNNAttribute();
    }

    public void clearCodePane() {
        codeManager.clearAll();
    }

    // ====================================================== Import Neural Network From Keras File ================================================================

    private ImportCodeManager importCodeManager;
    private ImportCodeManagerWithProcess importCodeManagerPs;

    private void initImport() {
        importCodeManager = new ImportCodeManager(this);
        importCodeManagerPs = new ImportCodeManagerWithProcess(this);
    }

    private void importNNFromKerasFile() {
        //importCodeManager.choosePythonFile();
        importCodeManagerPs.choosePythonFile();
    }
    // =============================================================================================================================================================

    // --------------------------------------------------------- Settings Pane ---------------------------------------------------------------------
    Project _project;
    AnActionEvent _event;

    public void set_project(Project _project) {
        this._project = _project;
    }

    public void set_event(AnActionEvent _event) {
        this._event = _event;
    }

    public Project get_project() {
        return _project;
    }

    public AnActionEvent get_event() {
        return _event;
    }

    @FXML
    private TextField OutputFilePathTxfd;
    @FXML
    private Button OutputFileSelectBtn;
    @FXML
    private TextField KerasRunnerPathTxfd;
    @FXML
    private Button KerasRunnerSelectBtn;

    private void initSettingsPane() {
        //PsiFile psiFile = _event.getData(LangDataKeys.PSI_FILE);
        //String present_file_path = psiFile.getContainingDirectory() + "\\" + psiFile.getName();
        //System.out.println(present_file_path);
        //OutputFilePathTxfd.setText(present_file_path);
        //codeManager.set_output_file_path(present_file_path);

        OutputFileSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Keras file");
                fileChooser.setInitialDirectory(new File(_project.getPresentableUrl()));
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Python File", "*.py")
                        //new FileChooser.ExtensionFilter("All Files", "*.*")
                );
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                String output_file_path = selectedFile.getAbsolutePath();
                OutputFilePathTxfd.setText(output_file_path);
                codeManager.set_output_file_path(output_file_path);
            }
        });

        KerasRunnerSelectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select Keras Runner");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Keras Runner", "*.exe")
                        //new FileChooser.ExtensionFilter("All Files", "*.*")
                );
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                String keras_runner_path = selectedFile.getAbsolutePath();
                KerasRunnerPathTxfd.setText(keras_runner_path);
                importCodeManagerPs.setKeras_process_address(keras_runner_path);
            }
        });
    }
}
