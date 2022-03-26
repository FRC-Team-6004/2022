package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootTime extends Command {

private double expireTime;
private double timeout;
private ShooterSubsystem shooterSubsystem;
private RobotContainer robotContainer;
private double shootPower;

  public ShootTime(double power, double seconds) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    timeout = seconds;
    shootPower = power;
  }

  protected void startTimer() {
    expireTime = timeSinceInitialized() + timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    robotContainer = new RobotContainer();
    startTimer();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    robotContainer.shooterSubsystem.shoot(shootPower);
    if(timeSinceInitialized() > 1)
    {
      robotContainer.shooterSubsystem.magazineManual(.5);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (timeSinceInitialized() >= expireTime);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    robotContainer.shooterSubsystem.shoot(0);
    robotContainer.shooterSubsystem.magazineManual(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}