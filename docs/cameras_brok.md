Cameras get magic internal ID numbers from the RoboRIO OS layer, and you can only find these out via the web interface. When you navigate to that, it'll show them as e.g. `cam0` and `cam1`. That number is waht is used in the config in the RobotMap file. An example of this is as follows:

```
public SortedMap<String, Integer> cameras;
String defaultCamera = "Climber Camera";
public TestRobotMap(){
	cameras=new TreeMap<String, Integer>();
	cameras.put(defaultCamera, 0);
	cameras.put("Front Camera", 1);
	cameras.put("Other Camera", 2);
}
```

so for example if you wanted to remove the 'Front Camera' you'd change it to 

```
public SortedMap<String, Integer> cameras;
String defaultCamera = "Climber Camera";
public TestRobotMap(){
	cameras=new TreeMap<String, Integer>();
	cameras.put(defaultCamera, 0);
	cameras.put("Other Camera", 2);
}
```

or if the "Climber Camera" got replaced and now was `cam3` you'd do

```
public SortedMap<String, Integer> cameras;
String defaultCamera = "Climber Camera";
public TestRobotMap(){
	cameras=new TreeMap<String, Integer>();
	cameras.put(defaultCamera, 3);
	cameras.put("Front Camera", 1);
	cameras.put("Other Camera", 2);
}
```

The `defaultCamera` is the camera that will be selected by default. You need at least one camera for it to work right (but you don't neccicarily need to watch the stream with RoboRealm or anyhting if you have bad enough IP traffic problems to have that matter.) The number is the number component of the `camX` ID, for example `cameras.put(defaultCamera, 0);` means call `cam0` "Climber Camera"

A common issue is that the camera server on the robot chokes on something after code is redeployed too many times without a reboot. In this case you just gotta reboot the RoboRIO (can be done via one of the tabs in the driver station.)
If for some reason you add or remove cameras and something breaks, it's probably that there's a limit on the RoboRIO as to the USB bandwith, and if you exceed that it can/will break (or if you remove some camera(s) and want to turn the resolution of the remaining ones up.) To modify the resolution/FPS settings, look in `org.usfirst.frc.team2129.robot.subsystems.CameraSubsystem.initCamera`, that contains the modesetting logic (as-is it just sets to the lowest availible mode, otherwise the controls get jerky.)