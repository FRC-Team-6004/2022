package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class Delay extends CommandBase {

  private Timer timer;
  private double expireTime;
  private double timeout;

  public Delay(double seconds) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    timer = new Timer();
    timeout = seconds;
  }

  public void startTimer() {
    timer.start();
    //expireTime = timeSinceInitialized() + timeout;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    startTimer();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return (timer.get() >= timeout);
  }

}