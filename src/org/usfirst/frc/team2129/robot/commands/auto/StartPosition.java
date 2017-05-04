package org.usfirst.frc.team2129.robot.commands.auto;

public enum StartPosition  {
	LEFT,
	MIDDLE,
	RIGHT;
	
	public static StartPosition get(String startingPosition) {
		if (startingPosition == null || startingPosition.isEmpty())
			return MIDDLE;
		
		if (startingPosition.toLowerCase().startsWith("l"))
			return LEFT;
		
		if (startingPosition.toLowerCase().startsWith("r"))
			return RIGHT;
		
		return MIDDLE;
	}
	
	public boolean isLeft() {
		return this == LEFT;
	}
	
	public boolean isMiddle() {
		return this == MIDDLE;
	}
	
	public boolean isRight() {
		return this == RIGHT;
	}
}