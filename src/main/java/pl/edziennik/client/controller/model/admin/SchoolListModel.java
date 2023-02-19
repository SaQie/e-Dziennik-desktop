package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.pojo.SchoolPojo;

import java.util.List;

@Getter
public class SchoolListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty nip;
    private final SimpleStringProperty regon;
    private final SimpleStringProperty address;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty schoolLevel;
    private final CheckBox select;


    public SchoolListModel(SchoolPojo pojo) {
        this.name = new SimpleStringProperty(pojo.getName());
        this.postalCode = new SimpleStringProperty(pojo.getPostalCode());
        this.city = new SimpleStringProperty(pojo.getCity());
        this.nip = new SimpleStringProperty(pojo.getNip());
        this.regon = new SimpleStringProperty(pojo.getRegon());
        this.address = new SimpleStringProperty(pojo.getAddress());
        this.phoneNumber = new SimpleStringProperty(pojo.getPhoneNumber());
        this.schoolLevel = new SimpleStringProperty(pojo.getSchoolLevel().getName());
        this.id = new SimpleLongProperty(pojo.getId());
        this.select = Styles.tableViewSelectionCheckBox();
    }


    public static List<SchoolListModel> mapPojoToModel(List<SchoolPojo> pojos) {
        return pojos.stream()
                .map(SchoolListModel::new)
                .toList();
    }

    public static SchoolListModel mapPojoToModel(SchoolPojo pojo) {
        return new SchoolListModel(pojo);
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }

    @Override
    public Long getId() {
        return id.getValue();
    }
}
