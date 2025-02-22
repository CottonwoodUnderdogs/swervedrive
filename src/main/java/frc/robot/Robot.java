// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
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
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

   
    private Gyro GyroReset = new Gyro (gyro);
   


    //private Coral CoralSystem;
     //AnalogGyro gyro = new AnalogGyro(0);

    //private final SparkBase Spark = new SparkBase();
     //Wheel represents a class that is composed of 2 sparkmax motors representing the turn and drive motor of each wheel as well as PID values and an angle value for each of the wheels
    private wheel FLWheel = new wheel (frontLeftDrive, frontLeftturn, 1.4, 0, 0, 0.506);   //1,2
    private wheel FRWheel = new wheel (frontRightDrive, frontRightturn, 0.2, 0, 2, 0.822); //3,4
    private wheel BLWheel = new wheel ( backLeftDrive, backLeftturn, 1.4, 0, 0, 0.127);    //8,7
    private wheel BRWheel = new wheel ( backRightDrive, backRightturn, 1.4, 0, 0, 0.309);  //5,6
    

    private XboxController controller;
    private SparkClosedLoopController FLController;

    //CoralSystem = new Coral(coralturnmotor, coraldoormotor, controller);

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        
       new Translation2d(14.5, 11.5),  // Front Left wheel position
        new Translation2d(-14.5, 11.5),  // Back Left wheel position
        new Translation2d(14.5, -11.5), // Front Right wheel position
        new Translation2d(-14.5, -11.5)   // Back Right wheel position

    );

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
        controller = new XboxController(0);
        
        //Need to somehow convert the configturnmotor object to the base sparkmax.config type i think???
      
        //TODO: Maybe get the value of the angle from the encoder and run it through the getClosedLoopController() as the input
    }


  /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
      
       double forward = controllermap.controllers[controllermap.forwardAxis[0]].getRawAxis(controllermap.forwardAxis[1]);
       double strafe = controllermap.controllers[controllermap.strafeAxis[0]].getRawAxis(controllermap.strafeAxis[1]);
       double rotate = controllermap.controllers[controllermap.rotateAxis[0]].getRawAxis(controllermap.rotateAxis[1]);
        //double forward= controller.getLeftY(); 
        
        //double = controller.getRightY(); 
       
       
       double gyroAngle= gyro.getAngle();
   
      double gaINrads = Math.toRadians(gyroAngle);

        double FWD = forward * Math.cos(gaINrads) + strafe * Math.sin(gaINrads);
        double STR= -forward * Math.sin(gaINrads) + strafe * Math.cos(gaINrads);
        

     
        System.out.println("forward: " + FWD);
        System.out.println("strafe: " + STR);

       

         ChassisSpeeds robotVelocity = new ChassisSpeeds(FWD, STR, rotate);

         SmartDashboard.putNumber("fwd", FWD);
        SmartDashboard.putNumber("str", STR);
        SmartDashboard.putNumber("rotate", rotate);
        //ChassisSpeeds robotVelocity = new ChassisSpeeds(0.2, 0, 0);

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
        boolean condition1 = forward < 0.15 && forward > -0.15 && strafe > -0.1 && strafe < 0.1 && rotate >1 && rotate < -1;
        boolean condition2 =  rotate > 0.1 && rotate < -0.1;
        
        
           
          FRWheel.setturnspeed(states[2]);
          BRWheel.setturnspeed(states[3]);
          FLWheel.setturnspeed(states[0]);
          BLWheel.setturnspeed(states[1]);
        
     //     m_frontLeft.driveVoltage(voltage.baseUnitMagnitude());

     System.out.println("angle" + states[0].angle.getDegrees());

        ncycles = ncycles + 1; 
        double relencodervalueFL = frontLeftturn.getAbsoluteEncoder().getPosition();
        double relencodervalueBL = backLeftturn.getAbsoluteEncoder().getPosition();
        double relencodervalueFR= frontRightturn.getAbsoluteEncoder().getPosition();
        double relencodervalueBR= backLeftturn.getAbsoluteEncoder().getPosition();
        double tempOffset = 0;

        SmartDashboard.putNumber("encoder value", relencodervalueBL);
        SmartDashboard.putNumber("goal position", states[1].angle.getRotations());
        
           if (ncycles%50 == 0) {

           // System.out.print("desired: "+states[3].angle.getRotations());
           // System.out.println("Actual: "+relencodervalueBL);
            System.out.println(" FL:  "+(Math.abs(states[0].angle.getRotations() - relencodervalueFL)-tempOffset));
           // System.out.println(" BL:  "+(Math.abs(states[3].angle.getRotations() - relencodervalueBL)-tempOffset));
           // System.out.println(" FR:  "+(Math.abs(states[2].angle.getRotations() - relencodervalueFR)-tempOffset));
           // System.out.println(" BR:  "+(Math.abs(states[3].angle.getRotations() - relencodervalueBR)-tempOffset));
           // System.out.println("angle" + states[0].angle.getDegrees());
          
           }
      if( condition1) {
        frontLeftDrive.set(0);
        backLeftDrive.set(0);
        frontRightDrive.set(0);
        backRightDrive.set(0); 
       } 
        else if ( condition2 ) {
        FLWheel.setdrivespeed(states[2]);
        BLWheel.setdrivespeed(states[3]);
        FRWheel.setdrivespeed(states[0]);
        BRWheel.setdrivespeed(states[1]);
       }
        else {
         FLWheel.setdrivespeed(states[2]);
         BLWheel.setdrivespeed(states[3]);
         FRWheel.setdrivespeed(states[0]);
         BRWheel.setdrivespeed(states[1]);
       }

       //Every time clink the button it is reset the forward 
       // Button LB
       if (controller.getRawButton(5)) {
        gyro.reset();
       }


       SmartDashboard.putNumber("AbsoluteEncoderValue", relencodervalueFR);
       SmartDashboard.putNumber("DesiredValue", states[0].angle.getRotations());
       SmartDashboard.putNumber("Current", frontRightturn.getOutputCurrent());
       
      //CoralSystem.Coral_door_turn();
      GyroReset.Gyro_resetforward();
       
      
  }
   


@Override
public void teleopInit() { 
   

}
}



