package pl.edziennik.client.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import pl.edziennik.client.common.builder.CommonStageBuilder;
import pl.edziennik.client.common.constants.ResourceConst;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.configuration.PropertiesLoader;

import static pl.edziennik.client.common.builder.CommonStageBuilder.StageBuilder.ShowMode.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static pl.edziennik.client.common.constants.ResourceConst.*;

public class AuthorizationUtils {

    /*
        VARIABLES
     */

    private static final String REFRESH_TOKEN_HEADER = "RefreshToken";
    private static final String AUTHORIZATION_TOKEN_HEADER = "Authorization";
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;

    public static void readAuthorizationDataAndSaveLocally(HttpHeaders headers) {
        if (headers != null) {
            String token = Objects.requireNonNull(headers.get(AUTHORIZATION_TOKEN_HEADER)).get(0);
            String refreshToken = Objects.requireNonNull(headers.get(REFRESH_TOKEN_HEADER)).get(0);
            Map<String, String> dataFromJWT = getDataFromJWT(token);
            PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_TOKEN_KEY.value(), token);
            PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_REFRESH_TOKEN_KEY.value(), refreshToken);
            PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_NAME_KEY.value(), dataFromJWT.get(ResourceConst.PROPERTIES_LOADER_NAME_KEY.value()));
            PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_ID_KEY.value(), dataFromJWT.get(ResourceConst.PROPERTIES_LOADER_ID_KEY.value()));
            PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_SUPER_ID_KEY.value(), dataFromJWT.get(PROPERTIES_LOADER_SUPER_ID_KEY.value()));
            if (dataFromJWT.get(ResourceConst.PROPERTIES_LOADER_ROLE_KEY.value()) != null) {
                PropertiesLoader.writeProperty(ResourceConst.PROPERTIES_LOADER_ROLE_KEY.value(), dataFromJWT.get(ResourceConst.PROPERTIES_LOADER_ROLE_KEY.value()));
            }
        }
    }

    public static void clearAuthorizationData() {
        PropertiesLoader.clearProperties();
    }

    @SneakyThrows
    public static void loadAuthorizationPage() {
        CommonStageBuilder.stageBuilder()
                .withView(ResourceConst.AUTHORIZATION_VIEW_ADDRESS.value())
                .withTitle(AUTHORIZATION_VIEW_TITLE_MESSAGE_KEY.value())
                .withHeight(HEIGHT)
                .withWidth(WIDTH)
                .withResizable(false)
                .withStyle(StageStyle.UTILITY)
                .withShowMode(CLOSE_PREVIOUS)
                .isMainStage(true)
                .build();
    }

    public static void showCorrectSceneAfterLogin(Stage currentStage) {
        String role = PropertiesLoader.readProperty(ResourceConst.PROPERTIES_LOADER_ROLE_KEY.value());

        CommonStageBuilder.StageBuilder builder = CommonStageBuilder.stageBuilder()
                .withWidth(1200)
                .withHeight(800)
                .withStyle(StageStyle.UTILITY)
                .withResizable(true)
                .withMinWidth(1100)
                .withFocusRequest(true)
                .withOwner(currentStage)
                .withShowMode(CLOSE_PREVIOUS)
                .isMainStage(true);

        if (role.equals(Role.ROLE_STUDENT.name())) {
            builder.withView(DASHBOARD_STUDENT_VIEW_ADDRESS.value())
                    .withTitle(ResourceConst.DASHBOARD_STUDENT_VIEW_TITLE.value())
                    .build();
            return;
        }
        if (role.equals(Role.ROLE_ADMIN.name())) {
            builder.withView(ResourceConst.DASHBOARD_ADMIN_VIEW_ADDRESS.value())
                    .withTitle(ResourceConst.DASHBOARD_ADMIN_VIEW_TITLE.value())
                    .build();
            return;

        }
        if (role.equals(Role.ROLE_PARENT.name())) {
            // parent
            return;
        }
        if (role.equals(Role.ROLE_TEACHER.name())) {
            builder.withView(ResourceConst.DASHBOARD_TEACHER_VIEW_ADDRESS.value())
                    .withTitle(ResourceConst.DASHBOARD_TEACHER_VIEW_TITLE.value())
                    .build();
        }
    }

    @SneakyThrows
    private static Map<String, String> getDataFromJWT(String rawToken) {
        Map<String, String> jwtData = new HashMap<>();
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String token = rawToken.substring(rawToken.indexOf(" ") + 1);
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JsonNode jsonNode = new ObjectMapper().readValue(payload, JsonNode.class);
        JsonNode id = jsonNode.get("id");
        jwtData.put("id", id.asText());
        JsonNode name = jsonNode.get("sub");
        jwtData.put("name", name.asText());
        JsonNode superId = jsonNode.get("superId");
        jwtData.put("superId", superId.asText());
        if (jsonNode.has("roles")) {
            JsonNode roles = jsonNode.get("roles");
            jwtData.put("role", roles.get(0).asText());
        }
        return jwtData;
    }


}
