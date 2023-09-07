package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.Arrays;
import java.lang.reflect.Array;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.DriveTrain;


@TeleOp
public class TeleOpMode extends LinearOpMode {
	private DcMotor LeftMotor;
	private DcMotor RightMotor;
	private DcMotor ClimberMotor;
	private DcMotor IntakeMotor;
	
	private ElapsedTime runtime = new ElapsedTime();
	public final boolean CALIBRATION = false;


	public double[] samples = new double[100];
	public double positionDifference = 0;
	public double timestampDriveTrain = 1;
	
	
	@Override
	public void runOpMode() {
		LeftMotor = hardwareMap.get(DcMotor.class, "LeftMotor");
		RightMotor = hardwareMap.get(DcMotor.class, "RightMotor");
		ClimberMotor = hardwareMap.get(DcMotor.class, "Climber");
		IntakeMotor = hardwareMap.get(DcMotor.class, "Intake");
		
		RightMotor.setDirection(DcMotor.Direction.REVERSE);
		
		RightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		LeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		
		RightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		LeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		
		
		telemetry.addData("Status", "Initialized");
		
		
		telemetry.update();
		// Wait for the game to start (driver presses PLAY)
		waitForStart();
		
		// run until the end of the match (driver presses STOP)
		runtime.reset();
		
		
		while (opModeIsActive()) {
			driveTrain();
			climber();
			intake();
			
			telemetry.update();
			}
		}
		
	public void intake(){
		if(gamepad1.left_bumper){
			IntakeMotor.setPower(1);
		}else{
			IntakeMotor.setPower(0);
		}
	}
	
	public void climber(){
		double climb = gamepad1.left_trigger - gamepad1.right_trigger;
		ClimberMotor.setPower(climb);
	}
		
		
	public void driveTrain(){
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

		samples[samples.length-1] = Math.abs(deltaPositionDifference) / Math.abs(deltaTime);

		popFirstSample(samples);

		telemetry.addData("Error velocity", averageArray(samples));
		//telemetry.addData("Samples", Arrays.toString(samples));
		telemetry.addData("controller", leftAxis);
		
		telemetry.addData("RightPosition", rightMotorPosition);
		telemetry.addData("LeftPosition", leftMotorPosition);
		telemetry.addData("Difference", positionDifference);
		telemetry.addData("Constant", Math.min(Math.abs(leftMotorPosition), Math.abs(rightMotorPosition))/Math.max(Math.abs(leftMotorPosition), Math.abs(rightMotorPosition)));
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
			LeftMotor.setPower(leftSpeed*0.985525);
			RightMotor.setPower(rightSpeed*0.9801511596);
			telemetry.addData("Direction", "Forward");
		}else{
			LeftMotor.setPower(leftSpeed);
			RightMotor.setPower(rightSpeed*0.9835*0.99);
			telemetry.addData("Direction", "Backward");
		}
	}
	public void popFirstSample(double[] arr){
		for (int i = 0; i < arr.length-1; i++){
			arr[i] = arr[i+1];
		}
	}
	public double averageArray(double[] arr){
		double sum = 0;
		for (int i = 0; i < arr.length; i++){
			sum+=arr[i];
		}
		return sum/arr.length;
	}
}

