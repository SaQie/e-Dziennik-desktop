package pl.edziennik.client.controller.model.admin;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import pl.edziennik.client.rest.pojo.SchoolPojo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SchoolListModel {

    private SimpleStringProperty name;
    private SimpleStringProperty postalCode;
    private SimpleStringProperty city;
    private SimpleStringProperty nip;
    private SimpleStringProperty regon;
    private SimpleStringProperty address;
    private SimpleStringProperty phoneNumber;
    // TODO dorobic na backendzie endpointy dla schoolLevel - wtedy mozna to pobierac i dociagac dane w tasku od pobierania listy szkol


    public SchoolListModel(String name, String postalCode, String city, String nip, String regon, String address, String phoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.city = new SimpleStringProperty(city);
        this.nip = new SimpleStringProperty(nip);
        this.regon = new SimpleStringProperty(regon);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public static List<SchoolListModel> mapPojoToModel(List<SchoolPojo> pojos) {
        return pojos.stream()
                .map(pojo ->
                        new SchoolListModel(pojo.getName(), pojo.getPostalCode(), pojo.getCity(), pojo.getNip(),
                                pojo.getRegon(), pojo.getAddress(), pojo.getPhoneNumber()))
                .collect(Collectors.toList());
    }
}
