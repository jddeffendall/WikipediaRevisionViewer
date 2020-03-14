import domain.Edit;
import domain.WikiPage;
import exceptions.NetworkErrorException;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
import java.util.Date;

public class WikiRevisionViewerUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Wikipedia Revision Viewer");

        GridPane gridPane = new GridPane();

        Button searchButton = new Button("Search");
        searchButton.setPrefSize(100,30);

        Label inputLabel = new Label("Word: ");
        inputLabel.setPrefSize(100,30);
        inputLabel.setAlignment(Pos.CENTER);

        TextField userInput = new TextField();
        userInput.setPrefSize(100,30);

        Label errorLabel = new Label();
        Label redirectLabel = new Label();

        TableView<Edit> recentEditorsTable = new TableView<Edit>();
        recentEditorsTable.setPlaceholder(new Label("Waiting for input..."));

        TableColumn editorColumn = new TableColumn("Editor");
        editorColumn.setMinWidth(175);
        TableColumn timestampColumn = new TableColumn("Timestamp");
        timestampColumn.setMinWidth(175);

        editorColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, String>("user")
        );
        timestampColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, Date>("timestamp")
        );

        recentEditorsTable.getColumns().addAll(editorColumn, timestampColumn);

        TableView countedEditsTable = new TableView();
        countedEditsTable.setPlaceholder(new Label("Waiting for input..."));

        TableColumn editColumn = new TableColumn("Editor");
        TableColumn countColumn = new TableColumn("Edit Count");
        countedEditsTable.getColumns().addAll(editColumn, countColumn);

        ObservableList<Edit> recentEditors = FXCollections.observableArrayList();

        searchButton.setOnAction(value -> {
            JsonGetter jsonGetter = new JsonGetter();
            try {
                String input = userInput.getText();
                String userJsonString = jsonGetter.JsonStringGetter(userInput.getText());
                WikiPage userWiki = JsonStringParser.ParseJsonToObjects(userJsonString);

                if (userWiki.getRedirect() != null) {
                    redirectLabel.setText("Redirected from " + input + " to " + userWiki.getPageTitle());
                }

                for (Edit i : userWiki.getPageEditors()) {
                    recentEditors.add(i);
                }
                recentEditorsTable.setItems(recentEditors);

            } catch (IOException e) {
                errorLabel.setText("IO Exception");
            } catch (NetworkErrorException e) {
                errorLabel.setText("Network Error Exception");
            } catch (ParameterIsNotJsonStringException e) {
                errorLabel.setText("Parameter is not Json String");
            } catch (NoWikipediaPageForWordException e) {
                errorLabel.setText("No Wikipedia page for that word");
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(inputLabel, userInput, searchButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.TOP_CENTER);
        gridPane.add(hbox,0,0,1,1);

        gridPane.add(recentEditorsTable, 0,1,1,1);

        Scene mainScene = new Scene(gridPane,500,500);
        stage.setScene(mainScene);
        stage.show();
    }
}
