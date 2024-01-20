package dev.neuralnexus.taterlib.logger;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** A generic implementation of the AbstractLogger interface. */
public class GenericLogger implements AbstractLogger {
    private final Logger logger;

    public GenericLogger(String PROJECT_ID) {
        logger = Logger.getLogger(PROJECT_ID);

        // Log formatter
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(
                new SimpleFormatter() {
                    private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                    @Override
                    public synchronized String format(LogRecord lr) {
                        return String.format(
                                format,
                                new Date(lr.getMillis()),
                                lr.getLevel().getLocalizedName(),
                                lr.getMessage());
                    }
                });
        logger.addHandler(handler);
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        this.logger.warning(message);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.severe(message);
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.fine(message);
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.finest(message);
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.severe(message);
    }
}
