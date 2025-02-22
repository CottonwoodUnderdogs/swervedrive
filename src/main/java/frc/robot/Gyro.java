package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.controllermap;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;



public class Gyro {
    private ADXRS450_Gyro gyro;
 

public Gyro (  ADXRS450_Gyro A) {
    gyro = A;
}

public void Gyro_resetforward () {
    boolean gyroReset= controllermap.controllers[controllermap.gyrorestButton[0]].getRawButton(controllermap.gyrorestButton[1]);
    if (gyroReset == true ) {
        gyro.reset();
       }

}


}
