package pl.edziennik.client.core;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import pl.edziennik.client.common.DialogFactory;
import pl.edziennik.client.common.ProgressFactory;
import pl.edziennik.client.rest.pojo.ConfigurationPojo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractController implements Initializable{

    protected final DialogFactory dialogFactory;
    protected final ProgressFactory progressFactory;

    public AbstractController() {
        this.dialogFactory = DialogFactory.getInstance();
        this.progressFactory = ProgressFactory.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createActions();
        setSceneSettings();
        setTableColumns();
        setSceneValidators();
        fetchStageData();
    }

    protected void fetchStageData(){

    }


    protected void setTableColumns() {
    }

    protected void setSceneValidators(){

    }

    protected abstract void createActions();

    protected abstract void setSceneSettings();

    protected abstract Stage getActualStage();

}
