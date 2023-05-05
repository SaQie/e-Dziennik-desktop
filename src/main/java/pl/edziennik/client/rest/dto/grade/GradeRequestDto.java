package pl.edziennik.client.rest.dto.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeRequestDto {

    private Integer grade;
    private Integer weight;
    private String description;

}
