package pl.edziennik.client.common.builder;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.core.DictionaryItemModel;
import pl.edziennik.client.core.StageManager;
import pl.edziennik.client.core.TableSelectionMode;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.exception.ViewException;
import pl.edziennik.client.rest.client.response.ApiErrors;
import pl.edziennik.client.util.NodeUtils;
import pl.edziennik.client.util.ResourceUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class CommonStageBuilder {

    public static final ButtonType YES_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_YES_KEY.value()));
    public static final ButtonType NO_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_NO_KEY.value()));
    public static final ButtonType OK_BUTTON = new ButtonType(ResourceUtil.getMessage(ResourceConst.BUTTON_OK_KEY.value()));

    private static final Logger LOGGER = Logger.getLogger(CommonStageBuilder.class.getName());

    public static final String ALERT_STYLES_PATCH = eDziennikApplication.class.getResource(ResourceConst.ALERT_STYLES_ADDRESS.value()).toExternalForm();
    public static final String TABLE_STYLES_PATCH = eDziennikApplication.class.getResource(ResourceConst.TABLE_STYLES_ADDRESS.value()).toExternalForm();
    public static final String GLOBAL_COLORS_STYLES_PATCH = eDziennikApplication.class.getResource(ResourceConst.GLOBAL_COLOR_STYLES.value()).toExternalForm();
    public static final String DICTIONARY_STYLES_ADDRESS = eDziennikApplication.class.getResource(ResourceConst.DICTIONARY_STYLES_ADDRESS.value()).toExternalForm();
    public static final String POPUP_STYLES_ADDRESS = eDziennikApplication.class.getResource(ResourceConst.POPUP_STYLES_ADDRESS.value()).toExternalForm();

    public static final ImageView INFORMATION_ICON = new ImageView(eDziennikApplication.class.getResource(ResourceConst.INFORMATION_ICON_ADDRESS.value()).toExternalForm());
    private static final ImageView SUCCESS_ICON = new ImageView(eDziennikApplication.class.getResource(ResourceConst.SUCCESS_ICON_ADDRESS.value()).toExternalForm());
    private static final ImageView ERROR_ICON = new ImageView(eDziennikApplication.class.getResource(ResourceConst.ERROR_ICON_ADDRESS.value()).toExternalForm());

    private CommonStageBuilder() {
    }

    public static StageBuilder stageBuilder() {
        return new StageBuilder();
    }

    public static DialogBuilder dialogBuilder() {
        return new DialogBuilder();
    }

    public static DictionaryBuilder dictionaryBuilder() {
        return new DictionaryBuilder();
    }

    public static class DialogBuilder {

        private String styles;
        private Modality modality;
        private StageStyle stageStyle;
        private Stage owner;
        private String title;
        private String contentText;
        private String headerText;
        private String stackTrace;
        private Alert.AlertType alertType;
        private ApiErrors[] errors;
        private boolean isSearchActualStage;
        private boolean isDefaultContentText;
        private String cause;
        private String plainContentText;

        public DialogBuilder withCssStyles(String styles) {
            this.styles = styles;
            return this;
        }

        public DialogBuilder withModality(Modality modality) {
            this.modality = modality;
            return this;
        }

        public DialogBuilder withStageStyle(StageStyle stageStyle) {
            this.stageStyle = stageStyle;
            return this;
        }

        public DialogBuilder withOwner(Stage owner) {
            this.owner = owner;
            return this;
        }

        public DialogBuilder withContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public DialogBuilder withPlainContentText(String plainContentText) {
            this.plainContentText = plainContentText;
            return this;
        }

        public DialogBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogBuilder withHeaderText(String headerText) {
            this.headerText = headerText;
            return this;
        }

        public DialogBuilder withAlertType(Alert.AlertType alertType) {
            this.alertType = alertType;
            return this;
        }

        public DialogBuilder withStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public DialogBuilder withDefaultContentText() {
            this.isDefaultContentText = true;
            return this;
        }

        public DialogBuilder withSearchActualStage() {
            this.isSearchActualStage = true;
            return this;
        }

        public DialogBuilder withErrors(ApiErrors[] errors) {
            this.errors = errors;
            return this;
        }

        public DialogBuilder withPlainCause(String cause) {
            this.cause = cause;
            return this;
        }

        public Alert build() {
            Alert alert;
            switch (alertType) {
                case CONFIRMATION -> {
                    alert = new Alert(alertType, null, NO_BUTTON, YES_BUTTON);
                    alert.getDialogPane().setGraphic(INFORMATION_ICON);
                }

                case ERROR -> {
                    alert = new Alert(alertType, null, OK_BUTTON);
                    alert.getDialogPane().setGraphic(ERROR_ICON);
                }

                default -> {
                    alert = new Alert(alertType, null, OK_BUTTON);
                    alert.getDialogPane().setGraphic(SUCCESS_ICON);
                }
            }

            if (owner != null) {
                alert.initOwner(owner);
            }

            if (styles != null) {
                alert.getDialogPane().getStylesheets().add(styles);
            }
            if (title != null) {
                alert.setTitle(ResourceUtil.getMessage(title));
            }
            if (headerText != null) {
                alert.setHeaderText(ResourceUtil.getMessage(headerText));
            }

            if (modality != null) {
                alert.initModality(modality);
            }
            if (stageStyle != null) {
                alert.initStyle(stageStyle);
            }

            if (contentText != null) {
                if (cause != null) {
                    alert.setContentText(ResourceUtil.getMessage(contentText) + "\n" + ResourceUtil.getMessage(cause));
                } else {
                    alert.setContentText(ResourceUtil.getMessage(contentText));
                }

            } else if (isDefaultContentText) {
                alert.setContentText(ResourceUtil.getMessage(ResourceConst.SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value()));
            }

            if (plainContentText != null) {
                alert.setContentText(plainContentText);
            }

            if (errors != null) {
                alert.setContentText(ResourceUtil.getMessage(ResourceConst.ERROR_DIALOG_MESSAGE_KEY.value())
                        + "\n" + Arrays.stream(errors)
                        .map(ApiErrors::getCause)
                        .collect(Collectors.joining("\n-", "-", "\n")));
            }

            if (stackTrace != null && stackTrace.length() > 5) {
                GridPane stackTraceListView = getStackTraceListView(stackTrace);
                alert.getDialogPane().setExpandableContent(stackTraceListView);
            }

            if (isSearchActualStage) {
                Stage stage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
                if (stage != null) {
                    alert.initOwner(stage);
                }
            }

            return alert;


        }

        private GridPane getStackTraceListView(String stackTrace) {
            Label label = new Label();

            TextArea textArea = new TextArea(stackTrace);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            return expContent;
        }

    }

    public static class StageBuilder {

        private int width;
        private int height;
        private int maxWidth;
        private int minWidth;
        private int maxHeight;
        private int minHeight;

        private String viewAddress;
        private String title;

        private StageStyle stageStyle;
        private Modality stageModality;
        private Stage owner;
        private ShowMode showMode;
        private Color color;

        private Button button;

        private boolean isFocusRequest;
        private boolean isAlwaysOnTop;
        private boolean isResizable;
        private boolean isSearchActualStage;
        private boolean isSetPositionCenter;

        private boolean isMainStage;


        public StageBuilder withSetPositionToCenter(boolean isSetPositionCenter) {
            this.isSetPositionCenter = isSetPositionCenter;
            return this;
        }

        public StageBuilder withFillColor(Color color) {
            this.color = color;
            return this;
        }

        public StageBuilder withSearchActualStage(boolean isSearchActualStage) {
            this.isSearchActualStage = isSearchActualStage;
            return this;
        }

        public StageBuilder withShowMode(ShowMode showMode) {
            this.showMode = showMode;
            return this;
        }

        public StageBuilder withView(String viewAddress) {
            this.viewAddress = viewAddress;
            return this;
        }

        public StageBuilder withAlwaysOnTop(boolean isAlwaysOnTop) {
            this.isAlwaysOnTop = isAlwaysOnTop;
            return this;
        }

        public StageBuilder withFocusRequest(boolean isFocusRequest) {
            this.isFocusRequest = isFocusRequest;
            return this;
        }

        public StageBuilder withOwner(Stage owner) {
            this.owner = owner;
            return this;
        }

        public StageBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public StageBuilder withModality(Modality stageModality) {
            this.stageModality = stageModality;
            return this;
        }

        public StageBuilder withStyle(StageStyle stageStyle) {
            this.stageStyle = stageStyle;
            return this;
        }

        public StageBuilder withButton(Button buttonToDisable) {
            this.button = buttonToDisable;
            return this;
        }

        public StageBuilder withResizable(boolean isResizable) {
            this.isResizable = isResizable;
            return this;
        }

        public StageBuilder isMainStage(boolean isMainStage){
            this.isMainStage = isMainStage;
            return this;
        }

        public StageBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public StageBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public StageBuilder withMaxWidth(int maxWidth) {
            this.maxWidth = maxWidth;
            return this;
        }

        public StageBuilder withMinWidth(int minWidth) {
            this.minWidth = minWidth;
            return this;
        }

        public StageBuilder withMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public StageBuilder withMinHeight(int minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public <T> T build() {
            FXMLLoader fxmlLoader = NodeUtils.getLoaderWithResources(eDziennikApplication.class.getResource(viewAddress));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load(), width, height);
            } catch (IOException e) {
                LOGGER.severe(e.getMessage());
                LOGGER.severe(e.getCause().getMessage());
                throw new ViewException(VIEW_EXCEPTION_MESSAGE_KEY.value());
            }
            T controller = fxmlLoader.getController();
            Stage stage = new Stage();

            if (stageStyle != null) {
                stage.initStyle(stageStyle);
            }
            if (minWidth != 0) {
                stage.setMinWidth(minWidth);
            }
            if (maxWidth != 0) {
                stage.setMaxWidth(maxWidth);
            }
            if (maxHeight != 0) {
                stage.setMaxHeight(maxHeight);
            }
            if (minHeight != 0) {
                stage.setMinHeight(minHeight);
            }
            if (title != null) {
                stage.setTitle(ResourceUtil.getMessage(title));
            }
            if (stageModality != null) {
                stage.initModality(stageModality);
            }
            if (color != null) {
                scene.setFill(color);
            }
            if (owner != null) {
                stage.initOwner(owner);
            }
            if (isFocusRequest) {
                stage.requestFocus();
            }
            if (isSearchActualStage) {
                searchActualStageAndSetPosition(scene, stage);
            }
            if (isSetPositionCenter) {
                if (owner != null) {
                    setPositionToCenter(scene, stage, owner);
                }
            }

            stage.setResizable(isResizable);
            stage.setAlwaysOnTop(isAlwaysOnTop);

            stage.setScene(scene);

            if (button != null) {
                stage.setUserData(button);
                StageManager.setIsShowing(stage);
            }

            if (isMainStage){
                StageManager.setMainStage(stage);
            }

            switch (showMode) {
                case OPEN_ABOVE -> {
                    stage.show();
                    return null;
                }
                case OPEN_ABOVE_AND_RETURN_CONTROLLER -> {
                    stage.show();
                    return controller;
                }
                case CLOSE_PREVIOUS_AND_RETURN_CONTROLLER -> {
                    owner.close();
                    stage.show();
                    return controller;
                }
                default -> {
                    if (owner != null) {
                        owner.close();
                    }
                    stage.show();
                    return null;
                }
            }

        }

        private void searchActualStageAndSetPosition(Scene scene, Stage stage) {
            Stage actualStage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            if (actualStage != null) {
                stage.initOwner(actualStage);
                setPositionToCenter(scene, stage, actualStage);
            }
        }

        private void setPositionToCenter(Scene scene, Stage stage, Stage actualStage) {
            stage.setX(actualStage.getX() + actualStage.getWidth() / 2 - scene.getWidth() / 2);
            stage.setY(actualStage.getY() + actualStage.getHeight() / 2 - scene.getHeight() / 2);
        }

        public enum ShowMode {

            CLOSE_PREVIOUS,
            CLOSE_PREVIOUS_AND_RETURN_CONTROLLER,
            OPEN_ABOVE,
            OPEN_ABOVE_AND_RETURN_CONTROLLER

        }

    }

    public static class DictionaryBuilder {

        public Dialog<DictionaryItemModel> build() {
            Dialog<DictionaryItemModel> dictionaryDialog = new Dialog<>();
            dictionaryDialog.setTitle(ResourceUtil.getMessage(ResourceConst.DICTIONARY_TITLE_KEY.value()));

            DialogPane dialogPane = dictionaryDialog.getDialogPane();
            dictionaryDialog.setWidth(600);
            dictionaryDialog.setHeight(500);

            Stage actualStage = (Stage) Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);

            if (actualStage != null) {
                dictionaryDialog.initOwner(actualStage);
            }

            ButtonType selectButton = new ButtonType(ResourceUtil.getMessage(BUTTON_SELECT_KEY.value()), ButtonBar.ButtonData.APPLY);
            dialogPane.getButtonTypes().addAll(selectButton, ButtonType.CANCEL);
            dialogPane.getStylesheets().add(GLOBAL_COLORS_STYLES_PATCH);
            dialogPane.getStylesheets().add(DICTIONARY_STYLES_ADDRESS);

            TableView<DictionaryItemModel> table = new TableView<>();
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            TableColumn<DictionaryItemModel, Number> selectColumn = new TableColumn<>("");
            selectColumn.setMaxWidth(45.0);
            selectColumn.setMinWidth(45.0);
            selectColumn.setCellValueFactory(new PropertyValueFactory<>("select"));

            TableColumn<DictionaryItemModel, Number> firstNameCol = new TableColumn<>(ResourceUtil.getMessage(IDENTIFIER_COLUMN_KEY.value()));
            firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());

            TableColumn<DictionaryItemModel, String> lastNameCol = new TableColumn<>(ResourceUtil.getMessage(NAME_COLUMN_KEY.value()));
            lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

            table.getColumns().add(selectColumn);
            table.getColumns().add(firstNameCol);
            table.getColumns().add(lastNameCol);

            NodeUtils.setTableSelectOption(table, TableSelectionMode.MULTIPLE);
            NodeUtils.setTableViewPlaceHolder(table);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);

            Pagination pagination = new Pagination();
            hBox.getChildren().add(pagination);

            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(table);
            borderPane.setBottom(hBox);

            borderPane.setMinWidth(600);
            borderPane.setMinHeight(500);

            dialogPane.setContent(borderPane);

            dictionaryDialog.initModality(Modality.APPLICATION_MODAL);
            dictionaryDialog.initStyle(StageStyle.UTILITY);

            dictionaryDialog.setResultConverter(dialogButton -> {
                if (dialogButton == selectButton) {
                    return NodeUtils.getSelectedDictionaryItem(table);
                }
                return null;
            });

            return dictionaryDialog;

        }

    }


}
