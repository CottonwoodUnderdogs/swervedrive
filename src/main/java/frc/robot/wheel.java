package frc.robot;

import java.lang.Thread.State;

import com.ctre.phoenix6.configs.GyroTrimConfigs;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.XboxController;
import java.lang.Math;
import java.lang.ModuleLayer.Controller;


public class wheel {
      private SparkMax drivemotor;
      private SparkMax turnmotor;
      private SparkMaxConfig Configturnmotor = new SparkMaxConfig();
      private SparkClosedLoopController Turncontroller;
      private int ncycles = 0;
      private double offset;
  
    
    public wheel( SparkMax A, SparkMax B, double p, double i, double d, double O) {

     drivemotor=A;
     turnmotor= B;
     offset = O;
     //Configturnmotor.idleMode(SparkBaseConfig.IdleMode.kCoast);
    
     Configturnmotor.closedLoop.pid(p,i,d);
     Configturnmotor.closedLoop.outputRange(-1, 1);
     Configturnmotor.inverted(true);
     Configturnmotor.closedLoop.positionWrappingEnabled(true);
     Configturnmotor.closedLoop.positionWrappingInputRange(0,1);
     
     Configturnmotor.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
     turnmotor.configure(Configturnmotor, SparkBase.ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters );
     

     Turncontroller= turnmotor.getClosedLoopController();
        
    }

  public void setdrivespeed (SwerveModuleState state) {
        
        drivemotor.set(state.speedMetersPerSecond);
      
  }

 

  public void setturnspeed (SwerveModuleState state ) {

    double encodervalue = turnmotor.getAbsoluteEncoder().getPosition();   
     state.optimize(new Rotation2d(encodervalue));
     double angle = (state.angle.getRotations());

  }

    
  // public void setturnspeed (SwerveModuleState state ) {
     
  //   // SwerveModuleState correctedDesiredState = new SwerveModuleState();
  //   // correctedDesiredState.speedMetersPerSecond = state.speedMetersPerSecond;
  //   // correctedDesiredState.angle = state.angle.plus(Rotation2d.fromRadians(offset));

  //   // // Optimize the reference state to avoid spinning further than 90 degrees.
  //   // SwerveModuleState optimizedDesiredState = SwerveModuleState.optimize(correctedDesiredState,
  //   //     new Rotation2d(turnmotor.getAbsoluteEncoder().getPosition()));
   
        


  //   double angle = turnmotor.getAbsoluteEncoder().getPosition();
  //   // (state.angle.plus(Rotation2d.fromRotations(offset)).getRotations());
  //    double m_turningEncoder = turnmotor.getAlternateEncoder().getPosition();
  //   //state.speedMetersPerSecond = state.speedMetersPerSecond;
  //    System .out.print(

  //    );
  //   state.optimize(new Rotation2d(angle));
  //   Turncontroller.setReference(state.angle.getRotations(), ControlType.kPosition);

   // SwerveModuleState correctedDesiredState = new SwerveModuleState();
    //state.optimize(new Rotation2d(turnmotor.getAbsoluteEncoder().getPosition()));
    // correctedDesiredState.speedMetersPerSecond = state.speedMetersPerSecond;
    // correctedDesiredState.angle = state.angle.plus(Rotation2d.fromRadians(offset));

    // Optimize the reference state to avoid spinning further than 90 degrees.
//     state.optimize (new Rotation2d( turnmotor.getAbsoluteEncoder().getPosition()));

//     System.out.println("Desired State - " + optimizedDesiredState);
//     double angle = (state.angle.plus(Rotation2d.fromRotations(offset)).getRotations());
// optimize angle
//     Turncontroller.setReference(optimizedDesiredState,ControlType.kPosition);
    

//     System.out.println(state.angle.getRotations());
   // double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();
   

    //if (Math.abs(Math.abs(state.angle.getRotations() - relencodervalue) - 0.5 ) <  0.1  ) {
        //turnmotor.set(0);
    //}
      //else{
       
      
  
public void encodervalue(SwerveModuleState state) {
   
    ncycles = ncycles + 1; 
    double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();

    if (ncycles%50 == 0) {
        System.out.println(" state "+(Math.abs(state.angle.getRotations() - relencodervalue)-0.5));
      }
}
}

