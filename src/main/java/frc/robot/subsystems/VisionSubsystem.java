// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

public class VisionSubsystem extends SubsystemBase {
  public     final double cameraHeight = Units.inchesToMeters(24); //change when measured
  public final double targetHeight = Units.feetToMeters(8.3);
  public final double cameraPitch = Units.degreesToRadians(0);

  PhotonCamera camera = new PhotonCamera("gloworm");

  public double distanceToTarget;
  public static double yaw;

  public void getDistance()
  {
    var result = camera.getLatestResult();

    SmartDashboard.putNumber("targetCount", result.getTargets().size());
    
    SmartDashboard.putBoolean("hasTargets", result.hasTargets());

    if (result.hasTargets()) {
      distanceToTarget = 
            PhotonUtils.calculateDistanceToTargetMeters(
              cameraHeight, 
              targetHeight, 
              cameraPitch, 
              Units.degreesToRadians(result.getBestTarget().getPitch()));
      SmartDashboard.putNumber("distance", distanceToTarget);
    }
  }

  public void getYaw() {
    
    var result = camera.getLatestResult();

    if (result.hasTargets()) {
      yaw = result.getBestTarget().getYaw();
      SmartDashboard.putNumber("yaw", yaw);
    }
  }
  
  public static double aim() {
    double turn = 0;
    if(yaw > 25){turn = 1;}
    else if(yaw < -25){turn = -1;}
    else if(yaw > 12.5){turn = .75;}
    else if(yaw < -12.5){turn = -.75;}
    else if(yaw > 6.25){turn = .5;}
    else if(yaw < -6.25){turn = -.5;}
    else if(yaw > 3.125){turn = .25;}
    else if(yaw < -3.125){turn = -.25;}
    else if(yaw > 1.5625){turn = .15;}
    else if(yaw < -1.5625){turn = -.15;}
    else{turn = 0;}
    return turn;

  }
  
  public VisionSubsystem() {}
 
  @Override
  public void periodic() {

  }
  @Override
  public void simulationPeriodic() {
  } 


}

