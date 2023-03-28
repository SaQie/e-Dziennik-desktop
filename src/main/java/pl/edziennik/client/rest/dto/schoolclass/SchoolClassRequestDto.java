package pl.edziennik.client.rest.dto.schoolclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolClassRequestDto {

    private final String className;
    private final Long idClassTeacher;
    private final Long idSchool;

    public SchoolClassRequestDto(String className, Long idClassTeacher, Long idSchool) {
        this.className = className;
        this.idClassTeacher = idClassTeacher;
        this.idSchool = idSchool;
    }

    public SchoolClassRequestDto(String className, Long idSchool) {
        this.className = className;
        this.idSchool = idSchool;
        this.idClassTeacher = null;
    }
}
