// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterCommand(ShooterSubsystem subsystem) {
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
    Joystick drStick = RobotContainer.driveStick;
    Joystick opStick = RobotContainer.operatorStick;

    //XboxController cont = RobotContainer.driveXboxController;
    m_subsystem.joystickShoot(opStick.getRawButton(3),opStick.getRawButton(5), opStick.getRawButton(1), 0.4, 0.65); //switch .joystickShootPID to .joystickShoot for voltage
    double magazineSpeed;
    if((opStick.getRawButton(11)||opStick.getRawButton(9))){magazineSpeed = .5;}
    else if((opStick.getRawButton(12)||opStick.getRawButton(10))){magazineSpeed = -.5;}
    else{magazineSpeed=0;}

    m_subsystem.magazineManual(magazineSpeed);
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
