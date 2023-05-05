package pl.edziennik.client.controller.teacher.grades.actions;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.factory.ProgressFactory;
import pl.edziennik.client.core.StageManager;
import pl.edziennik.client.core.contextmenu.ActionExecutor;
import pl.edziennik.client.core.toast.Toast;
import pl.edziennik.client.core.toast.ToastType;
import pl.edziennik.client.rest.dto.grade.GradeRequestDto;
import pl.edziennik.client.task.grade.SaveGradeTask;
import pl.edziennik.client.util.ResourceUtil;

public class AddGradeToStudentAction implements ActionExecutor {

    private final ProgressFactory progressFactory;

    public AddGradeToStudentAction() {
        this.progressFactory = ProgressFactory.getInstance();
    }

    @Override
    public void executeOnCurrentRow(Long studentId, Object... parameters) {
        Long subjectId = (Long) parameters[0];

        Dialog<GradeRequestDto> addGradeDialog = prepareAddGradeDialog();

        addGradeDialog.showAndWait().ifPresent(dto -> {
            progressFactory.createLittleProgressBar(new SaveGradeTask(studentId, subjectId, dto), (response) -> {
                Toast.show(ToastType.INFORMATION, ResourceConst.SUCCESS_DIALOG_CONTENT_MESSAGE_KEY.value());
            });
        });
    }

    private static Dialog<GradeRequestDto> prepareAddGradeDialog() {
        Dialog<GradeRequestDto> addGradeDialog = new Dialog<>();
        addGradeDialog.setTitle(ResourceUtil.getMessage(ResourceConst.ADD_GRADE_ACTION_NAME.value()));
        addGradeDialog.initModality(Modality.APPLICATION_MODAL);
        addGradeDialog.initOwner(StageManager.getMainStage());

        ButtonType saveButton = CommonStageBuilder.SAVE_BUTTON;
        ButtonType cancelButton = CommonStageBuilder.CANCEL_BUTTON;
        addGradeDialog.getDialogPane().getButtonTypes().setAll(saveButton, cancelButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ComboBox<Integer> gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll(1, 2, 3, 4, 5, 6);
        gradeComboBox.getSelectionModel().selectFirst();
        gridPane.addRow(0, new Label(ResourceUtil.getMessage(ResourceConst.GRADE_COLUMN_KEY.value())), gradeComboBox);

        TextArea komentarzTextArea = new TextArea();
        komentarzTextArea.setPrefRowCount(5);
        gridPane.addRow(2, new Label(ResourceUtil.getMessage(ResourceConst.COMMENT_TEXT.value())), komentarzTextArea);

        ComboBox<Integer> weightComboBox = new ComboBox<>();
        weightComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        weightComboBox.getSelectionModel().selectFirst();
        gridPane.addRow(1, new Label(ResourceUtil.getMessage(ResourceConst.WEIGHT_COLUMN_KEY.value())), weightComboBox);

        addGradeDialog.getDialogPane().setContent(gridPane);

        addGradeDialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButton) {
                return new GradeRequestDto(gradeComboBox.getValue(), weightComboBox.getValue(), komentarzTextArea.getText());
            }
            return null;
        });
        return addGradeDialog;
    }

    @Override
    public void execute(Object... parameters) {
        System.out.println("xxx");
        // nothing to do
    }

}
