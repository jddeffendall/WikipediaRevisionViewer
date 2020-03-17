import domain.Edit;
import domain.WikiPage;
import exceptions.NetworkErrorException;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import utils.EditSorter;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class WikiRevisionViewerUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Wikipedia Revision Viewer");
        stage.setHeight(500);
        stage.setWidth(800);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Font titleFont = Font.font("Verdana", FontWeight.BOLD, 20);

        Label introLabel = new Label("Welcome to the Wikipedia Revision Viewer!");
        introLabel.setFont(titleFont);
        introLabel.setAlignment(Pos.CENTER);
        gridPane.add(introLabel, 0,0,10,1);

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
        editorColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, String>("user")
        );

        TableColumn timestampColumn = new TableColumn("Timestamp");
        timestampColumn.setMinWidth(175);
        timestampColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, Date>("timestamp")
        );

        recentEditorsTable.getColumns().addAll(editorColumn, timestampColumn);


        TableView countedEditsTable = new TableView();
        countedEditsTable.setPlaceholder(new Label("Waiting for input..."));

        TableColumn editColumn = new TableColumn("Editor");
        editColumn.setMinWidth(175);
        editColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, String>("user")
        );

        TableColumn countColumn = new TableColumn("Edit Count");
        countColumn.setMinWidth(175);
        countColumn.setCellValueFactory(
                new PropertyValueFactory<Edit, Integer>("editCount")
        );
        countColumn.setSortType(TableColumn.SortType.DESCENDING);

        countedEditsTable.getColumns().addAll(editColumn, countColumn);

        //Observable array lists that hold the data to be put into the tables
        ObservableList<Edit> recentEditors = FXCollections.observableArrayList();
        ObservableList<Edit> countedEditors = FXCollections.observableArrayList();

        searchButton.setOnAction(value -> {
            JsonGetter jsonGetter = new JsonGetter();
            try {
                //Clear the table of previous searches
                recentEditorsTable.getItems().clear();
                countedEditsTable.getItems().clear();

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

                List<Edit> countedEdits = EditSorter.getEditCounts(userWiki.getPageEditors());

                for (Edit e : countedEdits) {
                    e.increaeEditCount();
                    countedEditors.add(e);
                }
                countedEditsTable.setItems(countedEditors);

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

        HBox searchHBox = new HBox();
        searchHBox.getChildren().addAll(inputLabel, userInput, searchButton);
        searchHBox.setSpacing(10);
        searchHBox.setAlignment(Pos.CENTER);
        gridPane.add(searchHBox,0,1,1,1);

        gridPane.add(recentEditorsTable, 0,2,1,1);
        gridPane.add(countedEditsTable,2,2,1,1);

        gridPane.add(redirectLabel, 0,10,1,1);
        gridPane.add(errorLabel,0,11,1,1);

        Scene mainScene = new Scene(gridPane);
        stage.setScene(mainScene);
        stage.show();
    }
}
