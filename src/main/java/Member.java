import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import core.NodeFactory;
import core.SubscriberFactory;
import enums.TopicType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import types.HzAppSetting;
import util.AppUtil;

import java.util.Date;

public class Member extends Application {
    private Parent rootNode;
    private static boolean isPublisher;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        AppUtil.hazelcastInstance = hazelcastInstance;
        AppUtil.loadPropertiesFromHzMap();

        SubscriberFactory subscriberFactory = new SubscriberFactory();
        subscriberFactory.initToSubscribeAllTopic();

        NodeFactory nodeFactory = new NodeFactory();
        nodeFactory.initialize();
        //nodeFactory.printAllNodesInConsole();

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
