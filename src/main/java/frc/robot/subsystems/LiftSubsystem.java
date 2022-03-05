// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//NOT IN USE

public class LiftSubsystem extends SubsystemBase {
 
    public CANSparkMax lift = new CANSparkMax(10, MotorType.kBrushless); 
    public RelativeEncoder liftEncoder;

    public LiftSubsystem() {
        liftEncoder = lift.getEncoder();
    }

    public void lift(float speed)
    {
        lift.setVoltage(speed*12);
    }
    public void liftManual(XboxController controller, double speed)
    {
        //double MaxV = 11000/133.3; //12v
        SmartDashboard.putNumber("intakePivotEncoder", liftEncoder.getPosition());

        if(controller.getXButtonPressed()){
            lift.setVoltage(speed*12);
        }
        else if(controller.getBButtonPressed()){
            lift.setVoltage(-speed*12);
        }
        if(controller.getXButtonReleased() || controller.getBButtonReleased()){
            lift.setVoltage(0);
        }
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
