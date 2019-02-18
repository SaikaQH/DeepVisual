package DeepVisual.UInterface.UIactions.NNBuilderPane;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.*;

public class sideButton extends Button {
    private final double btn_width = 6;
    private final double btn_height = 6;

    private final int up_btn_index = 0;
    private final int down_btn_index = 1;
    private final int left_btn_index = 2;
    private final int right_btn_index = 3;

    private NNBuilderButton _host;  // 宿主按钮，用于数据交换与函数调用
    private int _pos_index;  // 用于记录该边界按钮在宿主按钮的哪个位置

    private int out_CLine_num;
    private connectLine out_CLine_list[];  // 输出线条列表，整体可看成链表
    private int in_CLine_num;
    private connectLine in_CLine_list[];  // 输入线条列表，整体可看成链表

    private int CLine_id;

    public sideButton() {
        initSideBtnSize();
    }

    public sideButton(NNBuilderButton _host, int _pos_index) {
        this();
        this._host = _host;
        this._pos_index = _pos_index;
        this.CLine_id = 0;

        initCLineList();
        setSideBtnEvent();
    }

    private void initSideBtnSize() {  // 初始化按钮大小
        this.setPrefWidth(btn_width);
        this.setPrefHeight(btn_height);
        this.setMinWidth(USE_PREF_SIZE);
        this.setMinHeight(USE_PREF_SIZE);
        this.setMaxWidth(USE_PREF_SIZE);
        this.setMaxHeight(USE_PREF_SIZE);
    }

    private void initCLineList() {
        out_CLine_num = 0;
        in_CLine_num = 0;

        out_CLine_list = new connectLine[5];
        in_CLine_list = new connectLine[5];
    }

