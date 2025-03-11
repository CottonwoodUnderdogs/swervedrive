package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.TimedRobot;


public class SwerveSubsystem extends SubsystemBase{
 public static final SparkMax frontLeftDrive =  new SparkMax(1,MotorType.kBrushless);
 public static final SparkMax backLeftDrive = new SparkMax(8,MotorType. kBrushless);
 public static final SparkMax frontRightDrive = new SparkMax(3,MotorType.kBrushless);
 public static final SparkMax backRightDrive = new SparkMax(5,MotorType.kBrushless);
    
 public static final SparkMax frontLeftturn =  new SparkMax(2,MotorType.kBrushless);
 public static final SparkMax backLeftturn = new SparkMax(7,MotorType. kBrushless);
 public static final SparkMax frontRightturn = new  SparkMax(4,MotorType.kBrushless);
 public static final SparkMax backRightturn = new  SparkMax(6,MotorType.kBrushless);

   
 public static final wheel FLWheel = new wheel (frontLeftDrive, frontLeftturn, 2.8, 0, 2, 0.750);   //1,2
 public static final wheel FRWheel = new wheel (frontRightDrive, frontRightturn, 2.8, 0, 2, 0.5); //3,4
 public static final wheel BLWheel = new wheel ( backLeftDrive, backLeftturn, 2.8, 0, 2, 0);    //8,7
 public static final wheel BRWheel = new wheel ( backRightDrive, backRightturn, 2.8, 0, 2, 0.250);  //5,6

 
 }
  