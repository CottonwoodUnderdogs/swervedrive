// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.drive.SwerveDriveKinematics;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.servohub.ServoHub.ResetMode;

import static edu.wpi.first.units.Units.Value;
import edu.wpi.first.wpilibj.AnalogGyro;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
//import com.revrobotics.spark.config.ClosedLoopConfig; 
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

public class Robot extends TimedRobot {
    
    
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
   
    private final SparkMax frontLeftDrive = new SparkMax(1,MotorType.kBrushless);
    private final SparkMax backLeftDrive = new SparkMax(8,MotorType. kBrushless);
    private final SparkMax frontRightDrive = new SparkMax(3,MotorType.kBrushless);
    private final SparkMax backRightDrive = new SparkMax(5,MotorType.kBrushless);

    private final SparkMax frontLeftturn = new SparkMax(2,MotorType.kBrushless);
    private final SparkMax backLeftturn = new SparkMax(7,MotorType. kBrushless);
    private final SparkMax frontRightturn = new SparkMax(4,MotorType.kBrushless);
    private final SparkMax backRightturn = new SparkMax(6,MotorType.kBrushless);

    //temporary testing code
    private final SparkMax fourbarlinkage = new SparkMax(10,MotorType.kBrushless);

    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    private Timer time;
   
    private Gyro GyroReset = new Gyro (gyro);
    private DriveSystem Drive = new DriveSystem(frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive, frontRightturn, frontLeftturn, backRightturn, backLeftturn, gyro, null);

    //private Coral CoralSystem;
     //AnalogGyro gyro = new AnalogGyro(0);

    //private final SparkBase Spark = new SparkBase();
     //Wheel represents a class that is composed of 2 sparkmax motors representing the turn and drive motor of each wheel as well as PID values and an angle value for each of the wheels
  
    private XboxController controller;
    private XboxController controller2;

    //CoralSystem = new Coral(coralturnmotor, coraldoormotor, controller);

    //Coral = new Coral ( coralturnmotor, coraldoormotor, controller);


    int ncycles = 0;
    @Override
    public void robotInit() {
         ncycles = 0;
        
         //gyro.reset();
         gyro.calibrate();
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        //SmartDashboard.putData("Auto choices", m_chooser);
        //controller = new XboxController(0);
        //controller2 = new XboxController(1);

        //Need to somehow convert the configturnmotor object to the base sparkmax.config type i think???
      
    }


 
  @Override
      public void teleopPeriodic() {
        //double tempAxis = controller2.getLeftY();
        //fourbarlinkage.set(tempAxis);
      

       Drive.drivemotor();
       GyroReset.Gyro_resetforward();
      
    }
  

@Override
public void teleopInit() { 
   

}
}



