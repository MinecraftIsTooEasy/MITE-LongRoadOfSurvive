package net.oilcake.mitelros.network;

import net.minecraft.IConsoleLogManager;

import java.util.logging.Logger;

public class NoConsoleLogManager implements IConsoleLogManager {
    private final Logger logger = Logger.getLogger("ITF");

    @Override
    public Logger func_120013_a() {
        return this.logger;
    }

    @Override
    public void logInfo(String s) {

    }

    @Override
    public void logWarning(String s) {

    }

    @Override
    public void logWarningFormatted(String s, Object... objects) {

    }

    @Override
    public void logWarningException(String s, Throwable throwable) {

    }

    @Override
    public void logSevere(String s) {

    }

    @Override
    public void logSevereException(String s, Throwable throwable) {

    }

    @Override
    public void d(String s) {

    }
}
