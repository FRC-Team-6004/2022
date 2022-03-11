// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//NOT IN USE

public class LiftSubsystem extends SubsystemBase {
 
    public CANSparkMax lift = new CANSparkMax(10, MotorType.kBrushless); 
    public RelativeEncoder liftEncoder;

    double angle;
    boolean automaticControl;

    public LiftSubsystem() {
        liftEncoder = lift.getEncoder();

        liftEncoder.setPosition(0);
        angle = 0;
    }

    public void lift(float speed)
    {
        lift.setVoltage(speed*12);
    }
    public void liftControl(boolean liftUp, boolean liftDown, boolean liftUpManual, boolean liftDownManual)
    {
        double speed = 0.5;
        SmartDashboard.putNumber("liftEncoder", liftEncoder.getPosition());

        if(liftUp){ angle = 0; } //arbitrary
        //if(liftDown){ angle = 5.8; } //arbitrary

        if(liftUpManual || liftDownManual || liftDown){automaticControl = false;}
        else if(liftUp){automaticControl = true;}

        if(automaticControl){
            /*
            if(liftEncoder.getPosition() < angle - 0.5){lift.setVoltage(.1*12);}
            else if(liftEncoder.getPosition() > angle + 0.5){lift.setVoltage(-.1*12);}
            else{lift.setVoltage(0);}   
            */
        }
        else{
            if(liftUpManual){lift.setVoltage(speed*12);}
            else if(liftDownManual || liftDown){lift.setVoltage(-speed*12);}
            else{lift.setVoltage(0);}
        }
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
