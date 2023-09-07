package org.firstinspires.ftc.teamcode;

import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Constants;


public class Climber implements Subsystem{
	private DcMotor Motor;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	
	public Climber(HardwareMap hardwareMap, Gamepad gamepad1){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
	}
	
	public void initialize(){
		this.Motor = hardwareMap.get(DcMotor.class, Constants.Motors.CLIMBER_1_PORT);
	}
	
	public void execute(){
		double climb = gamepad1.left_trigger - gamepad1.right_trigger;
		Motor.setPower(climb);
	}
}