package org.usfirst.frc.team2500.subSystems.loader;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeCube extends CommandGroup {

    public IntakeCube() {
        addSequential(new SetClaw(true));
        addSequential(new SetWheels(1.0));
        addSequential(new CloseClaw());
    }
}
