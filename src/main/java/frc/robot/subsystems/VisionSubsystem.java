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
  public final double cameraHeight = Units.inchesToMeters(24); //change when measured
  public final double targetHeight = Units.feetToMeters(8.3);
  public final double cameraPitch = Units.degreesToRadians(0);

  PhotonCamera camera = new PhotonCamera("photonvision");

  public double distanceToTarget;
  public double yaw;

  public void getDistance()
  {
    var result = camera.getLatestResult();

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

  
  public VisionSubsystem() {}
 
  @Override
  public void periodic() {

  }
  @Override
  public void simulationPeriodic() {
  } 


}

