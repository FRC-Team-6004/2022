// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class IntakeSubsystem extends SubsystemBase {

    public CANSparkMax pivot = new CANSparkMax(11, MotorType.kBrushless);
    public CANSparkMax pivotFollow = new CANSparkMax(12, MotorType.kBrushless);
    public RelativeEncoder pivotEncoder;
    public CANSparkMax intake = new CANSparkMax(13, MotorType.kBrushless); 
    public CANSparkMax intakeFollow = new CANSparkMax(14, MotorType.kBrushless);

    public IntakeSubsystem() {
        pivotFollow.follow(pivot, true);
        intakeFollow.follow(intake, true);

        pivotEncoder = pivot.getEncoder();

    }

    public void rotateIntake(XboxController controller)
    {
        double angle = 0;
        if(controller.getYButton()){ angle = 0; } //arbitrary
        if(controller.getBButton()){ angle = 20; } //arbitrary
        
        SmartDashboard.putNumber("intakePivotEncoder", pivotEncoder.getPosition());

        if(pivotEncoder.getPosition() > angle + 10){
            //pivot.setVoltage(.2*12);
        }
        if(pivotEncoder.getPosition() < angle - 10){
            //pivot.setVoltage(-.2*12);
        }        

    }
    public void IntakeManual(XboxController controller, double speed)
    {
        //double MaxV = 11000/133.3; //12v

        if(controller.getLeftBumperPressed()){
            intake.setVoltage(speed*12);
        }
        else if(controller.getRightBumperPressed()){
            intake.setVoltage(-speed*12);
        }
        if(controller.getLeftBumperPressed() || controller.getRightBumperPressed()){
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
