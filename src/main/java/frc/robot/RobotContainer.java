// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.VisionCommand;
import frc.robot.subsystems.VisionSubsystem;
/*
import frc.robot.commands.DriveTrainCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.LiftCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LiftSubsystem;
*/

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleCommand m_autoCommand;
  private final ExampleSubsystem m_exampleSubsystem;
  private final VisionCommand visionCommand;
  private final VisionSubsystem visionSubsystem;
  
  /*
  private final ShooterSubsystem shooterSubsystem;
  private final DriveTrainSubsystem driveTrainSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  private final LiftSubsystem liftSubsystem;

  private final ShooterCommand shooterCommand;
  private final DriveTrainCommand driveTrainCommand;
  private final IntakeCommand intakeCommand;
  private final LiftCommand liftCommand;

  */
  public static Joystick driveStick;
  public static XboxController driveXboxController;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_exampleSubsystem = new ExampleSubsystem();
    m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    visionSubsystem = new VisionSubsystem();
    visionCommand = new VisionCommand(visionSubsystem);
    
    visionCommand.addRequirements(visionSubsystem);
    visionSubsystem.setDefaultCommand(visionCommand);

    /*
    shooterSubsystem = new ShooterSubsystem();
    driveTrainSubsystem = new DriveTrainSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    liftSubsystem = new LiftSubsystem();
  
    shooterCommand = new ShooterCommand(shooterSubsystem);
    driveTrainCommand = new DriveTrainCommand(driveTrainSubsystem);
    intakeCommand = new IntakeCommand(intakeSubsystem);
    liftCommand = new LiftCommand(liftSubsystem);

    shooterCommand.addRequirements(shooterSubsystem);
    shooterSubsystem.setDefaultCommand(shooterCommand);
    driveTrainCommand.addRequirements(driveTrainSubsystem);
    driveTrainSubsystem.setDefaultCommand(driveTrainCommand);
    intakeCommand.addRequirements(intakeSubsystem);
    intakeSubsystem.setDefaultCommand(intakeCommand);
    liftCommand.addRequirements(liftSubsystem);
    liftSubsystem.setDefaultCommand(liftCommand);

    */
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
