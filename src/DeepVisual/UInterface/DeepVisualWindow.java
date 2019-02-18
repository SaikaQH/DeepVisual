package DeepVisual.UInterface;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;

public class DeepVisualWindow extends JPanel implements Runnable {
    // 继承JPanel，使用JFXPanel会导致显示不出来

    private Project _project;
    private AnActionEvent _event;

    public DeepVisualWindow() {}

    public DeepVisualWindow(Project project, AnActionEvent event) {
        this._project = project;
        this._event = event;

        add(initAndShowGUI());

        this.isMaximumSizeSet();
    }

    private JFXPanel initAndShowGUI() {
        final JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(jfxPanel);
            }
        });
        return jfxPanel;
    }

    private void initFX(JFXPanel jfxPanel) {
        Parent root = null;

        // 载入插件界面的FXML文件
        FXMLLoader loader = new FXMLLoader();
        loader.setClassLoader(getClass().getClassLoader());
        loader.setLocation(getClass().getResource("DeepVisualWindow.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //root = FXMLLoader.load(getClass().getResource("DeepVisualWindow.fxml"));

        // 初始化界面，通过调用 Controller 的初始化函数
        DeepVisualWindowController controller = loader.getController();
        controller.set_project(_project);
        controller.set_event(_event);
        controller.initNNDrawer();

        Scene scene = new Scene(root);
        jfxPanel.setScene(scene);
    }

    public void run() {
        //
    }
}
