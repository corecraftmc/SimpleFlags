package com.ryderbelserion.simpleflags.flags.types;

import com.ryderbelserion.simpleflags.flags.FlagBuilder;
import com.ryderbelserion.simpleflags.flags.enums.CustomFlags;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import java.util.logging.Level;

public class DrownFlag extends FlagBuilder {

    private StateFlag flag;

    @Override
    public void register() {
        try {
            getRegistry().register(this.flag = new StateFlag("prevent-drowning", true));
        } catch (FlagConflictException exception) {
            Flag<?> existingFlag = getRegistry().get("prevent-drowning");

            if (existingFlag instanceof StateFlag stateFlag) {
                this.flag = stateFlag;

                return;
            }

            this.plugin.getLogger().log(Level.WARNING, "An error has occurred registering " + getName() + " flag.", exception);
        }
    }

    @Override
    public String getName() {
        return CustomFlags.DROWN_FLAG.getName();
    }

    @Override
    public StateFlag getFlag() {
        return this.flag;
    }
}