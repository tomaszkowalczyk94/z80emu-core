package org.tomaszkowalczyk94.z80emu.core.instruction.load8bit;

import org.tomaszkowalczyk94.xbit.XBit8;
import org.tomaszkowalczyk94.z80emu.core.Z80;
import org.tomaszkowalczyk94.z80emu.core.instruction.Instruction;
import org.tomaszkowalczyk94.z80emu.core.helper.FlagHelper;
import org.tomaszkowalczyk94.z80emu.core.helper.InstructionHelper;
import org.tomaszkowalczyk94.z80emu.core.instruction.InstructionResult;
import org.tomaszkowalczyk94.z80emu.core.register.FlagRegManager.Flag;

/**
 * <h2>LD A, I</h2>
 *
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>Operation</td>
 *         <td>A ← 1</td>
 *     </tr>
 *     <tr>
 *         <td>Op Code:</td>
 *         <td>LD </td>
 *     </tr>
 *     <tr>
 *         <td>Operands</td>
 *         <td>A, I</td>
 *     </tr>
 * </table>
 * <br>
 * The contents of the Interrupt Vector Register I are loaded to the Accumulator
 *
 * <h3>Condition Bits Affected</h3>
 * <table border="1" cellspacing="0">
 *     <tr>
 *         <td>S</td>
 *         <td>is set if the I Register is negative; otherwise, it is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>Z</td>
 *         <td>is set if the I Register is 0; otherwise, it is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>H</td>
 *         <td>is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>P/V</td>
 *         <td>contains contents of IFF2.</td>
 *     </tr>
 *     <tr>
 *         <td>N</td>
 *         <td>is reset.</td>
 *     </tr>
 *     <tr>
 *         <td>C</td>
 *         <td>is not affected.</td>
 *     </tr>
 * </table>
 * If an interrupt occurs during execution of this instruction, the Parity flag contains a 0
 *
 */
public class LoadAFromI extends Instruction {

    private FlagHelper flagHelper;

    public LoadAFromI(InstructionHelper helper, FlagHelper flagHelper) {
        super(helper);
        this.flagHelper = flagHelper;
    }

    @Override
    public InstructionResult execute(XBit8 opcode, Z80 z80) {
        z80.getRegs().setA(
                z80.getRegs().getI()
        );

        flagHelper.setSignFlagByA(z80);
        flagHelper.setZeroFlagByA(z80);
        flagHelper.setPvFlagByIff2(z80);

        z80.getRegs().setFlag(Flag.H, false);
        z80.getRegs().setFlag(Flag.N, false);

        return InstructionResult.builder()
                .machineCycles(2)
                .clocks(9)
                .executionTime(2.25f)
                .size(2)
                .build();
    }
}
