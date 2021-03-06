// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveRotateDistance extends CommandBase {
  private final DriveTrainSubsystem m_drive;
  private final double m_degrees;
  private final double m_speed;
  private double initialDistance;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveRotateDistance(double degrees, double speed, DriveTrainSubsystem drive) {
    m_degrees = degrees;
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetDriveEncoder();
    m_drive.driveTank(m_speed, -m_speed);
    m_drive.brakeMode();
    initialDistance = 0;

  }

  @Override
  public void execute() {
    m_drive.driveTank(m_speed, -m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.driveTank(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (m_drive.getYaw()) > (m_degrees - 2.5)&&(m_drive.getYaw()) < (m_degrees + 2.5);
  }
}