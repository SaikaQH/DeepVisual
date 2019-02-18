package DeepVisual.UInterface.UIactions.CompileAndFitPane.Attribute;

public class Callbacks {
    String callbacks_list[];

    public Callbacks() {
        initCallbacks();
    }

    private void initCallbacks() {
        callbacks_list = new String[] {
                "\'BaseLogger\'",
                "\'TerminateOnNaN\'",
                "\'ProgbarLogger\'",
                "\'History\'",
                "\'ModelCheckpoint\'",
                "\'EarlyStopping\'",
                "\'RemoteMonitor\'",
                "\'LearningRateScheduler\'",
                "\'TensorBoard\'",
                "\'ReduceLROnPlateau\'",
                "\'CSVLogger\'",
                "\'LambdaCallback\'",
                "None"
        };
    }

    public String[] getCallbacks_list() {
        return callbacks_list;
    }
}
