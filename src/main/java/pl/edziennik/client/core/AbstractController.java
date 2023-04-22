package pl.edziennik.client.core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.common.factory.DictionaryFactory;
import pl.edziennik.client.common.factory.ProgressFactory;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController implements Initializable {

    @FXML
    protected Button addButton, editButton, showButton, deleteButton, cancelButton, saveButton;

    @FXML
    public Button refreshButton;

    @FXML
    public MenuButton menuButton;

    @FXML
    protected MenuItem selectAllMenuItem, unselectAllMenuItem;

    protected final DialogFactory dialogFactory;
    protected final ProgressFactory progressFactory;
    protected final DictionaryFactory dictionaryFactory;

    public AbstractController() {
        this.dialogFactory = DialogFactory.getInstance();
        this.progressFactory = ProgressFactory.getInstance();
        this.dictionaryFactory = DictionaryFactory.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createActions();
        setSceneSettings();
        setTableColumns();
        setSceneValidators();
        fetchStageData();
        createDictionaries();
    }

    protected void fetchStageData() {

    }


    protected void setTableColumns() {
    }

    protected void setSceneValidators() {

    }

    protected void createDictionaries() {

    }

    protected abstract void createActions();

    protected abstract void setSceneSettings();

    protected abstract Stage getActualStage();

    protected <T extends TableViewSelection> void initializeSelectUnselectAllMenuItemAction(TableView<T> tableView) {
        selectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> {
                tableItem.getSelect().setSelected(true);
                tableView.getSelectionModel().selectLast();
            });
        });
        unselectAllMenuItem.setOnAction(item -> {
            tableView.getItems().forEach(tableItem -> tableItem.getSelect().setSelected(false));
            tableView.getSelectionModel().clearSelection();
        });
    }

}
