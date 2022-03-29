// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveDistance extends CommandBase {
  private final DriveTrainSubsystem m_drive;
  private final double m_distance;
  private final double m_speed;
  private double initialDistance;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveDistance(double units, double speed, DriveTrainSubsystem drive) {
    m_distance = units; //84000 units in 6 feet (72 inches) (1166.667 units in 1 inch)
    m_speed = speed;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.driveTank(m_speed, m_speed);
    m_drive.brakeMode();
    initialDistance = m_drive.getDriveEncoderDistance();

  }

  @Override
  public void execute() {
    m_drive.driveTank(m_speed, m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.driveTank(0, 0);
    
  }

  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getDriveEncoderDistance())+initialDistance >= m_distance * 1166.667;
  }
}