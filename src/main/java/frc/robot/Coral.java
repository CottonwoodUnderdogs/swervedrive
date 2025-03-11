package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;


public class Coral {
    public static final SparkMax coralturnmotor = new SparkMax (12,MotorType.kBrushless)
   

    private SparkMax coraldoormotor;
    private XboxController controller;
    private final CoralSystem System;


public Coral ( SparkMax A, SparkMax B, XboxController C){

   coralturnmotor = A;
   coraldoormotor = B;
   controller = C;

}

   public void Coral_turn () {

      boolean cturnr = controllermap.controllers[controllermap.cturnrButton[0]].getRawButton(controllermap.cturnrButton[1]);  
      boolean cturnl = controllermap.controllers[controllermap.cturnlButton[0]].getRawButton(controllermap.cturnlButton[1]); 
 
      double speed = (cturnr ? 0.5 :0 ) - (cturnl ? -0.5 : 0);

      coralturnmotor.set(speed);

}
   public void Coral_door () {

      boolean cdoor = controllermap.controllers[controllermap.cdoorButton[0]].getRawButton(controllermap.cdoorButton[1]);
    
     if (cdoor ? ) {
      if (coraldoormotor.isAtBottom()) {
          coraldoormotor.set(0.5);
      } 

      else { 
         coraldoormotor.set(0);
      }
   }

     else {

      if (coraldoormotor.isAtTop()) {
         coraldoormotor.set(-0.5);
      }
      else {
         coraldoormotor.set(0);
      }
   }

  }
  
}


   
