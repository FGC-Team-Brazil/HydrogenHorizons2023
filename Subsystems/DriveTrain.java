package org.firstinspires.ftc.teamcode.Subsystems;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystem;

public class DriveTrain implements Subsystem{
	
	
	private DcMotor LeftMotor;
	private DcMotor RightMotor;

	private ElapsedTime runtime = new ElapsedTime();
	public final boolean CALIBRATION = false;


	public double[] samples = new double[100];
	public double positionDifference = 0;
	public double timestampDriveTrain = 1;
	
	HardwareMap hardwareMap;
	Gamepad gamepad1;
	Telemetry telemetry;
	
	public DriveTrain(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry){
		this.hardwareMap = hardwareMap;	
		this.gamepad1 = gamepad1;
		this.telemetry = telemetry;
	}	
	public void initialize(){
		telemetry.addData("Drivetrain", "sim");

		LeftMotor = hardwareMap.get(DcMotor.class, Constants.Motors.DRIVETRAIN_LEFT_PORT);
		RightMotor = hardwareMap.get(DcMotor.class, Constants.Motors.DRIVETRAIN_RIGHT_PORT);
		
		RightMotor.setDirection(DcMotor.Direction.REVERSE);
		
		RightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		LeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		
		RightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		LeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}
	
	public void execute(){
		double rightMotorPosition = RightMotor.getCurrentPosition();
		double leftMotorPosition = LeftMotor.getCurrentPosition();

		double deltaTime = runtime.seconds() - timestampDriveTrain;
		double deltaPositionDifference = (Math.abs(Math.abs(rightMotorPosition) - Math.abs(leftMotorPosition))) - positionDifference;
		
		positionDifference = Math.abs(Math.abs(rightMotorPosition) - Math.abs(leftMotorPosition));
		timestampDriveTrain = runtime.seconds();
		
		double leftAxis = gamepad1.left_stick_y;
		double rightAxis = -gamepad1.right_stick_x;
		
		if (!(Math.abs(leftAxis) > 0.3)){
			leftAxis = 0;
		}
		if (!(Math.abs(rightAxis) > 0.3)){
			rightAxis = 0;
		}
			
		if (positionDifference < 10000 && CALIBRATION){
			arcadeDrive(1,0);
			
		}else{
			arcadeDrive(leftAxis, rightAxis);
			
		}

		if (CALIBRATION){
			samples[samples.length-1] = Math.abs(deltaPositionDifference / deltaTime);
			popFirstSample(samples);
			
			telemetry.addData("Error velocity", averageArray(samples));
			telemetry.addData("RightPosition", rightMotorPosition);
			telemetry.addData("LeftPosition", leftMotorPosition);
			telemetry.addData("Difference", positionDifference);
			telemetry.addData("Constant", Math.min(Math.abs(leftMotorPosition), Math.abs(rightMotorPosition))/Math.max(Math.abs(leftMotorPosition), Math.abs(rightMotorPosition)));
		}
		
	
	}
	public void arcadeDrive(double xSpeed, double zRotation){
		xSpeed = Math.copySign(xSpeed*xSpeed, xSpeed);
		zRotation = Math.copySign(zRotation*zRotation, zRotation);
		
		double leftSpeed = xSpeed - zRotation;
		double rightSpeed = xSpeed + zRotation;
		
		double greaterInput = Math.max(Math.abs(xSpeed), Math.abs(zRotation));
		double lesserInput = Math.min(Math.abs(xSpeed), Math.abs(zRotation));
		
		if (greaterInput == 0){
			LeftMotor.setPower(0);
			RightMotor.setPower(0);
			return;
		}
		
		double saturatedInput = (greaterInput + lesserInput) / greaterInput;
		
		leftSpeed /= saturatedInput;
		rightSpeed /= saturatedInput;
		
		
		if (xSpeed < 0){
			LeftMotor.setPower(leftSpeed*Constants.DriveTrainCalibration.LEFT_FORWARD);
			RightMotor.setPower(rightSpeed*Constants.DriveTrainCalibration.RIGHT_FORWARD);
		}else{
			LeftMotor.setPower(leftSpeed*Constants.DriveTrainCalibration.LEFT_BACKWARD);
			RightMotor.setPower(rightSpeed*Constants.DriveTrainCalibration.RIGHT_BACKWARD);
		}
	}
	
	public void popFirstSample(double[] arr){
		for (int i = 0; i < arr.length-1; i++){
			arr[i] = arr[i+1];
		}
	}
	public double averageArray(double[] arr){
		return Arrays.stream(arr).average().orElse(0.0);
	}

}


