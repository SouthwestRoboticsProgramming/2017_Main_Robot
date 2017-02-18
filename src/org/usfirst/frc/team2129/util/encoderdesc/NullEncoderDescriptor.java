package org.usfirst.frc.team2129.util.encoderdesc;

import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.NullIEncoder;

public class NullEncoderDescriptor extends IEncoderDescriptor {

	@Override
	protected IEncoder _get() {
		// TODO Auto-generated method stub
		return new NullIEncoder();
	}

}
