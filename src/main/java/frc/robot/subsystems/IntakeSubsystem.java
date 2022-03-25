// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

public class IntakeSubsystem extends SubsystemBase {

    public CANSparkMax pivot = new CANSparkMax(5, MotorType.kBrushless);
    //public CANSparkMax pivotFollow = new CANSparkMax(12, MotorType.kBrushless);
    public RelativeEncoder pivotEncoder;
    public CANSparkMax intake = new CANSparkMax(6,MotorType.kBrushless); 
    double angle = 0;
    boolean automaticControl = true;

    public IntakeSubsystem() {
        //pivotFollow.follow(pivot, true);

        pivot.setIdleMode(IdleMode.kBrake);
        //pivotFollow.setIdleMode(IdleMode.kBrake);

        pivotEncoder = pivot.getEncoder();

        pivotEncoder.setPosition(0);
        angle = 0;

    }

    public void rotateIntake(Boolean pivotUp, Boolean pivotDown, Boolean pivotUpManual, Boolean pivotDownManual)
    {
        if(pivotUp){ angle = 0; }
        if(pivotDown){ angle = 5.9; }
        
        SmartDashboard.putNumber("intakePivotEncoder", pivotEncoder.getPosition());

        if(pivotUpManual || pivotDownManual){automaticControl = false;}
        else if(pivotUp || pivotDown){automaticControl = true;}

        if(automaticControl){
            if(pivotEncoder.getPosition() < angle - 0.5){pivot.setVoltage(.3*12);}
            else if(pivotEncoder.getPosition() > angle + 0.5){pivot.setVoltage(-.3*12);}
            else{pivot.setVoltage(0);}     
        }
        else{
            if(pivotUpManual){pivot.setVoltage(0.3*12);}
            else if(pivotDownManual){pivot.setVoltage(-.3*12);}
            else{pivot.setVoltage(0);}
        }

    }
    public void IntakeManual(Boolean intakeIn, Boolean intakeOut, double speed)
    {
        //double speed = .4;

        if(intakeIn){
            intake.setVoltage(-speed*12);
        }
        else if(intakeOut){
            intake.setVoltage(speed*12);
        }
        else{
            intake.setVoltage(0);
        }
    }

    @Override
    public void periodic() {
    }

    @Override
    public void simulationPeriodic() {
    }
}
