// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/** An example command that uses an example subsystem. */
public class DriveDistance extends Command {

    private double distance;
    private RobotContainer robotContainer;
    private DriveTrainSubsystem driveTrainSubsystem;

    public DriveDistance(double inches) {
      //requires(Robot.driveTrainSubsystem);
      distance = inches;
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    robotContainer.driveTrainSubsystem.resetDriveEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    robotContainer.driveTrainSubsystem.arcadeDrive(0.0, 0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {

  }

  @Override
  public void interrupted() {

  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.driveTrainSubsystem.getDriveEncoderDistance() == distance;
  }
}