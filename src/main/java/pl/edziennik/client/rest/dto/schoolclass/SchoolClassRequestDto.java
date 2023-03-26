package pl.edziennik.client.rest.dto.schoolclass;

public class SchoolClassRequestDto {

    private final String className;
    private final Long idSupervisingTeacher;
    private final Long idSchool;

    public SchoolClassRequestDto(String className, Long idSupervisingTeacher, Long idSchool) {
        this.className = className;
        this.idSupervisingTeacher = idSupervisingTeacher;
        this.idSchool = idSchool;
    }

    public SchoolClassRequestDto(String className, Long idSchool) {
        this.className = className;
        this.idSchool = idSchool;
        this.idSupervisingTeacher = null;
    }
}
