import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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

        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
        primaryStage.setTitle("Laboratoire 6");
        primaryStage.setResizable(false);

        creerMenu(borderPane);
        image0 = new Image("file:chat.jpg");
        image1 = new Image("file:chat.jpg");
        image2 = new Image("file:chat.jpg");
        image3 = new Image("file:chat.jpg");
        imageView = new ImageView(image0);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void creerMenu(BorderPane borderPane){
        Menu menuFichier = new Menu("Fichier");
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
        borderPane.getChildren().add(borderPane);
        imageView.setOnContextMenuRequested(event -> contextMenu.show(imageView, event.getScreenX(), event.getScreenY()));

        itemImage1.setOnAction(event -> imageView.setImage(image1));
        itemImage2.setOnAction(event -> imageView.setImage(image2));
        itemImage3.setOnAction(event -> imageView.setImage(image3));
    }

    private void creerSlider(BorderPane borderPane){
        ColorAdjust imageColors = new ColorAdjust();
        imageView.setEffect(imageColors);

        Label labelLuminosite = new Label("Volume:");
        Slider sliderLuminosite = new Slider(-1, 1, 0);
        sliderLuminosite.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setBrightness((double)newValue));
        borderPane.getChildren().add(labelLuminosite);
        borderPane.getChildren().add(sliderLuminosite);
        Label labelContraste = new Label("Volume:");
        Slider sliderContraste = new Slider(-1, 1, 0);
        borderPane.getChildren().add(labelContraste);
        borderPane.getChildren().add(sliderContraste);
        sliderContraste.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setContrast((double)newValue));
        Label labelTeinte = new Label("Volume:");
        Slider sliderTeinte = new Slider(-1, 1, 0);
        sliderTeinte.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setHue((double)newValue));
        borderPane.getChildren().add(labelTeinte);
        borderPane.getChildren().add(sliderTeinte);
        Label labelSaturation = new Label("Volume:");
        Slider sliderSaturation = new Slider(-1, 1, 0);
        sliderSaturation.valueProperty().addListener((observable, oldValue, newValue) -> imageColors.setSaturation((double)newValue));
        borderPane.getChildren().add(labelSaturation);
        borderPane.getChildren().add(sliderSaturation);
    }
}
