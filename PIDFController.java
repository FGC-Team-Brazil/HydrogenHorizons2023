package org.firstinspires.ftc.teamcode;


public class PIDFController {
	float KP, KI, KD, KF;
	double integralSum, iRange, lastError;
	
	public PIDFController(float KP, float KI, float KD, float KF, float iRange){
		this.KP = KP;
		this.KI = KI;
		this.KD = KD;
		this.KF = KF;
		
		this.integralSum = 0;
		this.iRange = iRange;
		this.lastError = 0;
	}
	
	public double calculate(double setPoint, double velocity, double deltaTime){
		double error = setPoint - velocity;
		
		if(error < iRange){
			integralSum += error*deltaTime;
		}
		else{
			integralSum = 0;
		}
		
		double derivative = (error-lastError)/deltaTime;
		lastError = error;
		double output = (error*KP) + (integralSum*KI) + (derivative*KD) + (setPoint*KF);
		
		return output;
	}
	
	
}
