import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import core.NodeFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listener.MessageListenerImpl;
import util.AppUtil;

import java.util.Date;

public class Member extends Application {
    private Parent rootNode;
    private static boolean isPublisher;

    public static void main(String[] args) {
        /*if (args[0].equals("1")) {
            isPublisher = true;
        }*/
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        AppUtil.loadProperties();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        AppUtil.hazelcastInstance = hazelcastInstance;

        /*if (isPublisher) {
            ITopic<Date> topic = hazelcastInstance.getTopic("BEGIN_SEARCH");
            topic.publish(new Date());
        } else {
            ITopic<Date> topic = hazelcastInstance.getTopic("BEGIN_SEARCH");
            topic.addMessageListener(new MessageListenerImpl());
            System.out.println("Subscribed to topic BEGIN_SEARCH");
        }*/


        NodeFactory nodeFactory = new NodeFactory();
        nodeFactory.initialize();
        nodeFactory.printAllNodesInConsole();
        //nodeFactory.printAllNodes();

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
