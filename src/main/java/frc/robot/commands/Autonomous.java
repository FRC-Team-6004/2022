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
      new ShootPower(3,.1,.6,shootSub),
      new Delay(0.1),
      new DriveRotateDistance(180,.6,driveSub),
      new Delay(0.1),
      parallel(
        new DriveDistance(42,.35,driveSub),//go to ball (40 inches)
        new IntakeTime(4, .4, intakeSub, shootSub)
      ),
      new DriveRotateDistance(180,-.6,driveSub),
      new Delay(0.1),
      new DriveDistance(42,.75,driveSub),
      new Delay(0.1),
      new ShootPower(3,.1,.6,shootSub),
      new Delay(0.1),
      new DriveDistance(42,-.75,driveSub)
      
    );
  }


}