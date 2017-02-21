package org.usfirst.frc.team2129.util.encoderdesc;

import org.usfirst.frc.team2129.util.encoderdesc.iencoder.IEncoder;
import org.usfirst.frc.team2129.util.encoderdesc.iencoder.QuadratureEncoder;

public class QuadratureEncoderDescriptor extends IEncoderDescriptor {
	private int p1, p2;

	public QuadratureEncoderDescriptor(int a, int b) {
		p1 = a;
		p2 = b;
	}

	protected IEncoder _get() {
		return new QuadratureEncoder(p1, p2);
	}

}
