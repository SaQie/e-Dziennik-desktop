package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.pojo.TeacherPojo;

import java.util.List;

@Getter
public class TeacherListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty address;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty pesel;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty role;
    private final SimpleLongProperty idSchool;
    private final CheckBox select;

    public TeacherListModel(TeacherPojo pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.username = new SimpleStringProperty(pojo.getUsername());
        this.firstName = new SimpleStringProperty(pojo.getFirstName());
        this.lastname = new SimpleStringProperty(pojo.getLastName());
        this.address = new SimpleStringProperty(pojo.getAddress());
        this.postalCode = new SimpleStringProperty(pojo.getPostalCode());
        this.city = new SimpleStringProperty(pojo.getCity());
        this.pesel = new SimpleStringProperty(pojo.getPesel());
        this.phoneNumber = new SimpleStringProperty(pojo.getPhoneNumber());
        this.role = new SimpleStringProperty(pojo.getRole());
        this.idSchool = new SimpleLongProperty(pojo.getIdSchool());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<TeacherListModel> mapPojoToModel(List<TeacherPojo> pojos) {
        return pojos.stream()
                .map(TeacherListModel::new)
                .toList();
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public Long getId() {
        return id.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }
}
