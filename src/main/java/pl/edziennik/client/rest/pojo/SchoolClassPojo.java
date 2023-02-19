package pl.edziennik.client.rest.pojo;

import lombok.Data;

@Data
public class SchoolClassPojo {

    private Long id;
    private String className;
    private SimpleTeacherPojo supervisingTeacher;
    private SimpleSchoolPojo school;

}