    private void setSideBtnEvent() {  // 添加各类事件
        // ------------------------------- mouse -------------------------------------
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("mouse entered");
                setVisible(true);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("mouse exited");
                setVisible(false);
            }
        });

        // ------------------------------- drag & drop ---------------------------------
        this.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("" + _host.getNN_index() + "." + _pos_index);
                dragboard.setContent(content);

                getSelf()._host._parent.setAllSideBtnVisible(true);
                getSelf()._host._parent.setDragCLineStart(getSelf().getSideBtnMidPoint()[0], getSelf().getSideBtnMidPoint()[1]);
            }
        });

        this.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag entered");
            }
        });

        this.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag exited");
            }
        });

        this.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getGestureSource() != getSelf()  /* 拖拽 不来自自己 */
                        && event.getGestureSource().getClass() == new sideButton().getClass()  /* 拖拽 来自边界按钮 */
                        && event.getDragboard().hasString()  /* 剪切板中有String */ ) {
                    //System.out.println("drag over");
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
            }
        });

        this.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if(event.getGestureSource().getClass() == new sideButton().getClass()) {
                    //System.out.println("drag connect dropped");
                    _host._parent.setDragCLineVisible(false);

                    Dragboard dragboard = event.getDragboard();
                    String[] sideBtn_index = dragboard.getString().split("\\.", 0);

                    // ------------------------------------------------- 按钮编号 ----------------------------------------------- 边界按钮编号 --------------------------------
                    sideButton start_point = _host.get_parent().getNNbtn(Integer.valueOf(sideBtn_index[0]).intValue()).getSideBtn(Integer.valueOf(sideBtn_index[1]).intValue());
                    sideButton end_point = getSelf();

                    if(start_point._host.out_target_num > 0) return ;
                    if(end_point._host.in_source_num > 0) return ;

                    if(start_point._host.getFlag_no_output()) return ;
                    if(end_point._host.getFlag_no_input()) return ;

                    if(end_point.get_host().findInSource(start_point.get_host())) return ;  // 防止重复连接

                    connectLine c_line = new connectLine(start_point, end_point);
                    _host.get_parent().addConnectLine(c_line);
                }
            }
        });

        this.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                //System.out.println("drag done");
                setCursor(Cursor.DEFAULT);
                getSelf()._host._parent.setAllSideBtnVisible(false);
            }
        });
    }

    private sideButton getSelf() {  // 用于在事件中标记本身
        return this;
    }

    public double getSideBtn_width() {
        return btn_width;
    }

    public double getSideBtn_height() {
        return btn_height;
    }

    public NNBuilderButton get_host() { return this._host; }

    public int get_pos_index() {
        return this._pos_index;
    }

    public double[] getSideBtnMidPoint() {
        double mid_point[] = new double[]{
                getLayoutX() + btn_width / 2.0,
                getLayoutY() + btn_height / 2.0
        };
        return mid_point;
    }

    public int getCLine_id() {
        CLine_id ++;
        return this.CLine_id - 1;
    }

    public void addOutCLine(connectLine out_cline) {  // 添加输出线条
        if(out_CLine_num >= out_CLine_list.length-1) {
            connectLine[] temp_list = new connectLine[out_CLine_num * 2 + 1];
            for(int i = 0; i < out_CLine_list.length; i ++) {
                temp_list[i] = out_CLine_list[i];
            }
            out_CLine_list = temp_list;
        }

        out_CLine_list[out_CLine_num] = out_cline;
        out_CLine_num ++;

        this.get_host().addOutTarget(out_cline.getEndPoint().get_host());

        /* test
        System.out.println("out");
        for(int i = 0; i < out_CLine_num; i ++) {
            System.out.println(out_CLine_list[i].getCL_name());
        }*/
    }

    public void removeOutCLine(connectLine out_cline) {
        for(int i = 0; i < out_CLine_num;i ++) {  // 从出度线列表中删除
            if(out_CLine_list[i].getCL_id().equals(out_cline.getCL_id())) {
                for(int j = i; j < out_CLine_num; j ++) {
                    out_CLine_list[j] = out_CLine_list[j+1];
                }
                out_CLine_num --;
                break;
            }
        }

        this.get_host().removeOutTarget(out_cline.getEndPoint().get_host());
    }

    public void setOut_CLine_num(int out_CLine_num) {
        this.out_CLine_num = out_CLine_num;
    }

    public int getOut_CLine_num() {
        return out_CLine_num;
    }

    public connectLine[] getOut_CLine_list() {
        return out_CLine_list;
    }

    public void addInCLine(connectLine in_cline) {  // 添加输入线条
        if(in_CLine_num >= in_CLine_list.length-1) {
            connectLine[] temp_list = new connectLine[in_CLine_num * 2 + 1];
            for(int i = 0; i < in_CLine_list.length; i ++) {
                temp_list[i] = in_CLine_list[i];
            }
            in_CLine_list = temp_list;
        }

        in_CLine_list[in_CLine_num] = in_cline;
        in_CLine_num ++;

        this.get_host().addInSource(in_cline.getStartPoint().get_host());

        /* test
        System.out.println("in");
        for(int i = 0; i < in_CLine_num; i ++) {
            System.out.println(in_CLine_list[i].getCL_name());
        }*/
    }

    public void removeInCLine(connectLine in_cline) {
        for(int i = 0; i < in_CLine_num;i ++) {  // 从入度线列表中删除
            if(in_CLine_list[i].getCL_id().equals(in_cline.getCL_id())) {
                for(int j = i; j < in_CLine_num; j ++) {
                    in_CLine_list[j] = in_CLine_list[j+1];
                }
                in_CLine_num --;
                break;
            }
        }

        this.get_host().removeInSource(in_cline.getStartPoint().get_host());
    }

    public void setIn_CLine_num(int in_CLine_num) {
        this.in_CLine_num = in_CLine_num;
    }

    public int getIn_CLine_num() {
        return in_CLine_num;
    }

    public connectLine[] getIn_CLine_list() {
        return in_CLine_list;
    }

    public void removeAllCLine() {
        for(int i = 0; i < out_CLine_num; i ++) {  // 删除出度线
            out_CLine_list[i].getEndPoint().removeInCLine(out_CLine_list[i]);
            _host.get_parent().removeConnectLine(out_CLine_list[i]);
        }
        for(int i = 0; i < in_CLine_num; i ++) {  // 删除入度线
            in_CLine_list[i].getStartPoint().removeOutCLine(in_CLine_list[i]);
            _host.get_parent().removeConnectLine(in_CLine_list[i]);
        }
        /*
        for(int i = 0; i < out_CLine_num; i ++) {  // 遍历出度线
            for(int j = 0; j < out_CLine_list[i].getEndPoint().getIn_CLine_num(); j ++) {  // 在出度线终点的入度线中寻找该出度线
                if(out_CLine_list[i].getEndPoint().getIn_CLine_list()[j].getCL_id().equals(out_CLine_list[i].getCL_id())) {  // 根据CL_id查找，找到了，则在入度线列表中删除该线
                    for(int k = j; k < out_CLine_list[i].getEndPoint().getIn_CLine_num(); k ++) {  // 通过调整列表实现删除操作
                        out_CLine_list[i].getEndPoint().getIn_CLine_list()[k] = out_CLine_list[i].getEndPoint().getIn_CLine_list()[k+1];
                    }
                    out_CLine_list[i].getEndPoint().setIn_CLine_num(out_CLine_list[i].getEndPoint().getIn_CLine_num() - 1);  // 入度线总数减一
                    //out_CLine_list[i].getEndPoint().get_host().removeInSource(out_CLine_list[i].getStartPoint().get_host());
                    break;
                }
            }
            _host.get_parent().removeConnectLine(out_CLine_list[i]);
        }
        for(int i = 0; i < in_CLine_num; i ++) {  // 遍历入度线
            for(int j = 0; j < in_CLine_list[i].getStartPoint().getOut_CLine_num(); j ++) {  // 在入度线起点的出度线中寻找该入度线
                if(in_CLine_list[i].getStartPoint().getOut_CLine_list()[j].getCL_id().equals(in_CLine_list[i].getCL_id())) {  // 根据CL_id查找，找到了，则在出度线列表中删除该线
                    for(int k = j; k < in_CLine_list[i].getStartPoint().getOut_CLine_num(); k ++) {  // 通过调整列表实现删除操作
                        in_CLine_list[i].getStartPoint().getOut_CLine_list()[k] = in_CLine_list[i].getStartPoint().getOut_CLine_list()[k+1];
                    }
                    in_CLine_list[i].getStartPoint().setOut_CLine_num(in_CLine_list[i].getStartPoint().getOut_CLine_num() - 1);  // 出度线总数减一
                    //in_CLine_list[i].getStartPoint().get_host().removeOutTarget(in_CLine_list[i].getEndPoint().get_host());
                    break;
                }
            }
            _host.get_parent().removeConnectLine(in_CLine_list[i]);
        }*/
    }

    public void Dragging_changeOpacity(double _opacity) {
        this.setOpacity(_opacity);

        // 出度线条
        for(int i = 0; i < out_CLine_num; i ++) {
            out_CLine_list[i].setCLineOpacity(_opacity);
        }

        // 入度线条
        for(int i = 0; i < in_CLine_num; i ++) {
            in_CLine_list[i].setCLineOpacity(_opacity);
        }

    }

    public void DragTo(double pos_x, double pos_y) {  // 参数为中心点位置
        this.setLayoutX(pos_x - btn_width / 2.0);
        this.setLayoutY(pos_y - btn_height / 2.0);

        // 出度线条
        for(int i = 0; i < out_CLine_num; i ++) {
            out_CLine_list[i].setCLineProperty(
                    pos_x,
                    pos_y,
                    out_CLine_list[i].getEndPoint().getSideBtnMidPoint()[0],
                    out_CLine_list[i].getEndPoint().getSideBtnMidPoint()[1]
            );
        }

        // 入度线条
        for(int i = 0; i < in_CLine_num; i ++) {
            in_CLine_list[i].setCLineProperty(
                    in_CLine_list[i].getStartPoint().getSideBtnMidPoint()[0],
                    in_CLine_list[i].getStartPoint().getSideBtnMidPoint()[1],
                    pos_x,
                    pos_y
            );
        }
    }
}
