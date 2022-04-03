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
import pabeles.concurrency.ConcurrencyOps.Reset;
public class Autonomous extends SequentialCommandGroup {

  public Autonomous(DriveTrainSubsystem driveSub, ShooterSubsystem shootSub, IntakeSubsystem intakeSub) {

    addCommands(
      new ResetGyro(driveSub),
      /*
      new ShootPower(3,.1,.4,shootSub),
      new Delay(0.1),
      */
      //new DriveRotateDistance(170,.5,driveSub),
      new Delay(0.1),
      parallel(
        new DriveDistance(50,.35,driveSub),//go to ball (40 inches)
        new IntakeTime(5, .35, intakeSub, shootSub)
      ),
      new Delay(0.1),
      new DriveRotateDistance(165,.5,driveSub),
      new Delay(0.1),
      new DriveDistance(36,.5,driveSub),
      new Delay(0.1),
      new ShootPower(6,.1,.4,shootSub)
      
    );
  }


}