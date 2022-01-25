// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShooterSubsystem extends SubsystemBase {
  public ShooterSubsystem() {}

  public static CANSparkMax shooterLeft = new CANSparkMax(7, MotorType.kBrushless);
  public static CANSparkMax shooterRight = new CANSparkMax(8, MotorType.kBrushless);


  @Override
  public void periodic() {
    shooterRight.set(-shooterLeft.get());
  }

  public static void joystickShoot(Joystick stick)
  {
    double power = 0;
    if(stick.getRawButton(5) == true)
    {
      power = 1;
    }
    else if(stick.getRawButton(3) == true)
    {
      power = -1;
    }
    //power = power;
    shooterLeft.set(power);
  }

  @Override
  public void simulationPeriodic() {
  }


}
