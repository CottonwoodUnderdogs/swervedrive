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


import com.revrobotics.spark.SparkMax;
//TODO: Replace all uses of TalonFX with SparkMax. Then we should be able to start writing new code.
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Robot extends TimedRobot {
    
    
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();

    private final SparkMax frontLeft = new SparkMax(1,MotorType.kBrushless);
    private final SparkMax backLeft = new SparkMax(2,MotorType. kBrushless);
    private final SparkMax frontRight = new SparkMax(3,MotorType.kBrushless);
    private final SparkMax backRight = new SparkMax(4,MotorType.kBrushless);
   
    private XboxController controller;
    
    private SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(-0.5, 0.5),  // Front Left wheel position
        new Translation2d(0.5, -0.5),  // Front Right wheel position
        new Translation2d(-0.5, -0.5), // Back Left wheel position
        new Translation2d(0.5, -0.5)   // Back Right wheel position
    );



    @Override
    public void robotInit() {
       
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);

    }


  /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        
        double forward= controller.getLeftY(); 
        double strafe= controller.getLeftX();
        //double = controller.getRightY(); 
        double rotate= controller.getRightX();
       
        
            
        
        
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(new ChassisSpeeds(forward, strafe, rotate));

        // frontLeft.set(speed);
        // frontRight.set(speed);
        // backLeft.set(speed);
        // backRight.set(speed);
        
        frontLeft.set(states[0].speedMetersPerSecond);
        backLeft.set(states[1].speedMetersPerSecond);
        frontRight.set(states[2].speedMetersPerSecond);
        backRight.set(states[3].speedMetersPerSecond);

    System.out.println("Joystick Values:");
    System.out.println("Forward: " + forward);
    System.out.println("Strafe: " + strafe);
    System.out.println("Rotate: " + rotate);

    System.out.println("Swerve Module States:");
    System.out.println("Front Left Speed: " + states[0].speedMetersPerSecond + ", Angle: " + states[0].angle.getDegrees());
    System.out.println("Back Left Speed: " + states[1].speedMetersPerSecond + ", Angle: " + states[1].angle.getDegrees());
    System.out.println("Front Right Speed: " + states[2].speedMetersPerSecond + ", Angle: " + states[2].angle.getDegrees());
    System.out.println("Back Right Speed: " + states[3].speedMetersPerSecond + ", Angle: " + states[3].angle.getDegrees());

    }
   


@Override
public void teleopInit() { 
  controller = new XboxController(0); 

}
}