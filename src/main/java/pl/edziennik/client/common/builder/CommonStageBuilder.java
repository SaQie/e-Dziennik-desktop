package pl.edziennik.client.common.builder;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import lombok.SneakyThrows;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.utils.NodeUtils;

public class CommonStageBuilder {

    private CommonStageBuilder() {
    }

    public static StageBuilder builder(){
        return new StageBuilder();
    }

    public static class StageBuilder{

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

        private boolean isFocusRequest;
        private boolean isAlwaysOnTop;
        private boolean isResizable;
        private boolean isSearchActualStage;
        private boolean isSetPositionCenter;


        public StageBuilder withSetPositionToCenter(boolean isSetPositionCenter){
            this.isSetPositionCenter = isSetPositionCenter;
            return this;
        }

        public StageBuilder withFillColor(Color color){
            this.color = color;
            return this;
        }

        public StageBuilder withSearchActualStage(boolean isSearchActualStage){
            this.isSearchActualStage = isSearchActualStage;
            return this;
        }

        public StageBuilder withShowMode(ShowMode showMode){
            this.showMode = showMode;
            return this;
        }

        public StageBuilder withView(String viewAddress){
            this.viewAddress = viewAddress;
            return this;
        }

        public StageBuilder withAlwaysOnTop(boolean isAlwaysOnTop){
            this.isAlwaysOnTop = isAlwaysOnTop;
            return this;
        }

        public StageBuilder withFocusRequest(boolean isFocusRequest){
            this.isFocusRequest = isFocusRequest;
            return this;
        }

        public StageBuilder withOwner(Stage owner){
            this.owner = owner;
            return this;
        }

        public StageBuilder withTitle(String title){
            this.title = title;
            return this;
        }

        public StageBuilder withModality(Modality stageModality){
            this.stageModality = stageModality;
            return this;
        }

        public StageBuilder withStyle(StageStyle stageStyle){
            this.stageStyle = stageStyle;
            return this;
        }

        public StageBuilder withResizable(boolean isResizable){
            this.isResizable = isResizable;
            return this;
        }

        public StageBuilder withWidth(int width){
            this.width = width;
            return this;
        }

        public StageBuilder withHeight(int height){
            this.height = height;
            return this;
        }

        public StageBuilder withMaxWidth(int maxWidth){
            this.maxWidth = maxWidth;
            return this;
        }

        public StageBuilder withMinWidth(int minWidth){
            this.minWidth = minWidth;
            return this;
        }

        public StageBuilder withMaxHeight(int maxHeight){
            this.maxHeight = maxHeight;
            return this;
        }

        public StageBuilder withMinHeight(int minHeight){
            this.minHeight = minHeight;
            return this;
        }

        @SneakyThrows
        public <T> T build(){
            FXMLLoader fxmlLoader = NodeUtils.getLoaderWithResources(eDziennikApplication.class.getResource(viewAddress));
            Scene scene = new Scene(fxmlLoader.load(), width,height);
            T controller = fxmlLoader.getController();
            Stage stage = new Stage();

            if (stageStyle != null){
                stage.initStyle(stageStyle);
            }
            if (minWidth != 0){
                stage.setMinWidth(minWidth);
            }
            if (maxWidth != 0){
                stage.setMaxWidth(maxWidth);
            }
            if (maxHeight != 0){
                stage.setMaxHeight(maxHeight);
            }
            if (minHeight != 0){
                stage.setMinHeight(minHeight);
            }
            if (title != null){
                stage.setTitle(title);
            }
            if (stageModality != null){
                stage.initModality(stageModality);
            }
            if (color != null){
                scene.setFill(color);
            }
            if (owner != null){
                stage.initOwner(owner);
            }
            if (isFocusRequest){
                stage.requestFocus();
            }
            if (isSearchActualStage){
                searchActualStageAndSetPosition(scene, stage);
            }
            if (isSetPositionCenter){
                if (owner != null) {
                    setPositionToCenter(scene, stage, owner);
                }
            }

            stage.setResizable(isResizable);
            stage.setAlwaysOnTop(isAlwaysOnTop);

            stage.setScene(scene);

            switch (showMode){
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

        public enum ShowMode{

            CLOSE_PREVIOUS,
            CLOSE_PREVIOUS_AND_RETURN_CONTROLLER,
            OPEN_ABOVE,
            OPEN_ABOVE_AND_RETURN_CONTROLLER

        }

    }


}
