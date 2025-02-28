package frc.robot;

import com.revrobotics.spark.SparkMax;



public class Algae {
    private SparkMax EjectBall;
    
public boolean linkageToggle() {
boolean IntakeToggle = controllermap.controllers[controllermap.intakeToggleButton[0]].getRawButton(controllermap.intakeToggleButton[1]);

if (IntakeToggle == true) {
        EjectBall.set(0.5);
}
return true;
}
}

//Controller 2 is designated specifically for operating the climber and the algae shooter/collector