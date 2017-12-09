package net.xeraa.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;


public class LogMe {

    private static final Logger logger = LoggerFactory.getLogger(LogMe.class);

    public static void main(String[] args) {
        Random random = new Random();

        for(int i=0; i<10; i++) {

            // Generate a random session ID between 0 and 99 and add it as an MDC log as well as the iteration count
            String session = String.valueOf(random.nextInt(99));
            MDC.put("session", session);
            MDC.put("loop", String.valueOf(i));

            // Log an info and a warn (including the session ID) message
            logger.info("Give me an info");
            logger.warn("Give me a warn for session '{}'", session);

            // Include a caught exception for every third run
            if(i % 3 == 0) {
                try {
                    throw new RuntimeException("Give me an exception");
                } catch (RuntimeException e) {
                    logger.error("Hit a runtime exception", e);
                }
            }

            MDC.clear();
        }
    }
}
