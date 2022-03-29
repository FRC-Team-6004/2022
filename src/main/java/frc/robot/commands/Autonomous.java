package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autonomous extends SequentialCommandGroup {

  public Autonomous(DriveTrainSubsystem driveSub, ShooterSubsystem shootSub) {

    addCommands(
      new ShootTime(5, .5, shootSub),
      new Delay(5),
      new DriveDistance(500,-.5,driveSub)
    );
  }


}