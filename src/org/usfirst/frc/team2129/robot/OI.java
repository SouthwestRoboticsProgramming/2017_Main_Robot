package org.usfirst.frc.team2129.robot;

import org.usfirst.frc.team2129.robot.commands.IndexedCameraCommand;
import org.usfirst.frc.team2129.robot.commands.auto.AutoGearAlignmentCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI implements Buttons {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public Joystick        leftJoystick        = new Joystick(1);
	public Joystick        rightJoystick       = new Joystick(0);
	
	public OI(){
		Command autoAlignCommand = new AutoGearAlignmentCommand();
		new JoystickButton(leftJoystick, AUTO_ALIGN_BUTTON).whileHeld(autoAlignCommand);
//		new JoystickButton(rightJoystick, AUTO_CLIMB_BUTTON).whenPressed(new FullAutoClimbCommand());
//		new JoystickButton(leftJoystick, SHIFTER_BUTTON).whenPressed(new FastForwardCommand());
		
//		new JoystickButton(rightJoystick, 1).whileHeld(new ManualClimbCommand());
		//new JoystickButton(leftJoystick, 8).cancelWhenPressed(autoAlignCommand);		
		//Not working for some reason
//		Command climb = new FullAutoClimbCommand( );
//		Button lbtn1 = new JoystickButton(rightJoystick, AUTO_CLIMB_BUTTON);
//		Button rbtn1 = new JoystickButton(leftJoystick, AUTO_CLIMB_BUTTON);
//		lbtn1.toggleWhenActive(climb);
//		rbtn1.toggleWhenActive(climb);
		
		new JoystickButton(leftJoystick, CAMERA_FORWARD_BUTTON).whenPressed(new IndexedCameraCommand("Front", false));
		new JoystickButton(leftJoystick, CAMERA_MIDDLE_BUTTON).whenPressed(new IndexedCameraCommand("Middle", false));
		new JoystickButton(leftJoystick, CAMERA_BACK_BUTTON).whenPressed(new IndexedCameraCommand("Back", true));
		
		//???  Does having 2 commands wired to the same button cause issues?  Should be Command Group??
//		new JoystickButton(leftJoystick, 7).whenPressed(new SetForward());
//		new JoystickButton(leftJoystick, 9).whenPressed(new SetForward());
//		new JoystickButton(leftJoystick, 11).whenPressed(new SetReversed());
		 
//		new JoystickButton(leftJoystick, 11).whenPressed(new CalibrateAndMoveGear());
//		new JoystickButton(leftJoystick, 10).whenPressed(new AlignGearForPlacementCommand());
//		Command bdm = new AutoOrientCommand(0, 2, 0, true);
//		new JoystickButton(leftJoystick, 8).whenPressed(bdm);
//		new JoystickButton(leftJoystick, 9).cancelWhenPressed(bdm);
	}
	
}
