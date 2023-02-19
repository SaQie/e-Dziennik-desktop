package pl.edziennik.client.rest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTeacherPojo {

    private Long id;
    private String fullName;

}
