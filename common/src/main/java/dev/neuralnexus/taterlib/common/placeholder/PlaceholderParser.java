package dev.neuralnexus.taterlib.common.placeholder;

/** The placeholder parser class. */
public class PlaceholderParser {
    private String input;

    /**
     * Create a new placeholder parser.
     *
     * @param input The input string.
     */
    public PlaceholderParser(String input) {
        this.input = input;
    }

    /**
     * Parse the section sign.
     *
     * @return The string with the section sign.
     */
    static String parseSectionSign(String s) {
        return s.replaceAll("&", "§");
    }

    /**
     * Parse the section sign.
     *
     * @return The string with the section sign.
     */
    public static String substituteSectionSign(String s) {
        return s.replaceAll("&", "§");
    }

    /**
     * Strip the section sign from the string.
     *
     * @return The string without the section sign.
     */
    public static String stripSectionSign(String s) {
        return s.replaceAll("§.", "");
    }

    /**
     * Parse a placeholder.
     *
     * @param placeholder The placeholder to parse.
     * @param value The value to replace the placeholder with.
     * @return The placeholder parser.
     */
    public PlaceholderParser parseString(String placeholder, String value) {
        this.input = input.replace("%" + placeholder + "%", value);
        return this;
    }

    /**
     * Parse the section sign.
     *
     * @return The placeholder parser.
     */
    public PlaceholderParser parseSectionSign() {
        this.input = input.replaceAll("&", "§");
        return this;
    }

    /**
     * Strip the section sign from the string.
     *
     * @return The placeholder parser.
     */
    public PlaceholderParser stripSectionSign() {
        this.input = input.replaceAll("&", "§");
        return this;
    }

    public String getResult() {
        return input;
    }
}
