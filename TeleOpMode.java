package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.ArrayList;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.DriveTrain;
import org.firstinspires.ftc.teamcode.Climber;

@TeleOp
public class TeleOpMode extends LinearOpMode {
	private DcMotor IntakeMotor;
	
	public ArrayList<Subsystem> Subsystems;

	public DriveTrain driveTrain;
	public Climber climber;
	public Intake intake;
	
	@Override
	public void runOpMode() {
		this.Subsystems = new ArrayList<Subsystem>();
		this.driveTrain = new DriveTrain(hardwareMap, gamepad1);
		this.climber = new Climber(hardwareMap, gamepad1);
		this.intake = new Intake(hardwareMap, gamepad1);
		Subsystems.add(driveTrain);
		Subsystems.add(climber);
		Subsystems.add(intake);

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

