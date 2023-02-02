package pl.edziennik.client.rest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigurationPojo {

    private Long id;
    private String name;
    private boolean enabled;

}
