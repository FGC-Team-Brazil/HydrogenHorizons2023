package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveTrain{
	private DcMotor LeftMotor;
	private DcMotor RightMotor;

	private ElapsedTime runtime = new ElapsedTime();
	public final boolean CALIBRATION = false;


	public double[] samples = new double[100];
	public double positionDifference = 0;
	public double timestampDriveTrain = 1;
	
	public void initialize(){
		LeftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
		RightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
		
		RightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		LeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}
	
	public void execute(){
	
	
	}
}


