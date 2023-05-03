package pl.edziennik.client.rest.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edziennik.client.rest.dto.subject.SubjectGradeDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentSpecificSubjectGradeDto {

    private Long studentId;
    private String fullName;

    private SubjectGradeDto subjectData;

}
