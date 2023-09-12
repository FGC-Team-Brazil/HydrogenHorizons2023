package org.firstinspires.ftc.teamcode;


public class Constants {
	
	public final class Motors{
		public static final String DRIVETRAIN_LEFT_PORT = "LeftMotor";
		public static final String DRIVETRAIN_RIGHT_PORT = "RightMotor";
		
		public static final String CLIMBER_1_PORT = "ClimberMotor";
		
		public static final String INTAKE_1_PORT = "IntakeMotor";
	
		public static final String SHOOTER_1_PORT = "ShooterMotor";
		
		public static final String BUFFER_1_PORT = "BufferMotor";
	
	}
	public final class DriveTrainCalibration{
		public static final double LEFT_FORWARD = 0.985525;
		public static final double RIGHT_FORWARD = 0.9801511596;
		public static final double LEFT_BACKWARD = 1;
		public static final double RIGHT_BACKWARD = 0.9835*0.99;		
	}
	public final class ShooterPIDF{
		public static final double VELOCITY_TARGET = 2000;
		
		// public static final double KP = 3;
		// public static final double KI = 0.9;
		// public static final double KD = 2;
		// public static final double KF = 1;
		
		public static final double KP = 10;
		public static final double KI = 5;
		public static final double KD = 5;
		public static final double KF = 4;
	}
		
}