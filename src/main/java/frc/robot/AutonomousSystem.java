package frc.robot;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
public class AutonomousSystem {
    
 private Timer timer = new Timer();
 private ADXRS450_Gyro gyro;
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
    SwerveSubsystem.FLWheel.setspeed(states[2]);
    SwerveSubsystem.FRWheel.setspeed(states[3]);
    SwerveSubsystem.BLWheel.setspeed(states[0]);
    SwerveSubsystem.BRWheel.setspeed(states[1]);
        
   } else {
    isMoving=false;
    SwerveSubsystem.FLWheel.stop();
    SwerveSubsystem.FRWheel.stop();
    SwerveSubsystem.BLWheel.stop();
    SwerveSubsystem.BRWheel.stop();
   }
     return isMoving;
   }
}
