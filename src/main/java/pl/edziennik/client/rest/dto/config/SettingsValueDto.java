package pl.edziennik.client.rest.dto.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettingsValueDto {

    private Long settingId;
    private Boolean booleanValue;
    private String stringValue;
    private Long longValue;

    public SettingsValueDto(Long settingId, boolean booleanValue) {
        this.settingId = settingId;
        this.booleanValue = booleanValue;
        this.longValue = null;
        this.stringValue = null;
    }

    public SettingsValueDto(Long settingId, String stringValue) {
        this.settingId = settingId;
        this.stringValue = stringValue;
        this.longValue = null;
        this.booleanValue = null;
    }

    public SettingsValueDto(Long settingId, Long longValue) {
        this.settingId = settingId;
        this.longValue = longValue;
        this.booleanValue = null;
        this.stringValue = null;
    }

    public static SettingsValueDto getSettingValueDto(ConfigurationDto dto){
        if (dto.getBooleanValue() != null){
            return new SettingsValueDto(dto.getSettingId(), dto.getBooleanValue());
        }
        if (dto.getStringValue() != null){
            return new SettingsValueDto(dto.getSettingId(), dto.getStringValue());
        }
        if (dto.getLongValue() != null){
            return new SettingsValueDto(dto.getSettingId(), dto.getLongValue());
        }
        return null;
    }

}
