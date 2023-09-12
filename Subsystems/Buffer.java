package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Buffer implements Subsystem{
	DcMotor Motor;

	HardwareMap hardwareMap;
	Gamepad gamepad1;
	Telemetry telemetry;
	
	public Buffer(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
		this.telemetry = telemetry;
	}
	
	public void initialize(){
		Motor = hardwareMap.get(DcMotor.class, Constants.Motors.BUFFER_1_PORT);
	}
	public void execute(){
		if(gamepad1.right_bumper){
			Motor.setPower(0.6);
		}else{
			Motor.setPower(0);
		}
	}
}
