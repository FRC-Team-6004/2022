// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class DriveTrainSubsystem extends SubsystemBase {
  public DriveTrainSubsystem() {}

  public static WPI_TalonFX Left1 = new WPI_TalonFX(1);
  public static WPI_TalonFX Left2 = new WPI_TalonFX(2);
  public static WPI_TalonFX Left3 = new WPI_TalonFX(3);
  public static MotorControllerGroup Left = new MotorControllerGroup(Left1, Left2, Left3);
  public static WPI_TalonFX Right1 = new WPI_TalonFX(4);
  public static WPI_TalonFX Right2 = new WPI_TalonFX(5);
  public static WPI_TalonFX Right3 = new WPI_TalonFX(6);
  public static MotorControllerGroup Right = new MotorControllerGroup(Right1, Right2, Right3);
  public static DifferentialDrive difDrive = new DifferentialDrive(Left, Right);

  
 
  @Override
  public void periodic() {
  }
  public static void drive()
  {
    Right.setInverted(true);
    Left.setInverted(false);

  }
  public static void joystickDrive(Joystick stick, double speed)
  {
    difDrive.arcadeDrive(stick.getY()*speed, stick.getTwist()*speed);
  }

  public static void xboxControllerDrive(XboxController controller, double speed) 
  {
    difDrive.tankDrive(controller.getLeftY()*speed, -controller.getRightY()*speed);
  }
  public void driveTank(double leftSpeed, double rightSpeed) 
  {
    difDrive.tankDrive(leftSpeed, -rightSpeed);
  }
  @Override
  public void simulationPeriodic() {
  } 


}

