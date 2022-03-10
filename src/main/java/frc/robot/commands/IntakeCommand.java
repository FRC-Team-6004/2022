// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_subsystem;

  boolean pivotUp = false;
  boolean pivotDown = false;
  boolean intakeIn = false;
  boolean intakeOut = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeCommand(IntakeSubsystem subsystem) {

    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    Joystick stick = RobotContainer.driveStick;

    if(stick.getRawButtonPressed(5)){pivotUp = true;}
    else if(stick.getRawButtonPressed(3)){pivotDown = true;}

    if(stick.getRawButtonReleased(5)){pivotUp = false;}
    else if(stick.getRawButtonReleased(3)){pivotDown = false;}

    if((stick.getRawButtonPressed(7)||stick.getRawButtonPressed(9))){intakeIn = true;}
    else if((stick.getRawButtonPressed(8)||stick.getRawButtonPressed(10))){intakeOut = true;}

    if((stick.getRawButtonReleased(7)||stick.getRawButtonReleased(9))){intakeIn = false;}
    else if((stick.getRawButtonReleased(8)||stick.getRawButtonReleased(10))){intakeOut = false;}

    SmartDashboard.putBoolean("intakeIn", intakeIn);
    SmartDashboard.putBoolean("intakeOut", intakeOut);

    m_subsystem.rotateIntake(pivotUp,pivotDown, (stick.getPOV() == 270), (stick.getPOV()== 90));
    m_subsystem.IntakeManual(intakeIn,intakeOut);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
