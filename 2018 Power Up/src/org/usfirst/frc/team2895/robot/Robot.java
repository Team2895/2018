/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2895.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive; 

public class Robot extends IterativeRobot {
	private DifferentialDrive m_driveTrain;
	private Joystick m_driveJoyStick;
	private Joystick m_shooterJoyStick;
	private Spark m_intake;
	private Spark m_shooterR;
	private Spark m_shooterL;
	//private DifferentialDrive m_shooter;
	
	public class ButtonMap {
		public static final int A = 0;
		public static final int B = 1;
		public static final int X = 2;
		public static final int Y = 3;
		public static final int LB = 4;
		public static final int RB = 5;
		public static final int Back = 6;
		public static final int Start = 7;
		public static final int L3 = 8;
		public static final int R3 = 9;
	}
	
	@Override
	public void robotInit() {
		m_driveTrain = new DifferentialDrive(new Spark(0), new Spark(1));
		//m_intake = new Spark(2) ;
		m_shooterR = new Spark(7);
		m_shooterL = new Spark(8);
		
		//m_shooter = new DifferentialDrive(new Spark(7), new Spark(8));
		m_driveJoyStick = new Joystick(0);
		m_shooterJoyStick = new Joystick(1);
	}
	
	@Override
	public void disabledInit(){}
	
	@Override
	public void teleopInit() {
		
	}

	boolean AUTONOMOUS_COMPLETE = false;
	
	@Override
	public void autonomousPeriodic() {
		//super.autonomousPeriodic();
		if (AUTONOMOUS_COMPLETE) { return; }

		//  Disable so we can let run during Timer delay
		m_driveTrain.setSafetyEnabled(false);
		
	    // Drive forward for 4.2 seconds 
		double speed = 0.60;
		m_driveTrain.arcadeDrive(1.0*speed, 0);
		Timer.delay(5.5);
		
		m_driveTrain.arcadeDrive(0, 0);
		
		// Re-enable motor safety shut off
		m_driveTrain.setSafetyEnabled(true);
		
		// Full power shooter mechanism
		//m_shooterR.set(-1.0);
		//m_shooterL.set(-1.0);
		
		Timer.delay(10.0);
		
		// Stop shooter mechanism
		//m_shooterR.set(0);
		//m_shooterL.set(0);
		
		AUTONOMOUS_COMPLETE = true;
	}

	@Override
	public void teleopPeriodic() {
		m_driveTrain.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			
			// Drive arcade style
			m_driveTrain.arcadeDrive(-m_driveJoyStick.getY(), m_driveJoyStick.getX());
			
			// Intake
//			if(m_shooterJoyStick.getRawButton(ButtonMap.LB)) // window motor on
//			{
//				m_intake.setSpeed(0.2);
//			} else{
//				m_intake.setSpeed(0);
//			}
			
			m_shooterR.set(-1.0*m_shooterJoyStick.getY());
			m_shooterL.set(-1.0*m_shooterJoyStick.getY());
			
			//m_shooter.arcadeDrive(m_shooterJoyStick.getY(),m_shooterJoyStick.getX());
			
			//if(m_shooterJoyStick.getRawButton(ButtonMap.Y))
			//				m_intake.set(0.005);
			 //else {
			//m_intake.set(0.0);
			} 
			
			// The motors will be updated every 5ms
			Timer.delay(0.005);
		}
	}