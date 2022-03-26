// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
  public WPI_TalonFX shooter = new WPI_TalonFX(8);
  public CANSparkMax magazine = new CANSparkMax(7, MotorType.kBrushless); //ID needed
  
  private RelativeEncoder encoder;
  
  public ShooterSubsystem() {
    
  }

  @Override
  public void periodic() {
    
  }

  public void joystickShoot(Boolean low, Boolean high, Boolean shoot){
    double lowPower = .4; //arbitrary
    double highPower = .65; //arbitrary
    if(low && shoot){shooter.setVoltage(lowPower*12);}
    else if(high && shoot){shooter.setVoltage(highPower*12);}
    else{shooter.setVoltage(0);}
    
  }

  public void shoot(double speed){
    shooter.setVoltage(speed*12);
  }

  public void magazineManual(double speed){
    magazine.setVoltage(speed*12);
  }

  @Override
  public void simulationPeriodic() {
  }


}
