package pl.edziennik.client.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import pl.edziennik.client.common.PropertiesLoader;
import pl.edziennik.client.common.Role;
import pl.edziennik.client.controller.ProgressController;
import pl.edziennik.client.controller.admin.AdminController;
import pl.edziennik.client.eDziennikApplication;

import java.util.*;

import static pl.edziennik.client.common.ResourcesConstants.*;

public class AuthorizationUtils {

    /*
        VARIABLES
     */

    private static final String AUTHORIZATION_VIEW_TITLE = "e-Dziennik";
    private static final int WIDTH = 650;
    private static final int HEIGHT = 450;

    public static void readAuthorizationDataAndSaveLocally(HttpHeaders headers) {
        if (headers != null) {
            String token = Objects.requireNonNull(headers.get("Authorization")).get(0);
            String refreshToken = Objects.requireNonNull(headers.get("RefreshToken")).get(0);
            Map<String, String> dataFromJWT = getDataFromJWT(token);
            PropertiesLoader.writeProperty("token", token);
            PropertiesLoader.writeProperty("refreshToken", refreshToken);
            PropertiesLoader.writeProperty("name", dataFromJWT.get("name"));
            PropertiesLoader.writeProperty("id", dataFromJWT.get("id"));
            if (dataFromJWT.get("role") != null){
                PropertiesLoader.writeProperty("role", dataFromJWT.get("role"));
            }
        }
    }

    public static void clearAuthorizationData(){
        PropertiesLoader.clearProperties();
    }

    @SneakyThrows
    public static void loadAuthorizationPage(){
        Stage stage = new Stage();
        FXMLLoader loader = NodeUtils.getLoaderWithResources(eDziennikApplication.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
        stage.setTitle(AUTHORIZATION_VIEW_TITLE);
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @SneakyThrows
    public static void showCorrectSceneAfterLogin(Stage currentStage){
        String role = PropertiesLoader.readProperty("role");
        if (role == null){
            return;
        }
        if (role.equals(Role.ROLE_ADMIN.name())){
            FXMLLoader loader = NodeUtils.getLoaderWithResources(AuthorizationUtils.class.getResource(DASHBOARD_ADMIN_VIEW_ADDRESS));
            Scene scene = new Scene((AnchorPane) loader.load(), 1200, 800);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setMinWidth(1100);
            stage.requestFocus();
            stage.setScene(scene);
            stage.initOwner(currentStage);
            currentStage.close();
            stage.show();
            return;
        }
        if (role.equals(Role.ROLE_MODERATOR.name())){
            // moderator
            return;
        }
        if (role.equals(Role.ROLE_TEACHER.name())){

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
        if (jsonNode.has("roles")) {
            JsonNode roles = jsonNode.get("roles");
            jwtData.put("role", roles.get(0).asText());
        }
        return jwtData;
    }


}
