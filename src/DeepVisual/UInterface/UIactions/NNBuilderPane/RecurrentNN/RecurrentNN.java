package DeepVisual.UInterface.UIactions.NNBuilderPane.RecurrentNN;

import DeepVisual.UInterface.UIactions.NNBuilderPane.NNBuilderButton;

public class RecurrentNN extends NNBuilderButton {
    private SimpleRNNNN child_SimpleRNN;
    private GRUNN child_GRU;
    private LSTMNN child_LSTM;
    private ConvLSTM2DNN child_ConvLSTM2D;
    private SimpleRNNCellNN child_SimpleRNNCell;
    private GRUCellNN child_GRUCell;
    private LSTMCellNN child_LSTMCell;
    //private StackedRNNCellsNN child_StackedRNNCells;
    private CuDNNGRUNN child_CuDNNGRU;
    private CuDNNLSTMNN child_CuDNNLSTM;

    private RecurrentNN child_NN_list[];

    public RecurrentNN() {
        this.setText("< Recurrent >");
        this.getStyleClass().add("Recurrent");
        //this.NN_type += "Core - ";
    }

    public RecurrentNN(String NN_name) {
        this();
        initChild_NN_list();
    }

    private void initChild_NN_list() {
        child_SimpleRNN       = new SimpleRNNNN();
        child_GRU             = new GRUNN();
        child_LSTM            = new LSTMNN();
        child_ConvLSTM2D      = new ConvLSTM2DNN();
        child_SimpleRNNCell   = new SimpleRNNCellNN();
        child_GRUCell         = new GRUCellNN();
        child_LSTMCell        = new LSTMCellNN();
        //child_StackedRNNCells = new StackedRNNCellsNN();
        child_CuDNNGRU        = new CuDNNGRUNN();
        child_CuDNNLSTM       = new CuDNNLSTMNN();

        child_NN_list = new RecurrentNN[]{
                child_SimpleRNN,
                child_GRU,
                child_LSTM,
                child_ConvLSTM2D,
                child_SimpleRNNCell,
                child_GRUCell,
                child_LSTMCell,
                //child_StackedRNNCells,
                child_CuDNNGRU,
                child_CuDNNLSTM
        };
    }

    public RecurrentNN[] getChild_NN_list() {
        return child_NN_list;
    }
}
