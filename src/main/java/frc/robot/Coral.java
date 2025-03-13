package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;


public class Coral {
 private final CoralCommand coralsystem;
  

 public Coral (){
  coralsystem = new CoralCommand();

 }

 public void Coral_turn () {
  coralsystem.turning(0.7);
 
 }
 public void Coral_door () {
  coralsystem.opening(0.2);
    
  }
  
}


   
