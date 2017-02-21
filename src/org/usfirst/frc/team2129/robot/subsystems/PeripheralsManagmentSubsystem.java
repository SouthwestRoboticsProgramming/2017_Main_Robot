package org.usfirst.frc.team2129.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PeripheralsManagmentSubsystem extends Team2129Subsystem {
	private Compressor compressor = new Compressor();
	private PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public PeripheralsManagmentSubsystem(){
		compressor.setClosedLoopControl(true);
	}
	
	protected void initDefaultCommand() {}

	public Compressor getCompressor() {
		return compressor;
	}

	public PowerDistributionPanel getPdp() {
		return pdp;
	}

	public void setDashboardValues() {
		setCompressorDashboardValues();
		setPdpDashboardValues();
	}

	private void setCompressorDashboardValues() {
		setSmartDashboard("compressor_enabled", getCompressor().enabled());
		setSmartDashboard("compressor_cth_f", getCompressor().getCompressorCurrentTooHighFault());
		setSmartDashboard("compressor_cth_sf", getCompressor().getCompressorCurrentTooHighStickyFault());
		setSmartDashboard("compressor_nc_f", getCompressor().getCompressorNotConnectedFault());
		setSmartDashboard("compressor_nc_sf", getCompressor().getCompressorNotConnectedStickyFault());
		setSmartDashboard("compressor_s_f", getCompressor().getCompressorShortedFault());
		setSmartDashboard("compressor_s_sf", getCompressor().getCompressorShortedStickyFault());
	}

	private void setPdpDashboardValues() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			sb.append(";");
			// THIS is the thing throwing that error into the console
			sb.append(getPdp().getCurrent(i));
		}

		setSmartDashboard("pdp_status", sb.toString());
		setSmartDashboard("pdp_total_current", getPdp().getTotalCurrent());
		setSmartDashboard("pdp_total_power", getPdp().getTotalPower());
		setSmartDashboard("pdp_voltage", getPdp().getVoltage());
		setSmartDashboard("pdp_total_energy", getPdp().getTotalEnergy());
		setSmartDashboard("pdp_ch[15]", getPdp().getCurrent(15));
	}
}
