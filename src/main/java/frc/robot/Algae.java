package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.controllermap;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;


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