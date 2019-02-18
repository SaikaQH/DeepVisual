package DeepVisual.project;

import DeepVisual.UInterface.DeepVisualWindow;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

public class DeepVisualWindowComponent implements ProjectComponent {
    public static final String ID_TOOL_WINDOW = "DeepVisualWindow";
    private Project _project;
    private AnActionEvent _event;
    private DeepVisualWindow _DeepVisualWindow = null;

    public DeepVisualWindowComponent(Project project, AnActionEvent event) {
        this._project = project;
        this._event = event;
/*
        String project_path = project.getPresentableUrl();
        System.out.println(project_path);
*/
        initToolWindow();
    }

    private void initToolWindow() {
        this._DeepVisualWindow = new DeepVisualWindow(_project, _event);
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(this._project);
        ToolWindow toolWindow = toolWindowManager.registerToolWindow(DeepVisualWindowComponent.ID_TOOL_WINDOW, false, ToolWindowAnchor.RIGHT);
        //toolWindowManager.setMaximized(toolWindow, true);
        final Content content = ContentFactory.SERVICE.getInstance().createContent(getNNDrawerWindowPanel(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public DeepVisualWindow getNNDrawerWindowPanel() {
        return this._DeepVisualWindow;
    }
}
