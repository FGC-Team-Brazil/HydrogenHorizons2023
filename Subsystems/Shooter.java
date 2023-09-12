package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystem;
import org.firstinspires.ftc.teamcode.Constants;

public class Shooter implements Subsystem{
	public DcMotorEx Motor;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	Telemetry telemetry;
	
	public Shooter(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
		this.telemetry = telemetry;
	}
	public void initialize(){
		Motor = hardwareMap.get(DcMotorEx.class, Constants.Motors.SHOOTER_1_PORT);
		

		Motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODERS, new PIDFCoefficients(Constants.ShooterPIDF.KP, Constants.ShooterPIDF.KI, Constants.ShooterPIDF.KD, Constants.ShooterPIDF.KF));
		Motor.setVelocityPIDFCoefficients(Constants.ShooterPIDF.KP, Constants.ShooterPIDF.KI, Constants.ShooterPIDF.KD, Constants.ShooterPIDF.KF);

	}
	public void execute(){
		telemetry.addData("ShooterPos", Motor.getVelocity());
		if(gamepad1.a){
			Motor.setVelocity(Constants.ShooterPIDF.VELOCITY_TARGET);
			telemetry.addData("Velocity error", Constants.ShooterPIDF.VELOCITY_TARGET - Motor.getVelocity());

		}else{
			Motor.setVelocity(0);
		}
	}
}





