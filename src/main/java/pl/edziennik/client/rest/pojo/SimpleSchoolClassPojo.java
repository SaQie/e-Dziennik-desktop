package pl.edziennik.client.rest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSchoolClassPojo {

    private Long id;
    private String className;

}
