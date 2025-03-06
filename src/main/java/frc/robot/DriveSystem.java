package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//This extends the DriveSystem to everything that it contains, the driving and turning elements and the kinematics which the wheels work with
 public class DriveSystem {

  
  public ADXRS450_Gyro gyro;
  public SwerveDriveKinematics kinematics =   new SwerveDriveKinematics(  
      new Translation2d(14.5, 11.5),  // Front Left wheel position
      new Translation2d(-14.5, 11.5),  // Back Left wheel position
      new Translation2d(14.5, -11.5), // Front Right wheel position
      new Translation2d(-14.5, -11.5)   // Back Right wheel position
      
      );

    public DriveSystem ( ADXRS450_Gyro A) {
  
      gyro = A;

     }

    public void drivemotor(){

       double forward = controllermap.controllers[controllermap.forwardAxis[0]].getRawAxis(controllermap.forwardAxis[1]);
       double strafe = controllermap.controllers[controllermap.strafeAxis[0]].getRawAxis(controllermap.strafeAxis[1]);
       double rotate = controllermap.controllers[controllermap.rotateAxis[0]].getRawAxis(controllermap.rotateAxis[1]);
    
       double gyroAngle= gyro.getAngle();
       double gaINrads = Math.toRadians(gyroAngle);

       double FWD = forward * Math.cos(gaINrads) + strafe * Math.sin(gaINrads);
       double STR= forward * Math.sin(gaINrads) + strafe * Math.cos(gaINrads);
       double RWT = rotate;

       boolean condition1 = forward < 0.15 && forward > -0.15 && strafe > -0.1 && strafe < 0.1 && rotate >1 && rotate < -1;
       boolean condition2 =  rotate > 0.1 && rotate < -0.1;

      ChassisSpeeds robotVelocity = new ChassisSpeeds(FWD, STR, RWT);

      SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
      SwerveModuleState testState = new SwerveModuleState();
      testState.angle = new Rotation2d(0.00);
       
      SwerveSubsystem.FLWheel.setturnspeed(states[2]);
      SwerveSubsystem.FRWheel.setturnspeed(states[3]);
      SwerveSubsystem.BLWheel.setturnspeed(states[0]);
      SwerveSubsystem.BRWheel.setturnspeed(states[1]);



       if( condition1) {
        SwerveSubsystem.FLWheel.stop();
        SwerveSubsystem.FRWheel.stop();
        SwerveSubsystem.BLWheel.stop();
        SwerveSubsystem.BRWheel.stop();

       }

       else if ( condition2 ) {
        SwerveSubsystem.FLWheel.setdrivespeed(states[2]);
        SwerveSubsystem.FRWheel.setdrivespeed(states[3]);
        SwerveSubsystem.BLWheel.setdrivespeed(states[0]);
        SwerveSubsystem.BRWheel.setdrivespeed(states[1]);

       }

       else {
        SwerveSubsystem.FLWheel.setdrivespeed(states[2]);
        SwerveSubsystem.FRWheel.setdrivespeed(states[3]);
        SwerveSubsystem.BLWheel.setdrivespeed(states[0]);
        SwerveSubsystem.BRWheel.setdrivespeed(states[1]);
   
       SmartDashboard.putNumber("Gyro Angle", gyroAngle);

     }
  }
}


