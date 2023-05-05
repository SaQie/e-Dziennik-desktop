package pl.edziennik.client.core.contextmenu;

import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for context menu actions
 */
public class ContextMenuAction {

    private final String actionName;
    private final ActionExecutor actionExecutor;
    private final List<Object> parameters = new ArrayList<>();
    private final Image icon;
    private final ContextMenuActionExecutorMode mode;
    private Button refreshButton;
    private boolean assignToMenuButton;

    public ContextMenuAction(String actionNameKey, ActionExecutor actionExecutor, ContextMenuActionExecutorMode mode) {
        this.actionName = ResourceUtil.getMessage(actionNameKey);
        this.actionExecutor = actionExecutor;
        this.icon = null;
        this.mode = mode;
        this.refreshButton = null;
    }

    public ContextMenuAction(String actionName, ActionExecutor actionExecutor, String iconAddress, ContextMenuActionExecutorMode mode) {
        this.actionName = ResourceUtil.getMessage(actionName);
        this.actionExecutor = actionExecutor;
        this.icon = new Image(eDziennikApplication.class.getResource(iconAddress).toExternalForm());
        this.mode = mode;
        this.refreshButton = null;
    }

    /**
     * Set default parameters for action
     *
     * @param parameters
     * @return
     */
    public ContextMenuAction setParameters(Object... parameters) {
        this.parameters.addAll(List.of(parameters));
        return this;
    }

    public ContextMenuAction setRefreshSceneAfterExecuteUsingButton(Button refreshButton) {
        this.refreshButton = refreshButton;
        return this;
    }

    public ContextMenuAction assignToMenuButton(boolean assignToMenuButton) {
        this.assignToMenuButton = assignToMenuButton;
        return this;
    }

    /**
     * Returns translated action name
     *
     * @return
     */
    public String getActionName() {
        return actionName;
    }

    public boolean isAssignToMenuButton() {
        return assignToMenuButton;
    }

    public ContextMenuActionExecutorMode getExecutorMode() {
        return mode;
    }

    public Button getRefreshButton() {
        return refreshButton;
    }

    /**
     * Returns action icon
     *
     * @return
     */
    protected Image getIcon() {
        return this.icon;
    }

    /**
     * Execute action
     */
    public void execute() {
        actionExecutor.execute(parameters.toArray());

    }

    /**
     * Execute action on current selected row
     */
    public void executeOnCurrentRow(Long id) {
        actionExecutor.executeOnCurrentRow(id, parameters.toArray());
    }


}
