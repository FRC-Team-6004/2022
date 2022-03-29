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

  public double shooterSpeed = 0;
  public double magazineSpeed = 0;
  
  private RelativeEncoder encoder;
  
  public ShooterSubsystem() {
    
  }

  @Override
  public void periodic() {
    shooter.setVoltage(shooterSpeed*12);
    magazine.setVoltage(magazineSpeed*12);
    
  }

  public void joystickShoot(Boolean low, Boolean high, Boolean shoot){
    double lowPower = .4; //arbitrary
    double highPower = .65; //arbitrary

    if(low && shoot){shooterSpeed=lowPower;}
    else if(high && shoot){shooterSpeed=highPower;}
    else{shooterSpeed=0;}
  }

  public void shoot(double speed){
    shooterSpeed=speed;
  }

  public void magazineManual(double speed){
    magazineSpeed=speed;
  }

  @Override
  public void simulationPeriodic() {
  }


}
