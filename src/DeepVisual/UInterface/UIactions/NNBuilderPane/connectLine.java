package DeepVisual.UInterface.UIactions.NNBuilderPane;

import DeepVisual.UInterface.DeepVisualWindowController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class connectLine extends Label {
    private sideButton startPoint;
    private sideButton endPoint;
    private DeepVisualWindowController _parent;
    private String CL_id;

    private double s_x, s_y, e_x, e_y;

    private String CL_name;

    private double _width;
    private final double _height = 2;
    private final Color _bg_color = Color.GRAY;

    private double mid_point[];

    private double _rotate;

    private Label arrow;
    private final String a_img = "/icon/arrow.jpg";
    private double a_width;
    private final double a_height = 10;
    private double a_rotate;

    private Button delete_btn;
    private final String d_text = "--";
    private final double d_width = 9;
    private final double d_height = 9;

    private ContextMenu cl_cmenu;
    private MenuItem sp_name;
    private MenuItem ep_name;
    private MenuItem remove_cl;
    private final double cmenu_width = 150.0;

    public connectLine() {
        this.setBackground(new Background(new BackgroundFill(_bg_color, null, null)));
        this.setAlignment(Pos.CENTER);

        arrow = new Label();
        //arrow.setText(">");
        //arrow.setTextFill(_bg_color);
        //arrow.setFont(new Font(20));
        setCLineBG();
        arrow.setAlignment(Pos.CENTER_RIGHT);

        delete_btn = new Button(d_text);
        delete_btn.setFont(new Font(4));
        delete_btn.setAlignment(Pos.TOP_LEFT);
        delete_btn.setPrefWidth(d_width);
        delete_btn.setMaxWidth(USE_PREF_SIZE);
        delete_btn.setMaxWidth(USE_PREF_SIZE);
        delete_btn.setPrefHeight(d_height);
        delete_btn.setMaxHeight(USE_PREF_SIZE);
        delete_btn.setMaxHeight(USE_PREF_SIZE);
        delete_btn.setVisible(false);

        this.setCLineVisible(true);
    }

    public connectLine(sideButton startPoint, sideButton endPoint) {
        this();

        this.startPoint = startPoint;
        this.endPoint = endPoint;

        this._parent = startPoint.get_host().get_parent();

        initCL_id();

        initCL_name();

        initCLineContextMenu();

        setCLineProperty(
                startPoint.getSideBtnMidPoint()[0],
                startPoint.getSideBtnMidPoint()[1],
                endPoint.getSideBtnMidPoint()[0],
                endPoint.getSideBtnMidPoint()[1]  );

        addToSideBtn();

        setCLineEvent();
    }

    public connectLine(double _opacity) {
        this();
        this.setCLineOpacity(_opacity);
    }

    private void initCL_name() {
        CL_name = "connect line: from " + startPoint.get_host().getNN_name() + " to " + endPoint.get_host().getNN_name();
    }

    private void initCL_id() {
        CL_id = "" + startPoint.get_host().getNN_id() + "." + startPoint.get_pos_index() + "." + startPoint.getCLine_id();
    }

    protected void initCLineContextMenu() {  // 初始化右键菜单
        cl_cmenu = new ContextMenu();
        cl_cmenu.setPrefWidth(cmenu_width);
        cl_cmenu.setMinWidth(USE_PREF_SIZE);
        cl_cmenu.setMaxWidth(USE_PREF_SIZE);

        sp_name = new MenuItem("From: " + startPoint.get_host().getNN_name());
        ep_name = new MenuItem("To  : " + endPoint.get_host().getNN_name());
        SeparatorMenuItem separator_horizon = new SeparatorMenuItem();
        remove_cl = new MenuItem("Remove");
        cl_cmenu.getItems().addAll(sp_name, ep_name, separator_horizon, remove_cl);

        remove_cl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeCLine();
            }
        });
    }

    private void addToSideBtn() {  // 将连接线添加到边界按钮中，相当于链表指针的功能
        startPoint.addOutCLine(this);
        endPoint.addInCLine(this);
    }

    private void setCLineEvent() {
        // ------------------------------- context menu ---------------------------------
        this.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                sp_name.setText("From: " + startPoint.get_host().getNN_name());
                ep_name.setText("To  : " + endPoint.get_host().getNN_name());
                cl_cmenu.show(getSelf(), event.getScreenX(), event.getScreenY());
            }
        });

        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delete_btn.setVisible(true);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delete_btn.setVisible(false);
            }
        });

        delete_btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delete_btn.setVisible(true);
            }
        });

        delete_btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delete_btn.setVisible(false);
            }
        });

        delete_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                removeCLine();
            }
        });
    }

    public void removeCLine() {
        startPoint.removeOutCLine(this);
        endPoint.removeInCLine(this);
        _parent.removeConnectLine(this);
    }

    public void drawDragConnectLine(double s_x, double s_y, double e_x, double e_y) {
        setCLineProperty(s_x, s_y, e_x, e_y);
    }

    public void setCLineVisible(boolean is_visible) {
        this.setVisible(is_visible);
        arrow.setVisible(is_visible);
    }

    public void setCLineOpacity(double _opacity) {
        this.setOpacity(_opacity);
        arrow.setOpacity(_opacity);
    }

    public connectLine getSelf() {
        return this;
    }

    public String getCL_name() {
        return CL_name;
    }

    public String getCL_id() {
        return CL_id;
    }

    public sideButton getStartPoint() {
        return startPoint;
    }

    public sideButton getEndPoint() {
        return endPoint;
    }

    public Label getCLineArrow() {
        return arrow;
    }

    public Button getCLineDeleteBtn() {
        return delete_btn;
    }

    // ------------------------------------------- calculate function ----------------------------------------------------

    public void setCLineProperty(double s_x, double s_y, double e_x, double e_y) {
        this.s_x = s_x;
        this.s_y = s_y;
        this.e_x = e_x;
        this.e_y = e_y;

        calcCLineSize();
        calcCLineMidPoint();
        calcCLineRotate();
        setCLinePosition();
    }

    private void calcCLineSize(/*double s_x, double s_y, double e_x, double e_y*/) {  // 设定长宽
        _width = Math.hypot(e_x - s_x, e_y - s_y);  // hypot() 平方和的二次方根，用于取长度

        this.setPrefWidth(_width);
        this.setPrefHeight(_height);
        this.setMinWidth(USE_PREF_SIZE);
        this.setMinHeight(USE_PREF_SIZE);
        this.setMaxWidth(USE_PREF_SIZE);
        this.setMaxHeight(USE_PREF_SIZE);

        a_width = _width;  // 把arrow的宽度设成与母体的宽度一样，方便定位和旋转

        arrow.setPrefWidth(a_width);
        arrow.setPrefHeight(a_height);
        arrow.setMinWidth(USE_PREF_SIZE);
        arrow.setMinHeight(USE_PREF_SIZE);
        arrow.setMaxWidth(USE_PREF_SIZE);
        arrow.setMaxHeight(USE_PREF_SIZE);
    }

    private void calcCLineMidPoint(/*double s_x, double s_y, double e_x, double e_y*/) {  // 设定中点，方便后续计算
        double pos_x = (s_x + e_x) / 2.0;
        double pos_y = (s_y + e_y) / 2.0;
        mid_point = new double[]{pos_x, pos_y};

        delete_btn.setLayoutX(pos_x - d_width / 2.0);
        delete_btn.setLayoutY(pos_y - d_height / 2.0);
    }

    private void calcCLineRotate(/*double s_x, double s_y, double e_x, double e_y*/) {  // 设定旋转角度
        double dis_x = e_x - s_x;
        double dis_y = s_y - e_y;
        double long_edge = _width;
        double cos_rotate = dis_x / long_edge;
        _rotate = 0 - Math.signum(dis_y) * Math.acos(cos_rotate) * 180 / Math.PI;

        a_rotate = _rotate;  // arrow的旋转角度与母体一样
    }

    private void setCLineBG() {  // 设定背景，主要用于显示箭头图案
        Image CLineBG;
        CLineBG = new Image(getClass().getResourceAsStream(a_img), a_height, a_height, false, false);
        arrow.setGraphic(new ImageView(CLineBG));
    }

    private void setCLinePosition() {  // 设定位置
        this.setLayoutX(mid_point[0] - _width / 2.0);
        this.setLayoutY(mid_point[1] - _height / 2.0);
        this.setRotate(_rotate);

        arrow.setLayoutX(mid_point[0] - a_width / 2.0);
        arrow.setLayoutY(mid_point[1] - a_height / 2.0);
        arrow.setRotate(a_rotate);
    }
    // -------------------------------------------------------------------------------------------------------------------
}
