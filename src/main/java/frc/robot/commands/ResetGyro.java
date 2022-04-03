// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetGyro extends CommandBase {
  private final DriveTrainSubsystem m_drive;

  public ResetGyro(DriveTrainSubsystem drive) {
    m_drive = drive;

    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    m_drive.resetNavX();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}