
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
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class IntakeSubsystem extends SubsystemBase {

    public WPI_TalonFX pivot = new WPI_TalonFX(5);
    public WPI_TalonFX pivotFollow = new WPI_TalonFX(12);
    public RelativeEncoder pivotEncoder;
    public CANSparkMax intake = new CANSparkMax(6,MotorType.kBrushed); 
    double angle = 0;
    boolean automaticControl = true;

    public IntakeSubsystem() {
        pivotFollow.follow(pivot);
        pivotFollow.setInverted(true);

        pivot.setNeutralMode(NeutralMode.Brake);
        //pivotFollow.setIdleMode(IdleMode.kBrake);

        pivotEncoder.setPosition(0);
        angle = 0;

    }

    public void rotateIntake(Boolean pivotUp, Boolean pivotDown, Boolean pivotUpManual, Boolean pivotDownManual, Boolean pivotHeld, Boolean pivotHeldReleased)
    {
        if(pivotUp || pivotHeld){ angle = -1; }
        if(pivotDown || pivotHeldReleased){ angle = 12.0; }
        
        SmartDashboard.putNumber("intakePivotEncoder", pivotEncoder.getPosition());

        if(pivotUpManual || pivotDownManual){automaticControl = false;}
        else if(pivotUp || pivotDown || pivotHeld){automaticControl = true;}

        if(automaticControl){
            if(pivotEncoder.getPosition() < angle - 0.5){pivot.setVoltage(.1*12);}
            else if(pivotEncoder.getPosition() > angle + 0.5){pivot.setVoltage(-.2*12);}
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
