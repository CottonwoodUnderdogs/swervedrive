package frc.robot;

import com.revrobotics.spark.SparkMax;

public class Climber {
    private SparkMax up_down_motor;
    private SparkMax cling_motor;

public Climber( SparkMax A, SparkMax B){
    
    up_down_motor = A;
    cling_motor = B;

}

   public void Climber_up_down (){
     double upclimber =  controllermap.controllers[controllermap.upclimberButton[0]].getRawAxis(controllermap.upclimberButton[1]);
     double downclimeber = controllermap.controllers[controllermap.downclimberButton[0]].getRawAxis(controllermap.downclimberButton[2]);
   
      upclimber = Math.min(upclimber, 0.5);
      downclimeber = Math.min(downclimeber, -0.5);

      up_down_motor.set(upclimber);
      up_down_motor.set(downclimeber);
    
}

   public void Climber_cling () {
     boolean intakeclimber = controllermap.controllers[controllermap.intakeclimeberButton[0]].getRawButton(controllermap.intakeclimeberButton[1]);
     boolean letoffclimber = controllermap.controllers[controllermap.letoffclimberButton[0]].getRawButton(controllermap.letoffclimberButton[1]);
     
     double speed =  ( intakeclimber ? 0.5 : 0 ) - (letoffclimber ? -0.5 : 0 );
     
      cling_motor.set(speed); 

}

}
