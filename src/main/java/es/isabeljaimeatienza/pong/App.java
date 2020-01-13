package es.isabeljaimeatienza.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {
      int ballCenterX = 10; //poner variables globales debajo del class
      int ballCurrentSpeedX = 10; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
      int ballDirectionX = 1; //multiplicas velocidad por dirección
      
        int ballCenterY = 10; //poner variables globales debajo del class
      int ballCurrentSpeedY = 10; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
      int ballDirectionY = 1;
      
      
    @Override
    public void start(Stage stage) {
        
        
        //StackPane lo cambio por pane, porque sino apila (Recuerda guardar los imports en Fix import)
        Pane root = new Pane(); //lo guardo en una variable que he llamado root (el panel)
        var scene = new Scene(root, 640, 480);//Crea ventana de esa medida usando la variable root
        scene.setFill(Color.BLACK);//cuidado con las mayúsculas
        stage.setScene(scene);
        stage.show();
        
        //new Circle--> crear un objeto de la clase Circle
        Circle circleBall = new Circle(); //aquí voy a guardar una bola, con new me creo objeto circulo
        //llamando a métodos del objeto circleBall
        circleBall.setCenterX(10); // obligatoriamente debe de tener una medida, double permite decimales
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);//Cambiar el color de la bola
        
        //Circle circleBall2= new Circle(10, 30, 7); es otro modo de hacer la bola pero con menos líneas
        
        root.getChildren().add(circleBall);//los hijos hace referencia a las cosas que contiene el panel
        
        
         // Game loop usando Timeline
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX+=ballCurrentSpeedX * ballDirectionX;
                    if (ballCenterX >=640){
                        ballDirectionX = -1;
                        
                    }else if(ballCenterX <=0){
                        ballDirectionX = 1;
                      
                    }
                     circleBall.setCenterY(ballCenterY);
                    ballCenterY+=ballCurrentSpeedY * ballDirectionY;
                    if (ballCenterY >=480){
                        ballDirectionY = -1;
                        
                    }else if(ballCenterY <=0){
                        ballDirectionY = 1;
                    }
                }
                
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
   }


   public static void main(String[] args) {
       launch();
    }

}