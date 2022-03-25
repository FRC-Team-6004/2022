package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup {
  /**
  * Add your docs here.
  */
  public Autonomous() {
    addSequential(new DriveDistance(0));
    addSequential(new Delay(0));

  }
}