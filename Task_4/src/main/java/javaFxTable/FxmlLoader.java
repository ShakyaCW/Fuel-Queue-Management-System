package javaFxTable;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class FxmlLoader {

    private Pane view;

    public Pane getPage(String fileName) {

        try {
            URL fileUrl = Main.class.getResource("/javaFxTable/"+fileName+".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found.");
            }
            view = new FXMLLoader().load(fileUrl);


        }
        catch (Exception e) {
            System.out.println("No page");
        }
        return view;
    }
}
