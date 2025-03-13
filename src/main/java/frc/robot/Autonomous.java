package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
 private Timer timer;
 private final AutonomousSystem autoSystem;
 private int step=1;

 public Autonomous ( ) {
  autoSystem = new AutonomousSystem();
 }
 public void option1 () {
    System.out.println(Robot.gyro.getAngle());
   
    switch (step) {
    case 1:
    boolean value1 = autoSystem.rotation(50);
    if (value1 == false) {
        step = 2;
    }

    break;
    
    default:
    boolean end = autoSystem.moveForTime(0, 0, 0, 0);
    
    break;   

    }

 }
 public void testing1() {
    switch(step) {
    case 1:
boolean value1 = autoSystem.moveForTime(10,0.1,0,0);
    if (value1 == false)  {  
     
     step=2;
     
 }
}
 }
 public void testing () {
   switch(step) {
   case 1:
    boolean value1 = autoSystem.moveForTime(5,0.5,0,0);
    if (value1 == false)  {  

     step=2;

    }
    System.out.println("case 1");
   break;

//    case 2:
//     boolean value2 =  autoSystem.moveForTime(15,0,0.1,0);

//     if (value2  == false)  {  
//      step=3;
//     }
//      System.out.println("case 2");
//     break;

//    case 3:
//     boolean value3 = autoSystem.moveForTime(4, 0.1, 0, 0);
//     if (value3  == false)  {  
//         step=4;
//     }
//     System.out.println("case 3");
//     break;


    default:
    boolean end = autoSystem.moveForTime(0, 0, 0, 0);
    
    break;

   }

  }

 }

 
     