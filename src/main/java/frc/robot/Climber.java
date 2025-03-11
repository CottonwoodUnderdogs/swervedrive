package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

public class Climber {
 
  private final ClimberCommand climbersystem;

 public Climber(){
  climbersystem = new ClimberCommand();
}

 public void Climber_up_down (){

  climbersystem.up_down_climber(0.5);

}

   public void Climber_cling () {
    
    climbersystem.cling_climber(0.5);

}

}
