package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import lombok.Getter;
import pl.edziennik.client.common.Styles;
import pl.edziennik.client.rest.pojo.SchoolPojo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SchoolListModel implements TableViewSelection{

    private SimpleStringProperty name;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty city;
    private SimpleStringProperty nip;
    private SimpleStringProperty regon;
    private SimpleStringProperty address;
    private SimpleStringProperty phoneNumber;
    private SimpleLongProperty idSchoolLevel;
    private CheckBox select;



    public SchoolListModel(String name, String postalCode, String city, String nip, String regon, String address, String phoneNumber, Long idSchoolLevel) {
        this.name = new SimpleStringProperty(name);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
        this.nip = new SimpleStringProperty(nip);
        this.regon = new SimpleStringProperty(regon);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.idSchoolLevel = new SimpleLongProperty(idSchoolLevel);
        this.select = Styles.tableViewSelectionCheckBox();
    }

    public static List<SchoolListModel> mapPojoToModel(List<SchoolPojo> pojos) {
        return pojos.stream()
                .map(pojo ->
                        new SchoolListModel(pojo.getName(), pojo.getPostalCode(), pojo.getCity(), pojo.getNip(),
                                pojo.getRegon(), pojo.getAddress(), pojo.getPhoneNumber(), pojo.getIdSchoolLevel()))
                .collect(Collectors.toList());
    }

    public static SchoolListModel mapPojoToModel(SchoolPojo pojo){
        return new SchoolListModel(pojo.getName(), pojo.getPostalCode(),
                pojo.getCity(), pojo.getNip(), pojo.getRegon(), pojo.getAddress(),
                pojo.getPhoneNumber(), pojo.getIdSchoolLevel());
    }

    @Override
    public void setSelection() {
        this.select.setSelected(!select.isSelected());
    }
}
