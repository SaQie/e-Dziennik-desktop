package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.dto.student.StudentDto;

import java.util.List;

@Getter
public class StudentListModel implements TableViewSelection {

    private final SimpleLongProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty pesel;
    private final SimpleStringProperty parentPhoneNumber;
    private final SimpleStringProperty parentFirstName;
    private final SimpleStringProperty parentLastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty schoolClass;
    private final SimpleStringProperty school;
    private final SimpleStringProperty role;
    private final CheckBox select;

    public StudentListModel(StudentDto pojo) {
        this.id = new SimpleLongProperty(pojo.getId());
        this.username = new SimpleStringProperty(pojo.getUsername());
        this.firstName = new SimpleStringProperty(pojo.getFirstName());
        this.lastName = new SimpleStringProperty(pojo.getLastName());
        this.address = new SimpleStringProperty(pojo.getAddress());
        this.postalCode = new SimpleStringProperty(pojo.getPostalCode());
        this.city = new SimpleStringProperty(pojo.getCity());
        this.pesel = new SimpleStringProperty(pojo.getPesel());
        this.parentPhoneNumber = new SimpleStringProperty(pojo.getParentPhoneNumber());
        this.parentFirstName = new SimpleStringProperty(pojo.getParentFirstName());
        this.parentLastName = new SimpleStringProperty(pojo.getParentLastName());
        this.schoolClass = new SimpleStringProperty(pojo.getSchoolClass().getClassName());
        this.school = new SimpleStringProperty(pojo.getSchool().getName());
        this.role = new SimpleStringProperty(pojo.getRole());
        this.email = new SimpleStringProperty(pojo.getEmail());
        this.select = Styles.tableViewSelectionCheckBox();

    }

    public static List<StudentListModel> mapPojoToModel(List<StudentDto> pojos) {
        return pojos.stream()
                .map(StudentListModel::new)
                .toList();
    }

    public static StudentListModel mapPojoToModel(StudentDto pojo){
        return new StudentListModel(pojo);
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }

    @Override
    public Long getId() {
        return this.id.getValue();
    }

    @Override
    public boolean isSelected() {
        return this.select.isSelected();
    }
}
