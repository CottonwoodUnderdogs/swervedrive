package frc.robot;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.XboxController;

/* states
 FLWheel 1
 FRWheel 3
 BLWheel 0
 BRWheel 2   */

public class wheel {
      private SparkMax drivemotor;
      private SparkMax turnmotor;
      private SparkMaxConfig Configturnmotor = new SparkMaxConfig();
      private SparkClosedLoopController Turncontroller;
      private int ncycles = 0;
      private double offset;

    //constructor
    public wheel( SparkMax A, SparkMax B, double p, double i, double d, double O ) {
     drivemotor=A;
     turnmotor= B;
     offset = O;
     Configturnmotor.closedLoop.pid(p,i,d);
     Configturnmotor.closedLoop.outputRange(-1, 1);
     Configturnmotor.inverted(true);
     //Configturnmotor.closedLoop.positionWrappingEnabled(true);
     //Configturnmotor.closedLoop.positionWrappingInputRange(0,1);
     Configturnmotor.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
     turnmotor.configure(Configturnmotor, SparkBase.ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters );
     

     Turncontroller= turnmotor.getClosedLoopController();
        
    }

  public void setdrivespeed (SwerveModuleState state) {
        
        drivemotor.set(state.speedMetersPerSecond);
      
  }
  public void setturnspeed (SwerveModuleState state ) {

    double encodervalue = Math.toRadians(turnmotor.getAbsoluteEncoder().getPosition()*360);   
    double os = Math.toRadians(offset*360);
    double stateangle = Math.toRadians( state.angle.getRotations()*360);
    state.angle=new Rotation2d(stateangle+os);
    state.optimize(new Rotation2d(encodervalue));
    double angle = (state.angle.getRotations());
    Turncontroller.setReference(angle,ControlType.kPosition);
    double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();
    
    
    if (Math.abs(Math.abs(state.angle.getRotations() - relencodervalue) - 0.5 ) <  0.1  ) {

  
   } 
  }
  public void setspeed (SwerveModuleState state) {
    drivemotor.set(state.speedMetersPerSecond);
    
    double encodervalue = Math.toRadians(turnmotor.getAbsoluteEncoder().getPosition()*360);   
    double os = Math.toRadians(offset*360);
    double stateangle = Math.toRadians( state.angle.getRotations()*360);
    state.angle=new Rotation2d(stateangle+os);
    state.optimize(new Rotation2d(encodervalue));
    double angle = (state.angle.getRotations());
    Turncontroller.setReference(angle,ControlType.kPosition);
  }
 public void stop () {
  drivemotor.set(0);
  turnmotor.set(0);
 }

 public void speed (SwerveModuleState state) {
  drivemotor.set(-state.speedMetersPerSecond);

 }
 public void setrotationspeed ( SwerveModuleState state)  {
  double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();
  double angle = state.angle.getDegrees();
  if (Math.abs(Math.abs(state.angle.getRotations() - relencodervalue) -0.5 ) <  0.07  ) {
    turnmotor.set(0);
  }
  else{
    Turncontroller.setReference(angle+offset,ControlType.kPosition);
 }
}
 public void resetrotaion (SwerveModuleState state) {
  turnmotor.set(1);
  drivemotor.set(0);
 }

 } 
