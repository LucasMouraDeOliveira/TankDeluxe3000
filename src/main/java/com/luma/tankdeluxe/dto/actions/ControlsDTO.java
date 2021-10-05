package com.luma.tankdeluxe.dto.actions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ControlsDTO {
	
	private boolean forward;
	private boolean backward;
	private boolean left;
	private boolean right;
	private boolean shoot;
	private boolean dash;
	private boolean charging;
	private AimDTO aim;
	
}
