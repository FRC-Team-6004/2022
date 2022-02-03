// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

import javax.swing.plaf.SliderUI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
  public static CANSparkMax shooterLeft = new CANSparkMax(7, MotorType.kBrushless);
  public static CANSparkMax shooterRight = new CANSparkMax(8, MotorType.kBrushless);
  public static CANSparkMax shooterBack = new CANSparkMax(9, MotorType.kBrushless);

  
  private static RelativeEncoder encoderLeft;
  private static RelativeEncoder encoderRight;
  private static RelativeEncoder encoderBack;
  private static SparkMaxPIDController pidControllerLeft;
  private static SparkMaxPIDController pidControllerBack;

  public static double kP;
  public static double kI;
  public static double kD;
  public static double kIz;
  public static double kFF;
  public static double kMaxOutput;
  public static double kMinOutput;
  public static double maxRPM;
  
  public ShooterSubsystem() {
    shooterLeft.restoreFactoryDefaults();
    shooterRight.restoreFactoryDefaults();
    shooterBack.restoreFactoryDefaults();

    shooterRight.follow(shooterLeft);
    shooterRight.setInverted(true);

    shooterLeft.setIdleMode(IdleMode.kCoast);
    shooterRight.setIdleMode(IdleMode.kCoast);
    shooterBack.setIdleMode(IdleMode.kCoast);
    
    
    pidControllerLeft = shooterLeft.getPIDController();
    pidControllerBack = shooterBack.getPIDController();
    
    encoderLeft = shooterLeft.getEncoder();
    encoderRight = shooterRight.getEncoder();
    encoderBack = shooterBack.getEncoder();
    
  
    

    // PID coefficients
    kP = 0.0003; //.0004
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000015; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 6000; //5800

    // set PID coefficients

    
    pidControllerLeft.setP(kP);
    pidControllerLeft.setI(kI);
    pidControllerLeft.setD(kD);
    pidControllerLeft.setIZone(kIz);
    pidControllerLeft.setFF(kFF);
    pidControllerLeft.setOutputRange(kMinOutput, kMaxOutput);

    pidControllerBack.setP(kP);
    pidControllerBack.setI(kI);
    pidControllerBack.setD(kD);
    pidControllerBack.setIZone(kIz);
    pidControllerBack.setFF(kFF);
    pidControllerBack.setOutputRange(kMinOutput, kMaxOutput);
    

    
    // display PID coefficients on SmartDashboard
    
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    
  }

  @Override
  public void periodic() {
    
  }

  public static void joystickShoot(Joystick stick)
  {

    double slideVal = (-stick.getRawAxis(3)+1)/-2;
    
    if(stick.getRawButton(1))
    {
      shooterLeft.setVoltage(slideVal*12);
      shooterRight.setVoltage(-slideVal*12);   
      shooterBack.setVoltage(slideVal*12*.85);   

    }
    else 
    {
      shooterLeft.setVoltage(0);
      shooterRight.setVoltage(0); 
      shooterBack.setVoltage(0);   

    }
    
    SmartDashboard.putNumber("sliderValue", slideVal);
    SmartDashboard.putNumber("encoderRight", encoderRight.getVelocity());
    SmartDashboard.putNumber("encoderLeft", encoderLeft.getVelocity());
    SmartDashboard.putNumber("encoderBack", encoderBack.getVelocity());
    
  }

  public static void joystickShootPID(Joystick stick) 
  {
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

    if((p != kP)) { pidControllerBack.setP(p); kP = p; }
    if((i != kI)) { pidControllerBack.setI(i); kI = i; }
    if((d != kD)) { pidControllerBack.setD(d); kD = d; }
    if((iz != kIz)) { pidControllerBack.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { pidControllerBack.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      pidControllerBack.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }


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


    double slideVal = (-stick.getRawAxis(3)+1)/-2;
    double setPoint = slideVal*maxRPM;
    
    if(stick.getRawButton(1))
    {
      pidControllerLeft.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
      pidControllerBack.setReference(setPoint*.8, CANSparkMax.ControlType.kVelocity);  

    }
    else 
    {
      pidControllerLeft.setReference(0, CANSparkMax.ControlType.kVelocity);
      pidControllerBack.setReference(0, CANSparkMax.ControlType.kVelocity); 

    }
    
    
    SmartDashboard.putNumber("setPoint", setPoint);
    SmartDashboard.putNumber("encoderRight", encoderRight.getVelocity());
    SmartDashboard.putNumber("encoderLeft", encoderLeft.getVelocity());
    SmartDashboard.putNumber("encoderBack", encoderBack.getVelocity());
    
  }

  @Override
  public void simulationPeriodic() {
  }


}
