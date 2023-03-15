package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.dto.teacher.TeacherDto;

import java.util.List;

@Getter
public class TeacherListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleLongProperty userId;

    private final SimpleStringProperty username;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty address;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty pesel;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty role;
    private final SimpleStringProperty school;
    private final SimpleStringProperty email;
    private final CheckBox select;

    public TeacherListModel(TeacherDto pojo) {
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
        this.school = new SimpleStringProperty(pojo.getSchool().getName());
        this.email = new SimpleStringProperty(pojo.getEmail());
        this.userId = new SimpleLongProperty(pojo.getUserId());
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<TeacherListModel> mapPojoToModel(List<TeacherDto> pojos) {
        return pojos.stream()
                .map(TeacherListModel::new)
                .toList();
    }

    public static TeacherListModel mapPojoToModel(TeacherDto pojo) {
        return new TeacherListModel(pojo);
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
    public Long getUserId() {
        return this.userId.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }
}
