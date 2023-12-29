
import java.util.*;

class PongGame  extends App {
    void fireBall( Vector3 color, Vector2 velocity, Vector2 position){
        Ball ball = new Ball(); 
        ball.position = position; 
        ball.velocity = new Vector2(-2.0, 0); 
        ball.radius = 2.0;
        ball.color = color;
        
    }

boolean BallPaddleCollision(Vector2 position1, Vector2 position2, double radius1, double radius2 ){
        if (Vector2.distanceBetween( position1, position2) < radius1 + radius2){
            return true;
        }
        return false;
}

static class Ball {
    Vector3 color;
    double radius;
    Vector2 position;
    Vector2 velocity;
}


static class Paddle {
    Vector3 color;
    Vector2 position;
    double radius;
    int score;
    Vector2 velocity;
}


Paddle[] paddles;
int newScore;

Ball ball;

void setup() { 
    newScore = 0;
    ball = new Ball();
    ball.color = new Vector3(Vector3.blue);
    ball.radius = 20.0;
    ball.position = new Vector2(0.0, 0.0);
    ball.velocity = new Vector2(-1,0);
    
    
    paddles = new Paddle[2];
    for (int i = 0; i < paddles.length; ++i){
        paddles[i] = new Paddle();
        paddles[i].color = Vector3.green;
       paddles[i].radius = 45.0;
        paddles[i].velocity = new Vector2(0,0);
        
    }
    paddles[0].position = new Vector2(300, 0);
    paddles[1].position = new Vector2(-300, 0);
    
}


void loop() { 
    
      
        Paddle paddle1 = paddles[0];
        Paddle paddle2 = paddles[1];
    
    if (keyHeld('W')) { paddle1.position.y += 1.0; }
    if (keyHeld('S')) { paddle1.position.y -= 1.0; }
    if (keyHeld('O')) { paddle2.position.y += 1.0; }
    if (keyHeld('F')) { paddle2.position.y -= 1.0; }
    
    for (int i = 0; i < paddles.length; ++i){
        Paddle paddle = paddles[i];
        
    
    
    drawCircle(ball.position, ball.radius, ball.color);
    drawCircle(paddle.position, paddle.radius, paddle.color);


            Vector2 velocity1 = Vector2.directionVectorFrom(ball.position, ball.position); 
            fireBall(ball.color, velocity1, ball.position);              
        
    
    
     if (BallPaddleCollision(ball.position, paddle.position, ball.radius, paddle.radius )){
         newScore++;
         ball.velocity = ball.velocity.times(-1);
        ball.velocity = Vector2.directionVectorFrom(paddle.position, ball.position);
     }
     if ((ball.position.x >720) || (ball.position.x < -720)){
            ball.position = new Vector2(0.0, 0.0);
             ball.velocity = new Vector2(-1,0);
             newScore--;
            
            
     }
     if ((ball.position.y > 600) || (ball.position.y< -600)){
         ball.position = new Vector2(0.0, 0.0);
         ball.velocity = new Vector2(-1,0);
         newScore--;
     }
     
     
     drawString("User Score:" + newScore, new Vector2(0.0, 200.0), Vector3.red, 60,  true);
         

         
         

            
    
    ball.position = ball.position.plus(ball.velocity);
    drawCircle(ball.position, ball.radius, ball.color);

}
}

public static void main(String[] arguments) {
    App app = new PongGame();
    app.setWindowBackgroundColor(0.0, 0.0, 0.0);
    app.setWindowSizeInWorldUnits(700.0, 700.0);
    app.setWindowCenterInWorldUnits(0.0, 0.0);
    app.setWindowHeightInPixels(600);
    app.setWindowTopLeftCornerInPixels(200, 200);
    app.run();
}

}
