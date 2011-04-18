/**
 * VirtualMachine.java
 *
 * A Virtual Machine for the Abstract Machine Language. It's purpose is to be
 * able to decode and execute an instruction stack.
 */

package wam.vm;

import wam.code.*;
import wam.exception.*;

public class VirtualMachine {
    /**
     * Execute one instruction in the configuration supplied.
     *
     * @param config The configuration to work on.
     * @return the resulting, new configuration.
     */
    public Configuration step(Configuration config) throws WamException {
        config = new Configuration(config);
        Instruction instruction = config.getInstructions().pop();
        instruction.execute(config);

        return config;
    }
}
