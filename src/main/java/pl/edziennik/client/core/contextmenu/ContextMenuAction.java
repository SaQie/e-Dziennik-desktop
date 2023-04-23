package pl.edziennik.client.core.contextmenu;

import javafx.scene.image.ImageView;
import pl.edziennik.client.eDziennikApplication;
import pl.edziennik.client.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for context menu actions
 */
public class ContextMenuAction {

    private final String actionName;
    private final ActionExecutor actionExecutor;
    private final List<Object> parameters = new ArrayList<>();
    private final ImageView icon;

    public ContextMenuAction(String actionNameKey, ActionExecutor actionExecutor) {
        this.actionName = ResourceUtil.getMessage(actionNameKey);
        this.actionExecutor = actionExecutor;
        this.icon = null;
    }

    public ContextMenuAction(String actionName, ActionExecutor actionExecutor, String iconAddress) {
        this.actionName = actionName;
        this.actionExecutor = actionExecutor;
        ImageView imageView = new ImageView(eDziennikApplication.class.getResource(iconAddress).toExternalForm());
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        this.icon = imageView;
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

    /**
     * Returns translated action name
     *
     * @return
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Returns action icon
     *
     * @return
     */
    protected ImageView getIcon() {
        return this.icon;
    }

    /**
     * Execute action
     *
     * @param id
     */
    public void execute(Long id) {
        if (id == null) {
            actionExecutor.execute(parameters.toArray());
        }
        actionExecutor.executeOnCurrentRow(id, parameters.toArray());
    }


}
