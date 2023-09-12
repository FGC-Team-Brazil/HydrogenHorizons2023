package org.firstinspires.ftc.teamcode;

import java.util.Arrays;
import java.util.ArrayList;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.Subsystems.*;

@TeleOp
public class TeleOpMode extends LinearOpMode {
	private DcMotor IntakeMotor;
	
	public ArrayList<Subsystem> Subsystems;

	public DriveTrain driveTrain;
	public Climber climber;
	public Intake intake;
	public Shooter shooter;
	
	@Override
	public void runOpMode() {

		this.Subsystems = new ArrayList<Subsystem>();
		
		this.driveTrain = new DriveTrain(hardwareMap, gamepad1, telemetry);
		this.climber = new Climber(hardwareMap, gamepad1, telemetry);
		this.intake = new Intake(hardwareMap, gamepad1, telemetry);
		this.shooter = new Shooter(hardwareMap, gamepad1, telemetry);
		Subsystems.add(driveTrain);
		Subsystems.add(climber);
		Subsystems.add(intake);
		Subsystems.add(shooter);
		
		Subsystems.forEach(Subsystem::initialize);
		
		telemetry.addData("Status", "Initialized");

		telemetry.update();

		waitForStart();
		
		while (opModeIsActive()) {
			Subsystems.forEach(Subsystem::execute);
	
			telemetry.update();
		}
	}
}

