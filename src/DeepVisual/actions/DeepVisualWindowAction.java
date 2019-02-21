package DeepVisual.actions;

import DeepVisual.UInterface.DeepVisualWindow;
import DeepVisual.project.DeepVisualWindowComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;

public class DeepVisualWindowAction extends AnAction {
    private ToolWindow getToolWindow(Project project) {
        return ToolWindowManager.getInstance(project).getToolWindow("DeepVisualWindow");
    }

    private Project getProject(AnActionEvent event) {
        return (Project) event.getDataContext().getData("project");
    }

    public void actionPerformed(final AnActionEvent event) {
        Project project = getProject(event);

        //PsiFile psiFile = event.getData(LangDataKeys.PSI_FILE);
        //System.out.println(psiFile.getContainingDirectory().toString() + "\\" + psiFile.getName());
        /*Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        if(editor == null) System.out.println("null");
        Document document = editor.getDocument();
        System.out.println(document.getText());*/
/*
        PsiFile psifile = event.getData(LangDataKeys.PSI_FILE);
        String filename = psifile.getName();
        System.out.println(filename);
        String code = psifile.getText();
        System.out.println(code);
*/
        DeepVisualWindow deepVisualWindow = new DeepVisualWindowComponent(project, event).getDeepVisualWindowPanel();
        ToolWindow toolWindow = getToolWindow(project);
        toolWindow.activate(deepVisualWindow);
    }
}
