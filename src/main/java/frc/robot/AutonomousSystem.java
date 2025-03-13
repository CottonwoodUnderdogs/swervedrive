package frc.robot;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
public class AutonomousSystem {
    
 private Timer timer = new Timer();
 private boolean isMoving = false;
 private SwerveDriveKinematics kinematics =   new SwerveDriveKinematics(
  new Translation2d(14.5, 11.5),  // Front Left wheel position
  new Translation2d(-14.5, 11.5),  // Back Left wheel position
  new Translation2d(14.5, -11.5), // Front Right wheel position
  new Translation2d(-14.5, -11.5)   // Back Right wheel position
  );

 public AutonomousSystem (){
   
 }

 public boolean moveForTime(double T, double F, double S, double R) {

  if (!isMoving) {
   timer.start();
   timer.reset();
   isMoving = true;
  }

  double time = timer.get();
  ChassisSpeeds robotVelocity = new ChassisSpeeds(F, S, R);
  SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);

  if (time < T) {
   SwerveSubsystem.FLWheel.speed(states[1]);
   SwerveSubsystem.FLWheel.setturnspeed(states[1]);
    
   SwerveSubsystem.FRWheel.speed(states[3]);
   SwerveSubsystem.FRWheel.setturnspeed(states[3]);

   SwerveSubsystem.BLWheel.setspeed(states[0]);

   SwerveSubsystem.BRWheel.speed(states[2]);
   SwerveSubsystem.BRWheel.setturnspeed(states[2]);   
   } 

   else {
   isMoving=false;
   SwerveSubsystem.FLWheel.stop();
   SwerveSubsystem.FRWheel.stop();
   SwerveSubsystem.BLWheel.stop();
   SwerveSubsystem.BRWheel.stop();
   }
     return isMoving;
    //  PIDController pid = new PIDController(1, 0, 0);
    //  SwerveSubsystem.backLeftDrive.set(pid.calculate(SwerveSubsystem.backLeftDrive.getEncoder().getPosition()));

   }



   public boolean rotation (double degree) {

   if (!isMoving) {
    Robot.gyro.reset();
    isMoving = true;
   }

   double gyroAngle = Robot.gyro.getAngle();
   double target = degree;

//   if (gyroAngle < 0) {
   double R = 1*1/5;
   ChassisSpeeds robotVelocity = new ChassisSpeeds(0, 0, 0.1);
   SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
   
   if (gyroAngle > target) {
    SwerveSubsystem.FLWheel.speed(states[1]);
    SwerveSubsystem.FLWheel.setturnspeed(states[1]);
    
    SwerveSubsystem.FRWheel.speed(states[3]);
    SwerveSubsystem.FRWheel.setturnspeed(states[3]);

    SwerveSubsystem.BLWheel.setspeed(states[0]);

    SwerveSubsystem.BRWheel.speed(states[2]);
    SwerveSubsystem.BRWheel.setturnspeed(states[2]);

  }
   else {
   isMoving=false;
   SwerveSubsystem.FLWheel.stop();
   SwerveSubsystem.FRWheel.stop();
   SwerveSubsystem.BLWheel.stop();
   SwerveSubsystem.BRWheel.stop();
 }

    return isMoving;

}

//   else if (gyroAngle > 0) {
//     double R = 1*1/20;
//     ChassisSpeeds robotVelocity = new ChassisSpeeds(0, 0, R);
//      SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
//    if (gyroAngle < target) {
//      SwerveSubsystem.FLWheel.speed(states[1]);
//      SwerveSubsystem.FLWheel.setturnspeed(states[1]);
//      SwerveSubsystem.FRWheel.setspeed(states[3]);
//      SwerveSubsystem.BLWheel.setspeed(states[0]);
//      SwerveSubsystem.BRWheel.setspeed(states[2]);
//   }
  

//  else {
//    isMoving=false;
//    SwerveSubsystem.FLWheel.stop();
//    SwerveSubsystem.FRWheel.stop();
//    SwerveSubsystem.BLWheel.stop();
//    SwerveSubsystem.BRWheel.stop();
//  }


    // if (degree)
    // SwerveSubsystem.FLWheel.setrotationspeed(states[1]);
    // SwerveSubsystem.FRWheel.setrotationspeed(states[3]);
    // SwerveSubsystem.BLWheel.setrotationspeed(states[0]);
    // SwerveSubsystem.BRWheel.setrotationspeed(states[2]);

    // double relencodervalueFL = SwerveSubsystem.frontLeftturn.getAbsoluteEncoder().getPosition();
    // double relencodervalueBL = SwerveSubsystem.backLeftturn.getAbsoluteEncoder().getPosition();
    // double relencodervalueFR= SwerveSubsystem.frontRightturn.getAbsoluteEncoder().getPosition();
    // double relencodervalueBR= SwerveSubsystem.backLeftturn.getAbsoluteEncoder().getPosition();
 
    // System.out.println( "encoderFL  " + relencodervalueFL );
    // System.out.println( "encoderBL  " + relencodervalueBL ); 
    // System.out.println( "encoderFR  " + relencodervalueFR);
    // System.out.println( "encoderBR  " + relencodervalueBR);

    
  }



