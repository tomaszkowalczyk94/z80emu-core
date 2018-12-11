package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.Z80Exception;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;

/**
 * <h2>I ← A</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>I ← A</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>I, A</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Accumulator are loaded to the Interrupt Control Vector Register, I.
 */
public class LoadIFromA implements Instruction {
    @Override
    public void execute(XBit8 opcode, Z80 z80) throws Z80Exception {
        z80.getRegs().setI(
                z80.getRegs().getA()
        );
    }

    @Override
    public int getMachineCycles() {
        return 0;
    }

    @Override
    public int getClocks() {
        return 9;
    }

    @Override
    public float getExecutionTime() {
        return 2.25f;
    }

    @Override
    public int getSize() {
        return 2;
    }
}
