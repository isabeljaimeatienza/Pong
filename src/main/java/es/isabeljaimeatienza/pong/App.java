package es.isabeljaimeatienza.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {//declaración de variables- tenemos que intentar tener las menos posibles para ahorrar memoria-
    
    int score = 0; 
    int highScore = 0; 
    final short SCENE_HEIGHT = 480; //poniendo final hacemos una constante-- mayúsculas para que directamente sepamos que son constantes
    final short SCENE_WIDTH = 640;
    final short TEXT_SIZE = 24; 
    
    short ballCenterX = 10; //poner variables globales debajo del class
    byte ballCurrentSpeedX = 10; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
     byte ballDirectionX = 1; //multiplicas velocidad por dirección
      
     short ballCenterY = 10; //poner variables globales debajo del class
     byte ballCurrentSpeedY = 2; //esto hará que cambiemos la posición cuando queramos, es decir, le damos la velocidad, lo que le vamos sumando o restando
                                  // dependiendo si quiero que vaya hacia atrás para que vaya a la izquierda o sumando y que vaya a la derecha
     byte ballDirectionY = 1;
      
     short stickHeight = 50;
     short stickPosY = (short)((SCENE_HEIGHT- stickHeight)/2);
     byte stickCurrentSpeed = 10; 
     byte stickDirection = 0; 

     
     
     
    @Override
    public void start(Stage stage) {
        
    
        //StackPane lo cambio por pane, porque sino apila (Recuerda guardar los imports en Fix import)
        Pane root = new Pane(); //lo guardo en una variable que he llamado root (el panel)
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);//Crea ventana de esa medida usando la variable root
        scene.setFill(Color.BLACK);//cuidado con las mayúsculas
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        //new Circle--> crear un objeto de la clase Circle
        Circle circleBall = new Circle(); //aquí voy a guardar una bola, con new me creo objeto circulo
        //llamando a métodos del objeto circleBall
        circleBall.setCenterX(10); // obligatoriamente debe de tener una medida, double permite decimales
        circleBall.setCenterY(30);
        circleBall.setRadius(7);//son métodos: nosequé. lo que sea
        circleBall.setFill(Color.WHITE);//Cambiar el color de la bola
        
        
        double r= circleBall.getRadius()*2; //me retorna un número
        
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
       
        // DIBUJAR RED
        //crear un objeto de la clase line
        for (int i=0; i<SCENE_HEIGHT; i+=30){
            Line line = new Line (SCENE_WIDTH/2, i, SCENE_WIDTH/2, i+10);
            line.setStroke (Color.WHITE);
            line.setStrokeWidth(4);
            root.getChildren().add (line);
        }
        
     //PUNTUACIONES
     
     // LAYOUTS PARA MOSTRAR PUNTUACIONES 
     // LAYOUT PRINCIPAL
     HBox paneScores = new HBox();
     paneScores.setTranslateY (20);
     paneScores.setMinWidth (SCENE_WIDTH);
     paneScores.setAlignment(Pos.CENTER);
     paneScores.setSpacing(100);
     root.getChildren().add(paneScores);
     
     //Layout para puntuación actual
     HBox paneCurrentScore = new HBox();
     paneCurrentScore.setSpacing(10);
     paneScores.getChildren().add (paneCurrentScore);
     
    //Layout para puntuación máxima
     HBox paneHighScore = new HBox();
     paneHighScore.setSpacing(10);
     paneScores.getChildren().add (paneHighScore);
     
    //Texto de etiqueta para la puntuación
    Text textTitleScore = new Text ("Score:");
    textTitleScore.setFont(Font.font(TEXT_SIZE));
    textTitleScore.setFill(Color.WHITE);
    
    //Texto para la puntuación
     Text textScore = new Text ("0");
    textScore.setFont(Font.font(TEXT_SIZE));
    textScore.setFill(Color.WHITE);
    
    
    //Texto de etiqueta para la puntuación máxima
    Text textTitleHighScore = new Text ("Max.Score:");
    textTitleHighScore.setFont(Font.font(TEXT_SIZE));
    textTitleHighScore.setFill(Color.WHITE);
    
    // Texto para la puntuación máxima
    //Texto de etiqueta para la puntuación máxima
    Text textHighScore = new Text ("0");
    textHighScore.setFont(Font.font(TEXT_SIZE));
    textHighScore.setFill(Color.WHITE);
    
    //Añadir los textos a los layouts reservados para ellos
    paneCurrentScore.getChildren().add(textTitleScore);
    paneCurrentScore.getChildren().add(textScore);
    paneHighScore.getChildren().add(textTitleHighScore);
    paneHighScore.getChildren().add(textHighScore);
    
    
      
        
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
                    if (ballCenterX >=SCENE_WIDTH){
                        //ballDirectionX = -3;
                       if (score > highScore) {
                           highScore = score;
                           textHighScore.setText(String.valueOf(highScore));
                        }
                       
                      //Reiniciar partida
                       score = 0;
                       textScore.setText(String.valueOf(score));
                       ballCenterX = 6;
                       ballCurrentSpeedY = 5;
                    
                 
         
                        
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
                    Shape shapeCollision = Shape.intersect(circleBall, rectStick);  /* es diferente un objeto de una clase. Un objeto es una copia,
                                                                la clase es un molde. al usar static puedo poner el nombre de la clase, no necesito poner un objeto.
                                                                Así nos ahorramos tener que hacer un objeto para esto. 
                                                                */
                    boolean collisionVacia= shapeCollision.getBoundsInLocal().isEmpty ();/*no damos parámetros nos devuelve un booleano (true o false) si colisiona es falso (porque quiere decir que si no esta vacio has chocado)*/
                    if (collisionVacia == false && ballDirectionX > 0){
                        ballDirectionX = -3;
                        //Incrementar puntuación actual
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                }
                   
                
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
   }
    


   public static void main(String[] args) {
       launch();
        /* si un metodo pone delante algo, es el resultado que nos va a dar. Un ejemplo si pone void no devuelve nada
                    El setRadius por ejemplo, entre paréntesis los parámetros que hay que darles
       
      - Si no tiene static tenemos que poner un objeto, el circulo, pero no el molde, no la clase. 
       void (vacío) es lo que nos retorna, en este caso nada */
    }

}