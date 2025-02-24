package frc.robot;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 public class DriveSystem {
      private final wheel FLWheel, FRWheel, BLWheel, BRWheel;

      private SparkMax frontLeftDrive ;
      private SparkMax backLeftDrive;
      private SparkMax frontRightDrive ;
      private SparkMax backRightDrive ;

      private SparkMax frontLeftturn;
      private SparkMax backLeftturn ;
      private SparkMax frontRightturn ;
      private SparkMax backRightturn ;
 
      private ADXRS450_Gyro gyro;
      private SwerveDriveKinematics kinematics;


    public DriveSystem (SparkMax FLDrive, SparkMax BLDrive, SparkMax FRDrive, SparkMax BRDrive, SparkMax FLTurn, SparkMax BLTurn, SparkMax FRTurn, SparkMax BRTurn, ADXRS450_Gyro A, SwerveDriveKinematics B) {
   
      gyro = A;
      kinematics = B;

      frontLeftDrive = FLDrive;
      backLeftDrive = BLDrive;
      frontRightDrive = FRDrive;
      backRightDrive = BRDrive;

      frontLeftturn = FLTurn;
      backLeftturn  = BLTurn;
      frontRightturn = FRTurn;
      backRightturn = BRTurn;
 
      FLWheel = new wheel (frontLeftDrive, frontLeftturn, 1.4, 0, 0, 0.506);   //1,2
      FRWheel = new wheel (frontRightDrive, frontRightturn, 1.4, 0, 2, 0.822); //3,4
      BLWheel = new wheel ( backLeftDrive, backLeftturn, 1.4, 0, 0, 0.127);    //8,7
      BRWheel = new wheel ( backRightDrive, backRightturn, 1.4, 0, 0, 0.309);  //5,6

   }

    public void drivemotor(){

        
       double forward = controllermap.controllers[controllermap.forwardAxis[0]].getRawAxis(controllermap.forwardAxis[1]);
       double strafe = controllermap.controllers[controllermap.strafeAxis[0]].getRawAxis(controllermap.strafeAxis[1]);
       double rotate = controllermap.controllers[controllermap.rotateAxis[0]].getRawAxis(controllermap.rotateAxis[1]);
    
       double gyroAngle= gyro.getAngle();
       double gaINrads = Math.toRadians(gyroAngle);

       double FWD = forward * Math.cos(gaINrads) + strafe * Math.sin(gaINrads);
       double STR= -forward * Math.sin(gaINrads) + strafe * Math.cos(gaINrads);
       double RWT = rotate;

       boolean condition1 = forward < 0.15 && forward > -0.15 && strafe > -0.1 && strafe < 0.1 && rotate >1 && rotate < -1;
       boolean condition2 =  rotate > 0.1 && rotate < -0.1;

      ChassisSpeeds robotVelocity = new ChassisSpeeds(FWD, STR, RWT);
      SwerveModuleState[] states = kinematics.toSwerveModuleStates(robotVelocity);
      
         System.out.println(states[0]);

         FRWheel.setturnspeed(states[2]);
         BRWheel.setturnspeed(states[3]);
         FLWheel.setturnspeed(states[0]);
         BLWheel.setturnspeed(states[1]);


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
    
    // double relencodervalueFL = frontLeftturn.getAbsoluteEncoder().getPosition();
    // double relencodervalueBL = backLeftturn.getAbsoluteEncoder().getPosition();
       double relencodervalueFR= frontRightturn.getAbsoluteEncoder().getPosition();
    // double relencodervalueBR= backLeftturn.getAbsoluteEncoder().getPosition();
    // double tempOffset = 0;
    // int ncycles = 0;

     // double ncycles = ncycles + 1; 

       SmartDashboard.putNumber("AbsoluteEncoderValue", relencodervalueFR);
       SmartDashboard.putNumber("DesiredValue", states[0].angle.getRotations());
       SmartDashboard.putNumber("Current", frontRightturn.getOutputCurrent());
       

      //if (ncycles%50 == 0) {

        // System.out.print("desired: "+states[3].angle.getRotations());
        // System.out.println("Actual: "+relencodervalueBL);
        //System.out.println(" FL:  "+(Math.abs(states[0].angle.getRotations() - relencodervalueFL)-tempOffset));
        // System.out.println(" BL:  "+(Math.abs(states[3].angle.getRotations() - relencodervalueBL)-tempOffset));
        // System.out.println(" FR:  "+(Math.abs(states[2].angle.getRotations() - relencodervalueFR)-tempOffset));
        // System.out.println(" BR:  "+(Math.abs(states[3].angle.getRotations() - relencodervalueBR)-tempOffset));
        // System.out.println("angle" + states[0].angle.getDegrees());
       
     }
  }


