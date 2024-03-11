import edu.up.cg.bmpCompressor.Codec.Codec;
import edu.up.cg.bmpCompressor.Codec.Compressor;
import edu.up.cg.bmpCompressor.Codec.Decompressor;
import edu.up.cg.bmpCompressor.Tools.IOConsole;
import edu.up.cg.bmpCompressor.Tools.IOHandler;

/**
 * The Main class contains the entry point for the application.
 * It provides a simple command-line interface for users to choose between compression and decompression.
 */
public class Main {
    /**
     * The main method serves as the entry point for the application.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        IOHandler console = new IOConsole(); // Initialize an IOConsole object for input/output operations
        Codec codec; // Declare a Codec object to handle compression or decompression
        int[] options; // Array to store options for user input
        int option; // Variable to store the selected option

        do {
            options = new int[]{1, 2}; // Define the available options: 1 for compression, 2 for decompression
            option = console.getOption("1. Compress\n2. Decompress", "not a valid Option", options); // Get user choice
            switch (option) {
                case 1:
                    codec = new Compressor(); // Initialize a Compressor object for compression
                    break;
                case 2:
                    codec = new Decompressor(); // Initialize a Decompressor object for decompression
                    break;
            }

            options = new int[]{0,1}; // Define options for continuing or exiting the application
            option = console.getOption("Would you like to perform another action?\n1. Yes\n0. No","Please enter a valid option", options); // Ask user if they want to continue
        } while (option != 0); // Repeat until the user chooses to exit
    }
}
