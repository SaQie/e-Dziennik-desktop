package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.core.TableViewSelection;
import pl.edziennik.client.rest.dto.parent.ParentDto;

import java.util.List;

@Getter
public class ParentListModel implements TableViewSelection {


    private final SimpleLongProperty id;
    private final SimpleStringProperty username;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty city;
    private final SimpleStringProperty pesel;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty email;
    private final SimpleStringProperty role;
    private final SimpleStringProperty studentFullName;
    private final SimpleLongProperty idStudent;
    private final CheckBox select;

    public ParentListModel(ParentDto dto) {
        this.id = new SimpleLongProperty(dto.getId());
        this.username = new SimpleStringProperty(dto.getUsername());
        this.firstName = new SimpleStringProperty(dto.getFirstName());
        this.lastName = new SimpleStringProperty(dto.getLastName());
        this.address = new SimpleStringProperty(dto.getAddress());
        this.postalCode = new SimpleStringProperty(dto.getPostalCode());
        this.city = new SimpleStringProperty(dto.getCity());
        this.pesel = new SimpleStringProperty(dto.getPesel());
        this.role = new SimpleStringProperty(dto.getRole());
        this.email = new SimpleStringProperty(dto.getEmail());
        this.select = Styles.tableViewSelectionCheckBox();
        this.phoneNumber = new SimpleStringProperty(dto.getPhoneNumber());
        this.studentFullName = new SimpleStringProperty(dto.getStudent().getFullName());
        this.idStudent = new SimpleLongProperty(dto.getStudent().getId());

    }

    public static List<ParentListModel> mapToModel(List<ParentDto> parents) {
        return parents.stream().map(ParentListModel::new).toList();
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
