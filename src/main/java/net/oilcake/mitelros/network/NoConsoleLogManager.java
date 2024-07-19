package net.oilcake.mitelros.network;

import java.util.logging.Logger;
import net.minecraft.ILogAgent;

public class NoConsoleLogManager implements ILogAgent {
   private final Logger logger = Logger.getLogger("ITF");

   public Logger func_120013_a() {
      return this.logger;
   }

   public void logInfo(String s) {
   }

   public void logWarning(String s) {
   }

   public void logWarningFormatted(String s, Object... objects) {
   }

   public void logWarningException(String s, Throwable throwable) {
   }

   public void logSevere(String s) {
   }

   public void logSevereException(String s, Throwable throwable) {
   }

   public void logFine(String s) {
   }
}
