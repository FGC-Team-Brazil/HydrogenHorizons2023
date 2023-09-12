package org.firstinspires.ftc.teamcode.Subsystems;

import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystem;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake implements Subsystem{
	private DcMotor Motor;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	Telemetry telemetry;
	
	public Intake(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
		this.telemetry = telemetry;
	}
	
	public void initialize(){
		this.Motor = hardwareMap.get(DcMotor.class, Constants.Motors.INTAKE_1_PORT);
	}
	
	public void execute(){
		if(gamepad1.left_bumper){
			Motor.setPower(1);
		}else{
			Motor.setPower(0);
		}
	}
}
	
