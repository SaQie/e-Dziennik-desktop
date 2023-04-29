package pl.edziennik.client.rest.dto.grade;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto {

    private Long gradeId;
    private Integer grade;
    private Integer weight;
    private String description;
    private String teacherName;
    private Long teacherId;

    private LocalDate createdDate;


}
