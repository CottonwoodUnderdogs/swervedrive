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
//import edu.wpi.first.wpilibj.drive.SwerveDriveKinematics;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.servohub.ServoHub.ResetMode;

import static edu.wpi.first.units.Units.Value;

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
    private final SparkMax backLeftDrive = new SparkMax(3,MotorType. kBrushless);
    private final SparkMax frontRightDrive = new SparkMax(5,MotorType.kBrushless);
    private final SparkMax backRightDrive = new SparkMax(8,MotorType.kBrushless);

    private final SparkMax frontLeftturn = new SparkMax(2,MotorType.kBrushless);
    private final SparkMax backLeftturn = new SparkMax(4,MotorType. kBrushless);
    private final SparkMax frontRightturn = new SparkMax(6,MotorType.kBrushless);
    private final SparkMax backRightturn = new SparkMax(7,MotorType.kBrushless);
    //private final SparkBase Spark = new SparkBase();




    




    private XboxController controller;
    private SparkMaxConfig Configturnmotor;
    private SparkClosedLoopController FLController;

    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(-0.5, 0.5),  // Front Left wheel position
        new Translation2d(0.5, -0.5),  // Front Right wheel position
        new Translation2d(-0.5, -0.5), // Back Left wheel position
        new Translation2d(0.5, -0.5)   // Back Right wheel position
    );
  // Example chassis speeds: 1 meter per second forward, 3 meters
// per second to the left, and rotation at 1.5 radians per second
// counterclockwise.
ChassisSpeeds speeds = new ChassisSpeeds(1.0, 3.0, 1.5);
// Convert to module states
SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);
// Front left module state
// SwerveModuleState frontLeft = moduleStates[0];
// // Front right module state
// SwerveModuleState frontRight = moduleStates[1];
// // Back left module state
// SwerveModuleState backLeft = moduleStates[2];
// // Back right module state
// SwerveModuleState backRight = moduleStates[3];
int ncycles;
int elapsedcycles;
    @Override
    public void robotInit() {
        ncycles = 0;
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        controller = new XboxController(0);
        Configturnmotor = new SparkMaxConfig();
        //Past value was 0.21
        Configturnmotor.closedLoop.pid(1.6,0,.3);
        //Configturnmotor.inverted(true);
        //Creating PID and constants
        Configturnmotor.closedLoop.outputRange(-1, 1);
        Configturnmotor.closedLoop.positionWrappingEnabled(true);
        Configturnmotor.closedLoop.positionWrappingInputRange(0,1);
        Configturnmotor.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
        //Need to somehow convert the configturnmotor object to the base sparkmax.config type i think???
      
        frontLeftturn.configure(Configturnmotor, SparkBase.ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters );

        FLController = frontLeftturn.getClosedLoopController();
        
       //TODO: Maybe get the value of the angle from the encoder and run it through the getClosedLoopController() as the input
    }


  /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        ncycles = ncycles + 1;
        double forward= controller.getLeftY(); 
        double strafe= controller.getLeftX();
        //double = controller.getRightY(); 
        double rotate= controller.getRightX();
        ChassisSpeeds robotVelocity = new ChassisSpeeds(forward, strafe, rotate);
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
        boolean condition = forward < 0.15 && strafe < 0.1 && forward > -0.15 && strafe > -0.1;
        double relencodervalue = frontLeftturn.getAbsoluteEncoder().getPosition();
        double angle = (states[0].angle.getRotations() );
        
          if (ncycles%50 == 0) {
            System.out.println("Encoder value: "+(Math.abs(states[0].angle.getRotations() - relencodervalue)-0.5));
          }


          if (Math.abs(Math.abs(states[0].angle.getRotations() - relencodervalue) -0.5 ) <  0.05 && condition ) {
            frontLeftturn.set(0);
          }
          else{
        
          FLController.setReference(angle,ControlType.kPosition); 
        }
        
        //System.out.println("Commanded Robot velocity: "+ robotVelocity.vxMetersPerSecond+", "+robotVelocity.vyMetersPerSecond+", "+robotVelocity.omegaRadiansPerSecond)

        //Inserting value of the state and getting an angular velocity by running it through the PID controller
        
       if( forward < 0.15 && condition) {
        frontLeftDrive.set(0);
        backLeftDrive.set(0);
        frontRightDrive.set(0);
        backRightDrive.set(0); 
       } 
       else {
        frontLeftDrive.set(states[0].speedMetersPerSecond);
        backLeftDrive.set(states[1].speedMetersPerSecond);
        frontRightDrive.set(states[2].speedMetersPerSecond);
        backRightDrive.set(states[3].speedMetersPerSecond);
       }

    //System.out.println("Joystick Values:");
    //System.out.println("Forward: " + forward);
    //System.out.println("Strafe: " + strafe);
    //System.out.println("Rotate: " + rotate);

    //System.out.println("Swerve Module States:");
    //System.out.println("Front Left Speed: " + states[0].speedMetersPerSecond + ", Angle: " + states[0].angle.getDegrees());
    //System.out.println("Back Left Speed: " + states[1].speedMetersPerSecond + ", Angle: " + states[1].angle.getDegrees());
    }
   


@Override
public void teleopInit() { 
   

}
}