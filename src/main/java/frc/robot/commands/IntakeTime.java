// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeTime extends CommandBase {
  private final IntakeSubsystem m_intake;
  private final ShooterSubsystem m_shoot;
  private final double intakeSpeed;
  private Timer timer;
  private final double endTime;

  public IntakeTime(double time, double intakingSpeed, IntakeSubsystem intakeSub, ShooterSubsystem shootSub) {
    endTime = time;
    intakeSpeed = intakingSpeed;
    m_intake = intakeSub;
    m_shoot = shootSub;
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {
    
    timer = new Timer();
    timer.reset();
    timer.start();

  }

  @Override
  public void execute() {
    m_intake.rotateIntake(false, true, false, false, false, false);
    if(timer.get() > 1 && timer.get() < endTime) { 
      m_intake.IntakeManual(true,false,intakeSpeed); 
      m_shoot.magazineManual(.5);
    }  
    if(timer.get() > endTime){
      m_intake.IntakeManual(true,false,0);
      m_shoot.magazineManual(-.25);

    }

  }

  @Override
  public void end(boolean interrupted) {
    m_intake.IntakeManual(true,false,0);
    m_shoot.magazineManual(0);

  }

  @Override
  public boolean isFinished() {
    return timer.get()>endTime+0.5;
  }
}