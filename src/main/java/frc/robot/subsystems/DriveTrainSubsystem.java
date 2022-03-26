// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class DriveTrainSubsystem extends SubsystemBase {
  public DriveTrainSubsystem() {}

  public WPI_TalonFX Left1 = new WPI_TalonFX(2);
  public WPI_TalonFX Left2 = new WPI_TalonFX(4);
  public MotorControllerGroup Left = new MotorControllerGroup(Left1, Left2);
  public WPI_TalonFX Right1 = new WPI_TalonFX(1);
  public WPI_TalonFX Right2 = new WPI_TalonFX(3);
  public MotorControllerGroup Right = new MotorControllerGroup(Right1, Right2);
  public DifferentialDrive difDrive = new DifferentialDrive(Left, Right);

  double speedFactor;
  boolean enableManual;

  @Override
  public void periodic() {
  }
  public void drive()
  {

  }
  public void joystickDrive(Double speed, Double turn, Boolean fast, Boolean vision){ 
    Right.setInverted(true);
    Left.setInverted(false);


    if(vision){enableManual = false;}
    else{enableManual = true;}
    if(enableManual){
      if(fast){speedFactor = -0.8;}
      else{speedFactor = -0.55;}

      difDrive.arcadeDrive(speed*speedFactor, -turn*speedFactor);
    }
    if(vision){
      speed = .6;
      difDrive.tankDrive(VisionSubsystem.aim(),-VisionSubsystem.aim());
    }
  }
  public void driveTank(double leftSpeed, double rightSpeed) 
  {
    difDrive.tankDrive(leftSpeed, -rightSpeed);
  }
  public void alignToTarget() {
    
  }
  @Override
  public void simulationPeriodic() {
  }
  public void resetDriveEncoder() {
  }
  public void arcadeDrive(double d, double e) {
  }
public double getDriveEncoderDistance() {
    return 0;
} 


}

