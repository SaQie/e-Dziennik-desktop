package pl.edziennik.client.rest.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edziennik.client.rest.dto.subject.SubjectGradeDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeDto {

    private Long studentId;
    private String firstName;
    private String lastName;

    private List<SubjectGradeDto> subjects;

}
