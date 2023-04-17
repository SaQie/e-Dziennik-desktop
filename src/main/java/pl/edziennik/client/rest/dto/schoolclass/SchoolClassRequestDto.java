package pl.edziennik.client.rest.dto.schoolclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolClassRequestDto {

    private final String className;
    private final Long classTeacherId;
    private final Long schoolId;

    public SchoolClassRequestDto(String className, Long idClassTeacher, Long schoolId) {
        this.className = className;
        this.classTeacherId = idClassTeacher;
        this.schoolId = schoolId;
    }

    public SchoolClassRequestDto(String className, Long schoolId) {
        this.className = className;
        this.schoolId = schoolId;
        this.classTeacherId = null;
    }
}
