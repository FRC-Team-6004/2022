package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Autonomous extends SequentialCommandGroup {
  /**
  * Add your docs here.
  */
  public Autonomous() {
    addCommands(
      new ShootTime(.65,5),
      new Delay(1),
      new DriveDistance(12)
    );
  }

  public final void addCommands(ShootTime shootTime, Delay delay, DriveDistance driveDistance) {
    //figuring out what to do here
  }


}