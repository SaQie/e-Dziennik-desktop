package pl.edziennik.client.core.contextmenu;

public interface CurrentRowActionExecutor {

    void executeOnCurrentRow(Long rowId,Object... parameters);

}
