package org.usfirst.frc.team2129.robot;

import org.usfirst.frc.team2129.robot.commands.AutomatedClimbCommand;
import org.usfirst.frc.team2129.robot.commands.auto.AlignGearForPlacementCommand;
import org.usfirst.frc.team2129.robot.commands.auto.AutoGearAlignmentCommand;
import org.usfirst.frc.team2129.robot.commands.auto.CalibrateAndMoveGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
	
	
	
	public Joystick        leftJoystick        = new Joystick(0);
	public Joystick        rightJoystick       = new Joystick(1);
	public Joystick        thirdJoystick       = new Joystick(2);
	
	public OI(){
		new JoystickButton(rightJoystick, 2).whileHeld(new AutoGearAlignmentCommand());
		Command acc = new AutomatedClimbCommand();
		
		new JoystickButton(rightJoystick, 4).toggleWhenActive(acc);
		new JoystickButton(leftJoystick, 4).toggleWhenActive(acc);
		
		new JoystickButton(leftJoystick, 11).whenPressed(new CalibrateAndMoveGear());
		new JoystickButton(leftJoystick, 12).whenPressed(new AlignGearForPlacementCommand());
	}
	
}
