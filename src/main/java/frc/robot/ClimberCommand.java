package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;



public class ClimberCommand {
 public static final SparkMax up_down_motor = new SparkMax(11, MotorType.kBrushless);
 public static final SparkMax cling_motor = new SparkMax(10, MotorType.kBrushless);

 public ClimberCommand(){}

 public void up_down_climber (double maxspeed){
   double upclimber =  controllermap.controllers[controllermap.upclimberButton[0]].getRawAxis(controllermap.upclimberButton[1]);
  double downclimber = controllermap.controllers[controllermap.downclimberButton[0]].getRawAxis(controllermap.downclimberButton[1]);

  double downspeed = downclimber * -maxspeed;
  double upspeed = upclimber * maxspeed;

    up_down_motor.set(downspeed);
    up_down_motor.set(upspeed);

    // up_down_motor.set(downspeed);
System.out.println(upclimber);
}

 public void cling_climmber ( double maxspeed) {
    
 }
}