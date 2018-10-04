import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private ImageView imageView;
    private Image image0;
    private Image image1;
    private Image image2;
    private Image image3;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);

        primaryStage.setWidth(1000);
        primaryStage.setHeight(1000);
        primaryStage.setTitle("Laboratoire 6");
        primaryStage.setResizable(false);

        image0 = new Image("file:image0.jpg");
        image1 = new Image("file:image1.jpg");
        image2 = new Image("file:image2.jpg");
        image3 = new Image("file:image3.jpg");
        imageView = new ImageView(image0);
        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        creerSlider(borderPane,creerMenu(borderPane));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuItem creerMenu(BorderPane borderPane){
        Menu menuFichier = new Menu("Fichiers");
        Menu menuChanger = new Menu("Changer une image");
        MenuItem itemImage1 = new MenuItem("Image #1");
        MenuItem itemImage2 = new MenuItem("Image #2");
        MenuItem itemImage3 = new MenuItem("Image #3");
        menuFichier.getItems().addAll(menuChanger);
        menuChanger.getItems().addAll(itemImage1, itemImage2, itemImage3);
        Menu menuAction = new Menu("Actions");
        MenuItem itemReinitialiser = new MenuItem("Réinitialiser");
        menuAction.getItems().addAll(itemReinitialiser);
        ContextMenu contextMenu = new ContextMenu(menuFichier, menuAction);
        MenuBar menuBar = new MenuBar(menuFichier, menuAction);
        borderPane.setTop(menuBar);
        imageView.setOnContextMenuRequested(event -> contextMenu.show(imageView, event.getScreenX(), event.getScreenY()));

        itemImage1.setOnAction(event -> imageView.setImage(image1));
        itemImage2.setOnAction(event -> imageView.setImage(image2));
        itemImage3.setOnAction(event -> imageView.setImage(image3));

        return itemReinitialiser;
    }

    private void creerSlider(BorderPane borderPane, MenuItem menuItem){
        ColorAdjust imageColors = new ColorAdjust();
        imageView.setEffect(imageColors);

        Label labelLuminosite = new Label("Luminosité:");
        Slider sliderLuminosite = new Slider(-1, 1, 0);
        sliderLuminosite.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setBrightness((double)newValue));
        Label labelContraste = new Label("Contraste:");
        Slider sliderContraste = new Slider(-1, 1, 0);
        sliderContraste.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setContrast((double)newValue));
        Label labelTeinte = new Label("Teinte:");
        Slider sliderTeinte = new Slider(-1, 1, 0);
        sliderTeinte.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setHue((double)newValue));
        Label labelSaturation = new Label("Saturation:");
        Slider sliderSaturation = new Slider(-1, 1, 0);
        sliderSaturation.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setSaturation((double)newValue));
        VBox vBox = new VBox(labelLuminosite,sliderLuminosite,labelContraste,sliderContraste,labelTeinte,sliderTeinte,labelSaturation,sliderSaturation);
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(imageView,vBox);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(hBox);

        menuItem.setOnAction(event -> {
            sliderLuminosite.setValue(0);
            sliderContraste.setValue(0);
            sliderTeinte.setValue(0);
            sliderSaturation.setValue(0);
        });
    }
}
