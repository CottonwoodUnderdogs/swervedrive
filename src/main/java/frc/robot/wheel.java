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

    double encodervalue = turnmotor.getAbsoluteEncoder().getPosition();   
     state.optimize(new Rotation2d(encodervalue));
     double angle = (state.angle.getRotations());

    Turncontroller.setReference(angle+offset,ControlType.kPosition);
  //System.out.println(state.angle.getRotations());
    double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();
    
    
    if (Math.abs(Math.abs(state.angle.getRotations() - relencodervalue) - 0.5 ) <  0.1  ) {
        //turnmotor.set(0);
    }
      else{
       
        
  
} 
  }
public void encodervalue(SwerveModuleState state) {
   
    ncycles = ncycles + 1; 
    double relencodervalue = turnmotor.getAbsoluteEncoder().getPosition();

    if (ncycles%50 == 0) {
        System.out.println(" state "+(Math.abs(state.angle.getRotations() - relencodervalue)-0.5));
      }
}
}

