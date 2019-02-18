package DeepVisual.UInterface.UIactions.LayerPane.RecurrentLayer;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.LayerPane.LayerPaneButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RecurrentLayerButton extends LayerPaneButton {
    private SimpleRNNLayer child_SimpleRNN;
    private GRULayer child_GRU;
    private LSTMLayer child_LSTM;
    private ConvLSTM2DLayer child_ConvLSTM2D;
    private SimpleRNNCellLayer child_SimpleRNNCell;
    private GRUCellLayer child_GRUCell;
    private LSTMCellLayer child_LSTMCell;
    //private StackedRNNCellsLayer child_StackedRNNCells;
    private CuDNNGRULayer child_CuDNNGRU;
    private CuDNNLSTMLayer child_CuDNNLSTM;

    private RecurrentLayerButton child_layer_List[];

    public RecurrentLayerButton() {
        this.setText("< Recurrent >");
        layer_type += "Recurrent";
        this.getStyleClass().add("Recurrent");
    }

    public RecurrentLayerButton(DeepVisualWindowController _parent) {
        this();
        this._parent = _parent;  // 继承自LayerPaneButton
        initChildLayerList();
        addLayBtnEvent();

        this.getStyleClass().add("RecurrentLayerButton");
        this.getStyleClass().add("ParentLayer");
    }

    private void initChildLayerList() {  // 初始化子层按钮
        this.has_child = true;  // 继承自LayerPaneButton
        this.is_folded = true;  // 继承自LayerPaneButton

        child_SimpleRNN       = new SimpleRNNLayer();
        child_GRU             = new GRULayer();
        child_LSTM            = new LSTMLayer();
        child_ConvLSTM2D      = new ConvLSTM2DLayer();
        child_SimpleRNNCell   = new SimpleRNNCellLayer();
        child_GRUCell         = new GRUCellLayer();
        child_LSTMCell        = new LSTMCellLayer();
        //child_StackedRNNCells = new StackedRNNCellsLayer();
        child_CuDNNGRU        = new CuDNNGRULayer();
        child_CuDNNLSTM       = new CuDNNLSTMLayer();

        child_layer_List = new RecurrentLayerButton[]{
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

    private void addLayBtnEvent() {
        // 鼠标点击事件，点击展开/折叠子层
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                foldChildLayer(child_layer_List);  // 继承自LayerPaneButton
            }
        });
    }
}
