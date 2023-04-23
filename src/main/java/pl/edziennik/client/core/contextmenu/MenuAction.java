package pl.edziennik.client.core.contextmenu;

import pl.edziennik.client.common.factory.DialogFactory;
import pl.edziennik.client.common.factory.ProgressFactory;


public abstract class MenuAction {

    protected final ProgressFactory progressFactory = ProgressFactory.getInstance();
    protected final DialogFactory dialogFactory = DialogFactory.getInstance();

}
