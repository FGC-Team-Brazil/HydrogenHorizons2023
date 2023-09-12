package org.firstinspires.ftc.teamcode.Subsystems;

import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystem;


public class Climber implements Subsystem{
	private DcMotor Motor;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	Telemetry telemetry;
	
	public Climber(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
		this.telemetry = telemetry;
		
	}
	
	public void initialize(){
		this.Motor = hardwareMap.get(DcMotor.class, Constants.Motors.CLIMBER_1_PORT);
	}
	
	public void execute(){
		double climb = gamepad1.left_trigger - gamepad1.right_trigger;
		Motor.setPower(climb);
		//Motor.setPower(-0.9); -> Velocidade certa pro buffer
	}
}
