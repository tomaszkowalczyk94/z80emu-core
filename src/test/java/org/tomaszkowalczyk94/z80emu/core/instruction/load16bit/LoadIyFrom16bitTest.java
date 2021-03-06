package org.tomaszkowalczyk94.z80emu.core.instruction.load16bit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;

import static org.junit.Assert.*;

public class LoadIyFrom16bitTest {
    private Z80 z80;

    @Before
    public void setUp() {
        z80 = new Z80();
    }

    @Test
    public void execute() throws Exception {
        z80.getMemory().write(0, XBit8.valueOfUnsigned(0xFD));
        z80.getMemory().write(1, XBit8.valueOfUnsigned(0x21));
        z80.getMemory().write(2, XBit8.valueOfUnsigned(0x3C));
        z80.getMemory().write(3, XBit8.valueOfUnsigned(0xF0)); // ld IY, 61500

        z80.runOneInstruction();

        Assert.assertEquals(61500, z80.getRegs().getIy().getUnsignedValue());

        Assert.assertEquals(4, z80.getRegisterBank().getPc().getUnsignedValue());
        Assert.assertEquals(14, z80.getClockCyclesCounter());
        Assert.assertEquals(1, z80.getInstructionCounter());
    }
}