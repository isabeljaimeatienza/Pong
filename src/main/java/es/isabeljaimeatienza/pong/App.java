package es.isabeljaimeatienza.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {//declaración de variables- tenemos que intentar tener las menos posibles para ahorrar memoria-
     
    final short SCENE_HEIGHT = 480; //poniendo final hacemos una constante-- mayúsculas para que directamente sepamos que son constantes
    final short SCENE_WIDTH = 640;
    
    
    short ballCenterX = 10; //poner variables globales debajo del class
    byte ballCurrentSpeedX = 10; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
     byte ballDirectionX = 1; //multiplicas velocidad por dirección
      
     short ballCenterY = 10; //poner variables globales debajo del class
     byte ballCurrentSpeedY = 10; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
     byte ballDirectionY = 1;
      
     short stickHeight = 50;
     short stickPosY = (short)((SCENE_HEIGHT- stickHeight)/2);
     byte stickCurrentSpeed = 10; 
     byte stickDirection = 0; 
     byte stickDirection0 = 0; 
     
     
     
    @Override
    public void start(Stage stage) {
        
    
        //StackPane lo cambio por pane, porque sino apila (Recuerda guardar los imports en Fix import)
        Pane root = new Pane(); //lo guardo en una variable que he llamado root (el panel)
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);//Crea ventana de esa medida usando la variable root
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
        
        //Creación de rectángulo
        
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(stickHeight);
        rectStick.setX(SCENE_WIDTH-40);
        rectStick.setY((SCENE_HEIGHT-stickHeight)/2);/* altura ventana menos altura rectangulo entre dos para centrar la pala*/
        rectStick.setFill(Color.WHITE);
        root.getChildren().add(rectStick);
       
        
        //reconocer teclas-detectarlas
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(final KeyEvent keyEvent){
                switch(keyEvent.getCode()){
                    case UP:
                        stickDirection = -1; 
                        break;
                    case DOWN:
                        stickDirection = 1; 
                        break;
                }
             
            }
        });
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
                      //Así haremos que rebote :)
                    }else if(ballCenterY <=0){
                        ballDirectionY = 1;
                    }
                    
                    /*Para hacer el movimiento de la pala va aquí, dentro de la animación! Esto es porque le daremos una vez
                    e irá en la dirección que queramos, es decir le doy hacia arriba y subirá hasta que no digamos que vaya
                    hacia abajo. */ 
                    rectStick.setY(stickPosY);
                    stickPosY += stickCurrentSpeed * stickDirection;
                    if (stickPosY <= 0) {
                        stickDirection = 0;
                        stickPosY = 0;
                    }else if (stickPosY >= SCENE_HEIGHT- stickHeight){
                        stickDirection = 0;
                        stickPosY = (short) (SCENE_HEIGHT - stickHeight);
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