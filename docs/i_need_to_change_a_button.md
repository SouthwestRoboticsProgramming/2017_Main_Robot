Hopefully, you can just edit the `org.usfirst.frc.team2129.robot.OI` file. In that file, the buttons are indexed from 1, and should be labeled on the joystick. In the USB tab of the DriverStation you should be able to debug the button your pressing.

If your unlucky and it's not listed, dig around in the relevant commands, and it'll be in a `.getRawButton(n)` clause. Just change the number to what it should be.

When done, redeploy with Ctrl-B