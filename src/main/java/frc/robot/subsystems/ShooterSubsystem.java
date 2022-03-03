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
  public CANSparkMax shooterLeft = new CANSparkMax(7, MotorType.kBrushless);
  public CANSparkMax shooterRight = new CANSparkMax(8, MotorType.kBrushless);
  
  private RelativeEncoder encoderLeft;
  private RelativeEncoder encoderRight;
  private SparkMaxPIDController pidControllerLeft;

  public double kP;
  public double kI;
  public double kD;
  public double kIz;
  public double kFF;
  public double kMaxOutput;
  public double kMinOutput;
  public double maxRPM;
  
  public ShooterSubsystem() {
    shooterLeft.restoreFactoryDefaults();
    shooterRight.restoreFactoryDefaults();

    shooterRight.follow(shooterLeft, true);

    shooterLeft.setIdleMode(IdleMode.kCoast);
    shooterRight.setIdleMode(IdleMode.kCoast);
    
    pidControllerLeft = shooterLeft.getPIDController();
    
    encoderLeft = shooterLeft.getEncoder();
    encoderRight = shooterRight.getEncoder();

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

  public void joystickShoot(Joystick stick)
  {

    double slideVal = (-stick.getRawAxis(3)+1)/-2; // TODO: Fix this math
    
    if(stick.getRawButton(1))
    {
      shooterLeft.setVoltage(slideVal*12);

    }
    else 
    {
      shooterLeft.setVoltage(0);

    }
    
    SmartDashboard.putNumber("sliderValue", slideVal);
    SmartDashboard.putNumber("encoderRight", encoderRight.getVelocity());
    SmartDashboard.putNumber("encoderLeft", encoderLeft.getVelocity());
    
  }

  public void joystickShootPID(Joystick stick) 
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

    }
    else 
    {
      pidControllerLeft.setReference(0, CANSparkMax.ControlType.kVelocity);

    }
    
    
    SmartDashboard.putNumber("setPoint", setPoint);
    SmartDashboard.putNumber("encoderRight", encoderRight.getVelocity());
    SmartDashboard.putNumber("encoderLeft", encoderLeft.getVelocity());
    
  }

  @Override
  public void simulationPeriodic() {
  }


}
