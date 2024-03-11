package edu.up.cg.bmpCompressor.Tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * The IOConsole class provides methods for handling input and output operations through the console.
 */
public class IOConsole extends IOHandler {

    private Scanner scanner;

    /**
     * Constructs a new IOConsole instance with a default scanner connected to the standard input.
     */
    public IOConsole() {
        setScanner(new Scanner(System.in));
    }

    /**
     * Gets the scanner used for input.
     *
     * @return The Scanner object.
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Sets the scanner used for input.
     *
     * @param scanner The Scanner object to set.
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // Array of illegal characters for file names
    private final char[] ILLEGAL_CHARACTERS = { '/', '\n', '\r', '\t', '\0', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':' };

    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }

    @Override
    public int getOption(String info, String notValidInput, int[] options) {
        boolean validate = false;
        int data = 0;
        do {
            showInfo(info);
            try {
                String userInput = getScanner().nextLine();
                data = Integer.parseInt(userInput);
                for (int n : options) {
                    if (n == data) {
                        validate = true;
                        break;
                    }
                }
                if (!validate) {
                    throw new Exception();
                }
            } catch (Exception e) {
                showInfo(notValidInput);
            }
        } while (!validate);
        return data;
    }

    @Override
    public BufferedImage getBMP(String validInput, String invalidInput) {
        boolean validate = false;
        BufferedImage image = null;
        do {
            showInfo(validInput);
            try {
                String userInput = getScanner().nextLine();
                File file = new File(userInput);
                image = ImageIO.read(file);

                int index = file.getName().lastIndexOf('.');

                if (index > 0) {
                    if (file.getName().substring(index + 1).equals("bmp") && image.getWidth() > 0 && image.getWidth() <= Integer.MAX_VALUE && image.getHeight() > 0 && image.getHeight() <= Integer.MAX_VALUE) {
                        validate = true;
                    }
                } else {
                    throw new Exception();
                }

            } catch (Exception e) {
                showInfo(invalidInput);
            }
        } while (!validate);
        return image;
    }

    @Override
    public String getFileName(String validInput, String invalidInput) {
        boolean validate = false;
        String name = null;
        do {
            showInfo(validInput);
            try {
                name = scanner.nextLine();
                if (!name.isEmpty()) {
                    for (char n : ILLEGAL_CHARACTERS) {
                        if (name.indexOf(n) != -1) {
                            throw new InvalidParameterException();
                        }
                    }
                }
                validate = true;
            } catch (Exception e) {
                showInfo(invalidInput);
            }
        } while (!validate);
        return name;
    }

    @Override
    public String getFile(String validInput, String invalidInput) {
        boolean validate = false;
        File file = null;
        do {
            showInfo(validInput);
            try {
                file = Paths.get(scanner.nextLine()).toAbsolutePath().toFile();
                if (file.getPath().equals("C:\\")) {
                    throw new Exception();
                }
                if (file.exists()) {
                    validate = true;
                    System.out.println(file.getPath());
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                showInfo(invalidInput);
            }
        } while (!validate);
        return file.getPath();
    }
}
