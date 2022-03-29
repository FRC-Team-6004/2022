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
      new ShootPower(.13, .25,shootSub),
      new Delay(2),
      new DriveRotateDistance(180,.35,driveSub),
      new Delay(1),
      new DriveDistance(42,.35,driveSub)//go to ball (40 inches)
    );
  }


}