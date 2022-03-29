package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class ShootTime extends CommandBase {

  private Timer timer;
  private double timeout;
  private double speed;
  private ShooterSubsystem shooterSubsystem;

  public ShootTime(double seconds, double shootSpeed, ShooterSubsystem shooter) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    timer = new Timer();
    timeout = seconds;
    shooterSubsystem = shooter;
    speed = shootSpeed;
  }

  public void startTimer() {
    timer.start();
    //expireTime = timeSinceInitialized() + timeout;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    startTimer();
    //shooterSubsystem.joystickShoot(false, true, true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    shooterSubsystem.joystickShoot(false, true, true);
    if(timer.get() > 1){
      shooterSubsystem.magazineManual(.4);
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return (timer.get() >= timeout);
  }

}