package frc.robot;

import edu.wpi.first.wpilibj.XboxController;



public abstract class controllermap {

    // a static for 2 controller
    private static XboxController controller0 = new XboxController(0);
    private static XboxController controller1= new XboxController(1);
    public static XboxController[] controllers = {controller0, controller1};
    
    // drive
    public static int[] forwardAxis = {0,0};  // controller 0, Axis 0 - LY-axis
    public static int[] strafeAxis = {0,1};   // controller 0, Axis 1 - LX-axis
    public static int[] rotateAxis = {0,4};   // controller 0, Axis 4 - LY-axis
   

    // coral
    public static int[] cturnrButton = {0,2}; // controller 0, Button 2
    public static int[] cturnlButton = {0,0}; // controller 0, Button 0
    public static int[] cdoorButton = {0,6};  // controller 0, Button 6
    public static int[] gyrorestButton = {0,5}; // controller 0 , Button 5
    
    // climber
    public static int[] upclimberButton = {1,2}; // controller 1, Axis 2 - LT-axis
    public static int[] downclimberButton = {1,3}; // controller 1, Axis 3 -RT-axis
    public static int[] intakeclimeberButton = { 1,5}; 
    public static int[] letoffclimberButton = {1,6};
    
    public static int[] intakeToggleButton = {1,4}; // controller 1, Button 4
    public static int[] letoffToggleButton = {1,2};
    public static int[] sturnrButton = {1,3};
    public static int[] sturnlButton = {1,1};

   }

     

     

     
   
    
