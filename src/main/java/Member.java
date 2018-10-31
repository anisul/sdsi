import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AppUtil;

public class Member extends Application {
    private Parent rootNode;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        AppUtil.loadProperties();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        AppUtil.hazelcastInstance = hazelcastInstance;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sample.fxml"));
        rootNode = fxmlLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("SDSi");
        stage.setScene(new Scene(rootNode));
        stage.show();
    }
}
