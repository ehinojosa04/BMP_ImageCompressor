package edu.up.cg.bmpCompressor.Codec;

import edu.up.cg.bmpCompressor.Tools.IOConsole;
import edu.up.cg.bmpCompressor.Tools.IOHandler;

/**
 * Codec is an abstract class that serves as a base for BMP image compression and decompression codecs.
 * It provides common functionality and abstract methods to be implemented by concrete codec classes.
 */
public abstract class Codec {
    int divisor, bits;
    IOHandler inputOutput = new IOConsole();

    /**
     * Returns the IOHandler object used for input/output operations.
     * @return The IOHandler object.
     */
    public IOHandler getInputOutput() {
        return inputOutput;
    }

    /**
     * Returns the divisor used for scaling color values during compression or decompression.
     * @return The divisor value.
     */
    public int getDivisor() {
        return divisor;
    }

    /**
     * Sets the divisor used for scaling color values during compression or decompression.
     * @param divisor The divisor value to set.
     */
    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    /**
     * Returns the number of bits used for representing each color component.
     * @return The number of bits.
     */
    public int getBits() {
        return bits;
    }

    /**
     * Sets the number of bits used for representing each color component.
     * @param bits The number of bits to set.
     */
    public void setBits(int bits) {
        this.bits = bits;
    }

    /**
     * Abstract method to be implemented by concrete codec classes.
     * This method defines the main operation to be performed on the input file.
     */
    public abstract void operateFile();

    /**
     * Abstract method to be implemented by concrete codec classes.
     * This method defines the parameters required for compression or decompression.
     */
    public abstract void setParameters();
}
