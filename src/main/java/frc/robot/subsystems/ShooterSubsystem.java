// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
<<<<<<< Updated upstream
  public CANSparkMax shooterLeft = new CANSparkMax(7, MotorType.kBrushless);
  public CANSparkMax shooterRight = new CANSparkMax(8, MotorType.kBrushless);
  public CANSparkMax magazine = new CANSparkMax(9, MotorType.kBrushless); //ID needed
=======
  public WPI_TalonFX shooter = new WPI_TalonFX(8);
  public CANSparkMax magazine = new CANSparkMax(7, MotorType.kBrushless); //ID needed
  public double shooterSpeed = 0;
  public double magazineSpeed = 0;
>>>>>>> Stashed changes
  
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
    shooter.setVoltage(shooterSpeed*12);
    magazine.setVoltage(magazineSpeed*12);
    
  }

  public void joystickShoot(Boolean low, Boolean high, Boolean shoot){
    double lowPower = .4; //arbitrary
    double highPower = .65; //arbitrary
<<<<<<< Updated upstream
    if(low && shoot){shooterLeft.setVoltage(-lowPower*12);}
    else if(high && shoot){shooterLeft.setVoltage(-highPower*12);}
    else{shooterLeft.setVoltage(0);}

    SmartDashboard.putNumber("shooter encoder", encoderLeft.getVelocity());
=======
    if(low && shoot){shooterSpeed=lowPower;}
    else if(high && shoot){shooterSpeed=highPower;}
    else{shooterSpeed=0;}
>>>>>>> Stashed changes
    
  }

  public void shoot(double speed){
<<<<<<< Updated upstream
    shooterLeft.setVoltage(-speed*12);
  }

  public void magazineManual(double speed){
    magazine.setVoltage(-speed*12);
=======
    shooterSpeed = speed;
  }

  public void magazineManual(double speed){
    magazineSpeed = speed;
>>>>>>> Stashed changes
  }

  @Override
  public void simulationPeriodic() {
  }


}
