package pl.edziennik.client.rest.dto.config;

import lombok.*;

@Getter
@Setter
public class ConfigurationDto {

    private Long id;
    private String name;
    private Boolean booleanValue;
    private String stringValue;
    private Long longValue;

}
