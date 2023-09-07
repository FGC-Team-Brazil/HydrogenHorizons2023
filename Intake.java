package org.firstinspires.ftc.teamcode;

import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake {
	private DcMotor Motor;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	
	public Intake(HardwareMap hardwareMap, Gamepad gamepad1){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
	}
	
	public void initialize(){
		this.Motor = hardwareMap.get(DcMotor.class, Constants.Motors.INTAKE_1_PORT);
	}
	
	public void execute(){
		if(gamepad1.left_bumper){
			Motor.setPower(1);
		}
	}
}
	