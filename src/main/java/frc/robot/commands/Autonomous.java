package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
public class Autonomous extends SequentialCommandGroup {

  public Autonomous(DriveTrainSubsystem driveSub, ShooterSubsystem shootSub, IntakeSubsystem intakeSub) {

    addCommands(
      new ShootPower(4,.1,.5,shootSub),
      new Delay(0.5),
      new DriveRotateDistance(180,.4,driveSub),
      new Delay(0.5),
      parallel(
        new DriveDistance(42,.35,driveSub),//go to ball (40 inches)
        new IntakeTime(4, .4, intakeSub, shootSub)
      ),
      new DriveRotateDistance(180,-.4,driveSub),
      new Delay(0.5),
      new DriveDistance(42,.4,driveSub),
      new Delay(0.5),
      new ShootPower(4,.1,.5,shootSub),
      new Delay(0.5),
      new DriveDistance(42,-.4,driveSub)
      
    );
  }


}