package pl.edziennik.client.controller.teacher.grades.actions;

import pl.edziennik.client.core.contextmenu.ActionExecutor;

public class AddGradeToStudentAction implements ActionExecutor {


    @Override
    public void executeOnCurrentRow(Long rowId, Object... parameters) {
        System.out.println("yyy");
    }

    @Override
    public void execute(Object... parameters) {
        System.out.println("xxx");
        // nothing to do
    }
}
