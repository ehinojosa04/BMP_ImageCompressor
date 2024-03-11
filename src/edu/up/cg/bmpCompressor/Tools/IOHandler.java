package edu.up.cg.bmpCompressor.Tools;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * The IOHandler abstract class provides methods for handling input and output operations.
 * @author EmilianoH
 */
public abstract class IOHandler {

    /**
     * Displays information to the user.
     *
     * @param info The information to display.
     */
    public abstract void showInfo(String info);

    /**
     * Gets an option from the user with specified information prompts.
     *
     * @param validInput    The prompt for valid input.
     * @param notValidInput The prompt for invalid input.
     * @param options       The valid options the user can choose from.
     * @return The selected option.
     */
    public abstract int getOption(String validInput, String notValidInput, int[] options);

    /**
     * Gets a BufferedImage from the user with specified information prompts.
     *
     * @param validInput   The prompt for valid input.
     * @param invalidInput The prompt for invalid input.
     * @return The BufferedImage provided by the user.
     */
    public abstract BufferedImage getBMP(String validInput, String invalidInput);

    /**
     * Gets a file name from the user with specified information prompts.
     *
     * @param validInput   The prompt for valid input.
     * @param invalidInput The prompt for invalid input.
     * @return The file name provided by the user.
     */
    public abstract String getFileName(String validInput, String invalidInput);

    /**
     * Gets a File object from the user with specified information prompts.
     *
     * @param validInput   The prompt for valid input.
     * @param invalidInput The prompt for invalid input.
     * @return The File object provided by the user.
     */
    public abstract String getFile(String validInput, String invalidInput);
}
