package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;

public class CoralCommand {
  public static final SparkMax coralturnmotor = new SparkMax (12,MotorType.kBrushless);
  public static final SparkMax coraldoormotor = new SparkMax(13, MotorType.kBrushed);
  private DigitalInput toplimitswitch = new DigitalInput(2);
  private DigitalInput bottomlimitswitch = new DigitalInput(1);

  public CoralCommand (){}
 
  public void turning ( double maxspeed ){
   boolean cturnr = controllermap.controllers[controllermap.cturnrButton[0]].getRawButton(controllermap.cturnrButton[1]);  
   boolean cturnl = controllermap.controllers[controllermap.cturnlButton[0]].getRawButton(controllermap.cturnlButton[1]); 
  
   double speed = maxspeed;
   double speed1 = -0.1;
   if ( cturnr == true) {
     coralturnmotor.set(speed);
   }

   if ( cturnl == false && cturnr == true) {
     coralturnmotor.set(speed);
   }

   
   if ( cturnl == true && cturnr == false) {
     coralturnmotor.set(speed1);
   }
   if ( cturnl == true && cturnr == true) {
    coralturnmotor.set(0);
  }

   if ( cturnr == false) {
     coralturnmotor.set(0);
   }
  }
  
 public void opening (double maxspeed){
    boolean cdoor = controllermap.controllers[controllermap.cdoorButton[0]].getRawButton(controllermap.cdoorButton[1]);
    boolean isAtTop = toplimitswitch.get();
    boolean isAtBottom = bottomlimitswitch.get();

    double speed = maxspeed;
    // System.out.println(isAtBottom);
    if (cdoor == true) {
      if ( isAtBottom == true ){ 
        coraldoormotor.set(speed);
      }

      else {
        coraldoormotor.set(0);
      }
      if ( isAtTop == true ) {
        coraldoormotor.set(-speed);
    }
      else {
        coraldoormotor.set(-speed);
    }
    }

    else {
      if ( isAtTop == true) {
         coraldoormotor.set(speed);
      }
      else {
        coraldoormotor.set(-speed);
     //   coraldoormotor.set(0);

      }
    }

  }
  
 }

 