package pl.edziennik.client.rest.pojo;

import lombok.Data;

@Data
public class SchoolPojo {

    private Long id;
    private String name;
    private String postalCode;
    private String city;
    private String nip;
    private String regon;
    private String address;
    private String phoneNumber;
    private Long idSchoolLevel;

}
