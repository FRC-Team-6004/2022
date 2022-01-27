// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
  public ShooterSubsystem() {}

  public static CANSparkMax shooterLeft = new CANSparkMax(7, MotorType.kBrushless);
  public static CANSparkMax shooterRight = new CANSparkMax(8, MotorType.kBrushless);
  /*
  private static RelativeEncoder encoderLeft;
  private static RelativeEncoder encoderRight;
  private static SparkMaxPIDController pidControllerLeft;
  private static SparkMaxPIDController pidControllerRight;

  public static double kP;
  public static double kI;
  public static double kD;
  public static double kIz;
  public static double kFF;
  public static double kMaxOutput;
  public static double kMinOutput;
  public static double maxRPM;*/
  

  public void initialize() {
    shooterLeft.restoreFactoryDefaults();
    shooterRight.restoreFactoryDefaults();

    /*
    pidControllerLeft = shooterLeft.getPIDController();
    pidControllerRight = shooterRight.getPIDController();

    encoderLeft = shooterLeft.getEncoder();
    encoderRight = shooterRight.getEncoder();

    // PID coefficients
    kP = 6e-5; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000015; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    // set PID coefficients
    pidControllerLeft.setP(kP);
    pidControllerLeft.setI(kI);
    pidControllerLeft.setD(kD);
    pidControllerLeft.setIZone(kIz);
    pidControllerLeft.setFF(kFF);
    pidControllerLeft.setOutputRange(kMinOutput, kMaxOutput);

    pidControllerRight.setP(kP);
    pidControllerRight.setI(kI);
    pidControllerRight.setD(kD);
    pidControllerRight.setIZone(kIz);
    pidControllerRight.setFF(kFF);
    pidControllerRight.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    */
  }
  @Override
  public void periodic() {
    
  }

  public static void joystickShoot(Joystick stick)
  {
    /*
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { pidControllerLeft.setP(p); kP = p; }
    if((i != kI)) { pidControllerLeft.setI(i); kI = i; }
    if((d != kD)) { pidControllerLeft.setD(d); kD = d; }
    if((iz != kIz)) { pidControllerLeft.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { pidControllerLeft.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      pidControllerLeft.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
    if((p != kP)) { pidControllerRight.setP(p); kP = p; }
    if((i != kI)) { pidControllerRight.setI(i); kI = i; }
    if((d != kD)) { pidControllerRight.setD(d); kD = d; }
    if((iz != kIz)) { pidControllerRight.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { pidControllerRight.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      pidControllerRight.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
    */

    /**
     * PIDController objects are commanded to a set point using the 
     * SetReference() method.
     * 
     * The first parameter is the value of the set point, whose units vary
     * depending on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four 
     * parameters:
     *  com.revrobotics.CANSparkMax.ControlType.kDutyCycle
     *  com.revrobotics.CANSparkMax.ControlType.kPosition
     *  com.revrobotics.CANSparkMax.ControlType.kVelocity
     *  com.revrobotics.CANSparkMax.ControlType.kVoltage
     */
    double slideVal = stick.getRawAxis(3);
    //double setPoint = slideVal*maxRPM;
    if(stick.getRawButton(1))
    {
      //pidControllerLeft.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
      //pidControllerRight.setReference(setPoint, CANSparkMax.ControlType.kVelocity);

      shooterLeft.set(slideVal);
      shooterRight.set(-slideVal);   
    }
    else 
    {
      //pidControllerLeft.setReference(0, CANSparkMax.ControlType.kVelocity);
      //pidControllerRight.setReference(0, CANSparkMax.ControlType.kVelocity);

      shooterLeft.set(0);
      shooterRight.set(0);   
    }

    
    //SmartDashboard.putNumber("SetPoint", setPoint);
    //SmartDashboard.putNumber("ProcessVariable", encoderRight.getVelocity());
  }

  @Override
  public void simulationPeriodic() {
  }


}
