package DeepVisual.UInterface.UIactions.NNBuilderPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import DeepVisual.UInterface.UIactions.AttributePane.NNAttribute;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class NNBuilderButton extends Button {

    public NNBuilderButton() {
        this.NN_type = "";

        this.getStyleClass().add("NNBuilderButton");

        this.initNNBtnSize();  // 初始化按钮大小
        this.initNNBtnContextMenu();  // 初始化右键菜单
        this.initSideBtn();  // 初始化边界按钮
        this.initNNBtnAttribute();  // 初始化按钮属性
    }

    public NNBuilderButton(double layout_pos_x, double layout_pos_y) {
        this();

        initNNBtnPosition(layout_pos_x, layout_pos_y);
    }

    public NNBuilderButton(double _opacity) {
        this.getStyleClass().add("NNBuilderButton");
        this.initNNBtnSize();
        this.setOpacity(_opacity);
    }

    // 用于与界面进行信息交换
    protected DeepVisualWindowController _parent;
    public void set_parent(DeepVisualWindowController _parent) {
        this._parent = _parent;
    }
    public DeepVisualWindowController get_parent() {
        return this._parent;
    }

    // --------------------------------------------------------- 绘图界面部分 -------------------------------------------------------------------------
    protected final double btn_width = 100.0;
    protected final double btn_height = 50.0;

    protected ContextMenu btn_cmenu;
    protected MenuItem show_btn_name;
    protected MenuItem remove_btn;
    protected MenuItem clear_btn;
    protected final double cmenu_width = 150.0;

    protected sideButton up_btn;    public final int up_btn_index = 0;
    protected sideButton down_btn;  public final int down_btn_index = 1;
    protected sideButton left_btn;  public final int left_btn_index = 2;
    protected sideButton right_btn; public final int right_btn_index = 3;

    protected String NN_type;

    protected int NN_index;

    // 子类继承函数
    protected void initNNBtnPosition(double layout_pos_x, double layout_pos_y) {  // 初始化按钮位置及边界按钮位置
        setBtn_position(layout_pos_x, layout_pos_y);

        this.setNNBtnEvent();
    }

    protected void initNNBtnSize() {  // 初始化按钮大小
        this.setPrefWidth(btn_width);
        this.setPrefHeight(btn_height);
        this.setMinWidth(USE_PREF_SIZE);
        this.setMinHeight(USE_PREF_SIZE);
        this.setMaxWidth(USE_PREF_SIZE);
        this.setMaxHeight(USE_PREF_SIZE);
    }

    protected void initNNBtnContextMenu() {  // 初始化右键菜单
        btn_cmenu = new ContextMenu();
        btn_cmenu.setPrefWidth(cmenu_width);
        btn_cmenu.setMinWidth(USE_PREF_SIZE);
        btn_cmenu.setMaxWidth(USE_PREF_SIZE);

        show_btn_name = new MenuItem("Name: " + this.NN_name);
        SeparatorMenuItem separator_horizon = new SeparatorMenuItem();
        remove_btn = new MenuItem("Remove");
        clear_btn = new MenuItem("Clear(Remove all)");
        btn_cmenu.getItems().addAll(show_btn_name, separator_horizon, remove_btn, clear_btn);

        remove_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeNNBtn();
            }
        });

        clear_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _parent.clearAll();
            }
        });
    }

    protected void initSideBtn() {  // 初始化边界按钮
        up_btn    = new sideButton(this, up_btn_index);
        down_btn  = new sideButton(this, down_btn_index);
        left_btn  = new sideButton(this, left_btn_index);
        right_btn = new sideButton(this, right_btn_index);

        setSideBtnVisible(false);
    }

    public void setSideBtnVisible(boolean is_visible) {  // 设置边界按钮是否可见
        up_btn.setVisible(is_visible);
        down_btn.setVisible(is_visible);
        left_btn.setVisible(is_visible);
        right_btn.setVisible(is_visible);
    }

    protected void setSideBtnPosition() {  // 设置边界按钮的位置，主按钮位置改变时再调用
        double btn_up_edge = this.getLayoutY();  // 主按钮的四边坐标
        double btn_down_edge = this.getLayoutY() + btn_height;
        double btn_left_edge = this.getLayoutX();
        double btn_right_edge = this.getLayoutX() + btn_width;

        double btn_mid_x = this.getLayoutX() + btn_width / 2.0;  // 主按钮的两条中线坐标
        double btn_mid_y = this.getLayoutY() + btn_height / 2.0;

        double side_btn_width = up_btn.getSideBtn_width();  // 4个长宽都一样，取其中一个就行
        double side_btn_height = up_btn.getSideBtn_height();

        up_btn.setLayoutX( btn_mid_x - side_btn_width / 2.0 );
        up_btn.setLayoutY( btn_up_edge - side_btn_height / 2.0);

        down_btn.setLayoutX( btn_mid_x - side_btn_width / 2.0 );
        down_btn.setLayoutY( btn_down_edge - side_btn_height / 2.0);

        left_btn.setLayoutX( btn_left_edge - side_btn_width / 2.0);
        left_btn.setLayoutY( btn_mid_y - side_btn_height / 2.0);

        right_btn.setLayoutX( btn_right_edge - side_btn_width / 2.0);
        right_btn.setLayoutY( btn_mid_y - side_btn_height / 2.0);
    }

    protected void setNNBtnEvent() {  // 设置按钮事件
        // ------------------------------- mouse event ---------------------------------
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("mouse entered");
                setSideBtnVisible(true);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("mouse exited");
                setSideBtnVisible(false);
            }
        });

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setShowNNBtnAttribute();
                //setSideBtnVisible(true);
                //showAttributeForTest();
            }
        });

        // ------------------------------- context menu ---------------------------------
        this.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                show_btn_name.setText("name: " + NN_name);
                btn_cmenu.show(getSelf(), event.getScreenX(), event.getScreenY());
            }
        });

        // ------------------------------- drag & drop ---------------------------------
        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("" + getNN_index() + "." + getNN_name());
                dragboard.setContent(content);
            }
        });

        this.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag done");
            }
        });
    }

    private NNBuilderButton getSelf() {
        return this;
    }

    public void DraggingTo(double pos_x, double pos_y, double _opacity) {  // 参数为鼠标位置，也是按钮中心点位置
        Dragging_changeOpacity(_opacity);
        Dragging_changePosition(pos_x, pos_y);
    }

    private void Dragging_changeOpacity(double _opacity) {
        this.setOpacity(_opacity);

        up_btn.Dragging_changeOpacity(_opacity);
        down_btn.Dragging_changeOpacity(_opacity);
        left_btn.Dragging_changeOpacity(_opacity);
        right_btn.Dragging_changeOpacity(_opacity);
    }

    private void Dragging_changePosition(double pos_x, double pos_y) {
        setBtn_position(pos_x, pos_y);

        up_btn.DragTo    (pos_x, pos_y - btn_height / 2.0);
        down_btn.DragTo  (pos_x, pos_y + btn_height / 2.0);
        left_btn.DragTo  (pos_x - btn_width / 2.0, pos_y);
        right_btn.DragTo (pos_x + btn_width / 2.0, pos_y);
    }

    private void removeNNBtn() {
        for(int i = 0; i < 4; i ++) {
            getSideBtnList()[i].removeAllCLine();
        }
        _parent.removeNNBtn(this);
    }

    // ------------------------ 对外接口 -----------------------------
    public final double getBtn_width() {
        return btn_width;
    }
    public final double getBtn_height() {
        return btn_height;
    }

    public void setBtn_position(double layout_pos_x, double layout_pos_y) {  // 设置按钮位置，参数为鼠标位置，也是按钮中心点位置
        this.setLayoutX(layout_pos_x - btn_width / 2.0);
        this.setLayoutY(layout_pos_y - btn_height / 2.0);

        this.setSideBtnPosition();
    }

    public String getNN_type() {
        return this.NN_type;
    }

    public sideButton[] getSideBtnList() {  // 获取整个边界按钮组
        return new sideButton[]{up_btn, down_btn, left_btn, right_btn};
    }

    public sideButton getSideBtn(int side_btn_index) {  // 获取单个边界按钮
        if(side_btn_index == up_btn_index) return up_btn;
        else if(side_btn_index == down_btn_index) return down_btn;
        else if(side_btn_index == left_btn_index) return left_btn;
        else if(side_btn_index == right_btn_index) return right_btn;
        return new sideButton();
    }

    public void setNN_index(int nn_index) {
        this.NN_index = nn_index;
    }
    public int getNN_index() {
        return this.NN_index;
    }

    // -------------------------------------------------------------- 属性界面部分 -----------------------------------------------------------------
    protected String NN_name;
    protected int NN_id;

    protected NNAttribute attr_list[];

    protected int in_source_num;
    protected NNBuilderButton in_source_list[];
    protected int out_target_num;  // 出的部分没有入的部分重要，所有不需要记录有哪些节点

    protected String input_name = "";
    protected boolean flag_no_input = false;
    protected boolean flag_no_output = false;

    private void initNNBtnAttribute() {  // 初始化按钮属性
        this.NN_name = "";
        this.NN_id = -1;

        in_source_num = 0;
        this.in_source_list = new NNBuilderButton[5];

        out_target_num = 0;
    }

    public void setNN_name(String NN_name) {
        this.NN_name = NN_name;
        this.setText(NN_name);
    }

    public void setAttribute(String attr_name, String attr_value) {
        //System.out.println("  " + attr_name + ": " + attr_value);
        for(NNAttribute attr : attr_list) {
            //System.out.println("  " + attr.getAttribute_name() + ": " + attr.getAttribute_value());
            if(attr.getAttribute_name().equals(attr_name)) {
                attr.setAttribute_value(attr_value);
                return;
            }
        }
        //System.out.println("-------------------------------------------------------------");
    }

    public void setAttribute(int attr_num, String attr_value) {
        //System.out.println("  index" + attr_num + ": " + attr_value);
        attr_list[attr_num + 2].setAttribute_value(attr_value);
    }

    public void addInSource(NNBuilderButton in_source) {
        if(in_source_num >= in_source_list.length-1) {
            NNBuilderButton[] temp_list = new NNBuilderButton[in_source_num * 2 + 1];
            for(int i = 0; i < in_source_list.length; i ++) {
                temp_list[i] = in_source_list[i];
            }
            in_source_list = temp_list;
        }
        in_source_list[in_source_num] = in_source;
        in_source_num ++;


        /*for(int i = 0; i < in_source_num; i ++) {
            System.out.println(in_source_list[i].NN_name + ", " + in_source_list[i].getNN_id());
        }*/
    }

    public void removeInSource(NNBuilderButton in_source) {
        for(int i = 0; i < in_source_num; i ++) {
            if(in_source_list[i].getNN_id() == in_source.getNN_id()) {
                for(int j = i; j < in_source_num; j ++) {
                    in_source_list[j] = in_source_list[j+1];
                }
                in_source_num --;
                break;
            }
        }

        /*
        for(int i = 0; i < in_source_num; i ++) {
            System.out.println(in_source_list[i].NN_name + ", " + in_source_list[i].getNN_id());
        }*/
    }

    public int getIn_source_num() {
        return in_source_num;
    }

    public void addOutTarget(NNBuilderButton out_target) {
        out_target_num ++;
    }

    public void removeOutTarget(NNBuilderButton out_target) {
        out_target_num --;
    }

    public int getOut_target_num() {
        return out_target_num;
    }

    public boolean findInSource(NNBuilderButton in_source) {
        for(int i = 0; i < in_source_num; i ++) {
            if(in_source_list[i].getNN_id() == in_source.getNN_id())
                return true;
        }
        return false;
    }

    public NNBuilderButton[] getIn_source_list() {
        return in_source_list;
    }

    public String getNN_name() {
        return this.NN_name;
    }

    public void setNN_id(int NN_id) {
        this.NN_id = NN_id;
    }

    public int getNN_id() {
        return this.NN_id;
    }

    public NNAttribute[] getAttr_list() {
        return attr_list;
    }

    public NNAttribute getNNAttribute(int index) {
        return attr_list[index];
    }

    protected void setShowNNBtnAttribute() {
        _parent.showNNBtnAttribute(this);
    }
    /* test
    protected void showAttributeForTest() {
        System.out.println(this.NN_name);
        for(int i = 0; i < attr_list.length; i ++) {
            System.out.println(attr_list[i].getAttribute_name() + ": " + attr_list[i].getAttribute_value());
        }
        System.out.println("");
    }*/

    public void setInput_name(String input_name) {
        this.input_name = input_name;
    }

    public String getInput_name() {
        return this.input_name;
    }

    public void setFlag_no_input(boolean flag) {
        this.flag_no_input = flag;
    }

    public boolean getFlag_no_input() {
        return this.flag_no_input;
    }

    public void setFlag_no_output(boolean flag) {
        this.flag_no_output = flag;
    }

    public boolean getFlag_no_output() {
        return this.flag_no_output;
    }
}
