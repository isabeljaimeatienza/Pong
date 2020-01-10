package es.isabeljaimeatienza.pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        StackPane root = new StackPane(); //lo guardo en una variable que he llamado root (el panel)
        var scene = new Scene(root, 640, 480);//Crea ventana de esa medida usando la variable root
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle(); //aquí voy a guardar una bola, con new me creo objeto circulo
        circleBall.setCenterX(10); // obligatoriamente debe de tener una medida, double permite decimales
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        //Circle circleBall2= new Circle(10, 30, 7); es otro modo de hacer la bola pero con menos líneas
        
        root.getChildren().add(circleBall);//los hijos hace referencia a las cosas que contiene el panel
        
    }

    public static void main(String[] args) {
        launch();
    }

}