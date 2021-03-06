package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit16;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadSpFromHlTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xF9)); //ld sp, hl
        z80.getRegs().setHL(XBit16.valueOfUnsigned(0x1234));

        z80.runOneInstruction();

        Assert.assertEquals(0x1234, z80.getRegs().getSp().getUnsignedValue());

        Assert.assertEquals(1, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(6, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}