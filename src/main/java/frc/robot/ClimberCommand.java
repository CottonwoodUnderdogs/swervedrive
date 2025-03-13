package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;


public class ClimberCommand {
 private SparkMax up_down_motor = new SparkMax(11, MotorType.kBrushless);
 private SparkMax cling_motor = new SparkMax(10, MotorType.kBrushless);
 private DigitalInput limitswitch = new DigitalInput(3);

 public ClimberCommand(){}
   

 public void cling_climber ( double maxspeed) {
  boolean intakeclimber = controllermap.controllers[controllermap.intakeclimeberButton[0]].getRawButton(controllermap.intakeclimeberButton[1]);
  boolean letoffclimber = controllermap.controllers[controllermap.letoffclimberButton[0]].getRawButton(controllermap.letoffclimberButton[1]);
  boolean isAtLimit = limitswitch.get();
 
  double speed = maxspeed;

    
if ( isAtLimit == true){
  if ( intakeclimber == true && letoffclimber == false ) {
    cling_motor.set(speed);
  }

  if (intakeclimber == false && letoffclimber == true ) {
    cling_motor.set(-speed);
  }

  if (intakeclimber == true && letoffclimber == true ) {
    cling_motor.set(0);
  }

  if (intakeclimber == false && letoffclimber == false ) {
    cling_motor.set(0);
  }
}
else  {
  if ( intakeclimber == true && letoffclimber == false ) {
    cling_motor.set(0);
  }

  else { cling_motor.set(0);}

}
System.out.println(isAtLimit);
 
  }

 
 
 public void up_down_climber (double maxspeed){
  double upclimber =  controllermap.controllers[controllermap.upclimberButton[0]].getRawAxis(controllermap.upclimberButton[1]);
  double downclimber = controllermap.controllers[controllermap.downclimberButton[0]].getRawAxis(controllermap.downclimberButton[1]);

  double downspeed = downclimber * maxspeed;
  double upspeed = -upclimber * maxspeed;

  if (upclimber >0.1 && downspeed <0.1) {
    up_down_motor.set(upspeed);
  }

  if (downspeed >0.1 && upclimber <0.1 ) {
    up_down_motor.set(downspeed);
  }

  if (downspeed >0.1 && upclimber >0.1) {
    up_down_motor.set(0);
  }

  if (downspeed <0.1 && upclimber <0.1) {
    up_down_motor.set(0);
  }

  // System.out.println("up" + upclimber);
  // System.out.println("down" + downclimber);


    // up_down_motor.set(downspeed);
    // up_down_motor.set(upspeed);

 }
}

