package pl.edziennik.client.rest.dto.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SettingsValueDto {

    private Long id;
    private Boolean booleanValue;
    private String stringValue;
    private Long longValue;

    public SettingsValueDto(Long id, boolean booleanValue) {
        this.id = id;
        this.booleanValue = booleanValue;
        this.longValue = null;
        this.stringValue = null;
    }

    public SettingsValueDto(Long id, String stringValue) {
        this.id = id;
        this.stringValue = stringValue;
        this.longValue = null;
        this.booleanValue = null;
    }

    public SettingsValueDto(Long id, Long longValue) {
        this.id = id;
        this.longValue = longValue;
        this.booleanValue = null;
        this.stringValue = null;
    }

}
