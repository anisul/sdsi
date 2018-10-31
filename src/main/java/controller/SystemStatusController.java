package controller;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.Member;
import com.hazelcast.internal.json.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.AppUtil;

import java.util.Set;

public class SystemStatusController {
    @FXML
    private TextArea systemStatusTextarea;

    @FXML
    private void initialize(){
        Cluster cluster = AppUtil.hazelcastInstance.getCluster();
        Set<Member> setMembers  = cluster.getMembers();

        int i = 1;
        for (Member member : setMembers ) {
            systemStatusTextarea.appendText("Member [" + i +"]: " + member.getAddress() + " " + member.getUuid() + "\n");
            i++;
        }
    }
}
