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
  
  public ShooterSubsystem() {
    shooterLeft.restoreFactoryDefaults();
    shooterRight.restoreFactoryDefaults();

    shooterRight.follow(shooterLeft, true);

    shooterLeft.setIdleMode(IdleMode.kCoast);
    shooterRight.setIdleMode(IdleMode.kCoast);
        
    encoderLeft = shooterLeft.getEncoder();
    
  }

  @Override
  public void periodic() {
    
  }

  public void joystickShoot(Boolean low, Boolean high, Boolean shoot)
  {
    double lowPower = .4; //arbitrary
    double highPower = .8; //arbitrary
    if(low && shoot){shooterLeft.setVoltage(lowPower*12);}
    else if(high && shoot){shooterLeft.setVoltage(highPower*12);}

    SmartDashboard.putNumber("shooter encoder", encoderLeft.getVelocity());
    
  }

  @Override
  public void simulationPeriodic() {
  }


}
