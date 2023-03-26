package javaFxTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainsceneController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private void handleBtn1() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Queue1");
        mainPane.setCenter(view);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleBtn2() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Queue2");
        mainPane.setCenter(view);
    }

    public void handleBtn3() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Queue3");
        mainPane.setCenter(view);
    }

    public void handleBtn4() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Queue4");
        mainPane.setCenter(view);
    }

    public void handleBtn5() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Queue5");
        mainPane.setCenter(view);
    }

    public void handleBtn6() {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("WaitingQueue");
        mainPane.setCenter(view);
    }
}
