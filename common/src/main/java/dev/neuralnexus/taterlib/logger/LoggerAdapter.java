package dev.neuralnexus.taterlib.logger;

/** A generic implementation of the AbstractLogger interface. */
public class LoggerAdapter implements AbstractLogger {
    private final String prefix;
    private final AbstractLogger logger;

    public LoggerAdapter(String PROJECT_ID, Object logger) {
        this("", PROJECT_ID, logger);
    }

    public LoggerAdapter(String prefix, String PROJECT_ID, Object logger) {
        this.prefix = prefix;

        LoggerType type = LoggerType.GENERIC;

        if (logger instanceof java.util.logging.Logger) {
            type = LoggerType.JAVA;
        }

        try {
            if (Class.forName("org.apache.logging.log4j.Logger").isInstance(logger)) {
                type = LoggerType.APACHE;
            }
        } catch (ClassNotFoundException ignored) {
        }

        try {
            if (Class.forName("org.slf4j.Logger").isInstance(logger)) {
                type = LoggerType.SLF4J;
            }
        } catch (ClassNotFoundException ignored) {
        }

        switch (type) {
            case JAVA:
                this.logger = new GenericJavaLogger(logger);
                break;
            case APACHE:
                this.logger = new GenericApacheLogger(logger);
                break;
            case SLF4J:
                this.logger = new GenericSlf4jLogger(logger);
                break;
            default:
                this.logger = new GenericLogger(PROJECT_ID);
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void info(String message) {
        this.logger.info(this.prefix + message);
    }

    /** {@inheritDoc} */
    @Override
    public void warn(String message) {
        this.logger.warn(this.prefix + message);
    }

    /** {@inheritDoc} */
    @Override
    public void error(String message) {
        this.logger.error(this.prefix + message);
    }

    /** {@inheritDoc} */
    @Override
    public void debug(String message) {
        this.logger.debug(this.prefix + message);
    }

    /** {@inheritDoc} */
    @Override
    public void trace(String message) {
        this.logger.trace(this.prefix + message);
    }

    /** {@inheritDoc} */
    @Override
    public void fatal(String message) {
        this.logger.fatal(this.prefix + message);
    }

    enum LoggerType {
        JAVA,
        APACHE,
        SLF4J,
        GENERIC
    }
}
