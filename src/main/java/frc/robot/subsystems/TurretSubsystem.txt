// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class TurretSubsystem extends SubsystemBase {

    public static CANSparkMax turret = new CANSparkMax(10, MotorType.kBrushless); 

    public TurretSubsystem() {}

    public void rotateTurret(float speed)
    {
        turret.setVoltage(speed*12);
    }
    public static void rotateTurretManual(XboxController controller, double speed)
    {
        //double MaxV = 11000/133.3; //12v

        if(controller.getXButtonPressed()){
            turret.setVoltage(speed*12);
        }
        else if(controller.getBButtonPressed()){
            turret.setVoltage(-speed*12);
        }
        if(controller.getXButtonReleased() || controller.getBButtonReleased()){
            turret.setVoltage(0);
        }
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
