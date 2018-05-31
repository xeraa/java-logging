package net.xeraa.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;


public class LogMe {

    private static final Logger logger = LoggerFactory.getLogger(LogMe.class);

    public static void main(String[] args) {
        Random random = new Random();

        for(int i=1; i<=20; i++) {

            // Generate a random session ID between 0 and 99 and add it as an MDC log as well as the iteration count
            String session = String.valueOf(random.nextInt(99));
            MDC.put("session", session);
            MDC.put("loop", String.valueOf(i));

            // Trace information for the loop run
            logger.trace("Iteration '{}' and session '{}'", i, session);

            // Log some errors, warns, infos, and debugs
            if(i % 15 == 0){
                try {
                    throw new RuntimeException("Bad runtime...");
                } catch (RuntimeException e) {
                    MDC.put("user_experience", "\uD83E\uDD2C");
                    logger.error("Wake me up at night", e);
                }
            } else if (i % 5 == 0){
                logger.warn("Investigate tomorrow");
            } else if (i % 3 == 0){
                logger.info("Collect in production");
            } else {
                logger.debug("Collect in development");
            }

            MDC.clear();
        }
    }
}
