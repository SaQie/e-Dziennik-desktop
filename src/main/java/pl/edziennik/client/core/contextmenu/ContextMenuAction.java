package pl.edziennik.client.core.contextmenu;

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

    public ContextMenuAction(String actionNameKey, ActionExecutor actionExecutor) {
        this.actionName = ResourceUtil.getMessage(actionNameKey);
        this.actionExecutor = actionExecutor;
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
