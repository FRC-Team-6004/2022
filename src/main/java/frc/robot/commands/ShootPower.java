// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootPower extends CommandBase {
  private final ShooterSubsystem m_shooter;
  private final double shootSpeed;
  private final double magSpeed;
  private Timer timer;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public ShootPower(double shooterSpeed, double magazineSpeed, ShooterSubsystem shooterSub) {
    shootSpeed = shooterSpeed;
    magSpeed = magazineSpeed;
    m_shooter = shooterSub;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_shooter.shoot(shootSpeed);
    timer = new Timer();
    timer.reset();
    timer.start();

  }

  @Override
  public void execute() {
    m_shooter.shoot(shootSpeed);
    if(timer.get()>2){
      m_shooter.magazineManual(magSpeed);
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.shoot(0);
  }

  @Override
  public boolean isFinished() {
    return timer.get()>4;
  }
}