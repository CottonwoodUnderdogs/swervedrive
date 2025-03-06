package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
 private Timer timer;
 private final AutonomousSystem autoSystem;
 private int step=1;

 public Autonomous ( ) {
  autoSystem = new AutonomousSystem();
 }

 public void moving () {
  boolean isActive = autoSystem.moveForTime(0, 0, 0, 0);
  
   switch(step) {
   case 1:
    boolean value1 = isActive;
    if (value1 == false)  {  
     autoSystem.moveForTime(5,0.5,0,0);
     step=2;
    }
   break;

   case 2:
    boolean value2 = isActive;
    if (value2  == false)  {  
     autoSystem.moveForTime(10,0,0.5,0);
     step=3;
    }
    break;

   case 3:
    boolean value3 = isActive; 
    if (value3  == false)  {  
     autoSystem.moveForTime(15,0,0,1);
     step=4;
    }
    break;

    }

   }

  }

 
     