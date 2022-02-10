// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveTrainCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  private final DriveTrainSubsystem driveTrainSubsystem;
  private final TurretSubsystem turretSubsystem;
  private final IntakeSubsystem intakeSubsystem;

  private final ExampleCommand m_autoCommand;
  private final ShooterCommand shooterCommand;
  private final DriveTrainCommand driveTrainCommand;
  private final TurretCommand turretCommand;
  private final IntakeCommand intakeCommand;

  public static Joystick driveStick;
  public static XboxController driveXboxController;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_exampleSubsystem = new ExampleSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    driveTrainSubsystem = new DriveTrainSubsystem();
    turretSubsystem = new TurretSubsystem();
    intakeSubsystem = new IntakeSubsystem();
  
    m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    shooterCommand = new ShooterCommand(shooterSubsystem);
    driveTrainCommand = new DriveTrainCommand(driveTrainSubsystem);
    turretCommand = new TurretCommand(turretSubsystem);
    intakeCommand = new IntakeCommand(intakeSubsystem);

    shooterCommand.addRequirements(shooterSubsystem);
    shooterSubsystem.setDefaultCommand(shooterCommand);
    driveTrainCommand.addRequirements(driveTrainSubsystem);
    driveTrainSubsystem.setDefaultCommand(driveTrainCommand);
    turretCommand.addRequirements(turretSubsystem);
    turretSubsystem.setDefaultCommand(turretCommand);
    intakeCommand.addRequirements(intakeSubsystem);
    intakeSubsystem.setDefaultCommand(intakeCommand);

    
    driveStick = new Joystick(0);
    // Configure the button bindings
    configureButtonBindings();

    driveXboxController = new XboxController(1);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
