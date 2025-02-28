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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;





import com.revrobotics.spark.SparkMax;

//import com.revrobotics.spark.config.ClosedLoopConfig; 

   public class Autonomous {
    
    private final wheel FLWheel, FRWheel, BLWheel, BRWheel;

      private  SparkMax frontLeftDrive ;
      private  SparkMax backLeftDrive;
      private  SparkMax frontRightDrive ;
      private  SparkMax backRightDrive ;

      private  SparkMax frontLeftturn;
      private  SparkMax backLeftturn ;
      private  SparkMax frontRightturn ;
      private  SparkMax backRightturn ;
    
       private Timer timer;
       private DriveSystem driveSystem;

    public Autonomous (SparkMax FLDrive, SparkMax BLDrive, SparkMax FRDrive, SparkMax BRDrive, SparkMax FLTurn, SparkMax BLTurn, SparkMax FRTurn, SparkMax BRTurn, ADXRS450_Gyro A, SwerveDriveKinematics B, Timer C) {
      
      timer = C;

      frontLeftDrive = FLDrive;
      backLeftDrive = BLDrive;
      frontRightDrive = FRDrive;
      backRightDrive = BRDrive;

      frontLeftturn = FLTurn;
      backLeftturn = BLTurn;
      frontRightturn = FRTurn;
      backRightturn = BRTurn;
      
      FLWheel = new wheel (frontLeftDrive, frontLeftturn, 1.4, 0, 0, 0.506);   //1,2
      FRWheel = new wheel (frontRightDrive, frontRightturn, 0.2, 0, 2, 0.822); //3,4
      BLWheel = new wheel ( backLeftDrive, backLeftturn, 1.4, 0, 0, 0.127);    //8,7
      BRWheel = new wheel ( backRightDrive, backRightturn, 1.4, 0, 0, 0.309);  //5,6

    }

   public void autonomous() {
     timer.reset();
     timer.start();
   }
   
   public void autonomousPeriodic () {
     double time = timer.get();

      if (time < 2.0) {
        SwerveModuleState state = new SwerveModuleState (0.5, new Rotation2d( 3.04, 174.88));
       
        FLWheel.setturnspeed(state);
        FRWheel.setturnspeed(state);
        BLWheel.setturnspeed(state);
        BRWheel.setturnspeed(state);
        
        FLWheel.setdrivespeed(state);
        FRWheel.setdrivespeed(state);
        BLWheel.setdrivespeed(state);
        BRWheel.setdrivespeed(state);
    
      }
     
    }
   
 } 

