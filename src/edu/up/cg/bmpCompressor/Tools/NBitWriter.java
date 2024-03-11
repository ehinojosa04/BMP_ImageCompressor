package edu.up.cg.bmpCompressor.Tools;

import java.io.*;

/**
 * NBitWriter is a class that provides functionality to write integers to a file using an N amount of bits.
 * This class allows writing integer values with a specified number of bits to an output stream.
 * @author EmilianoH
 */
public class NBitWriter {
    private OutputStream outputStream; // The output stream used for writing data to the file
    private int buffer; // Buffer to store binary data temporarily
    private int bufferCount; // Count of bits stored in the buffer

    /**
     * Constructs a new NBitWriter object with the specified file path.
     * @param filePath The path of the file to write data to.
     */
    public NBitWriter(String filePath) {
        try {
            setOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));
            setBuffer(0);
            setBufferCount(0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Writes the specified integer value using the specified number of bits.
     * @param value The integer value to write.
     * @param bits The number of bits to use for writing the value.
     */
    public void writeValue(int value, int bits) {
        setBuffer(buffer |= value << getBufferCount());
        setBufferCount(getBufferCount() + bits);
        while (getBufferCount() >= 8) {
            try {
                getOutputStream().write(getBuffer() & 0xFF);
                setBuffer(buffer >>= 8);
                setBufferCount(getBufferCount() - 8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Writes the specified integer value as a 32-bit integer.
     * @param value The integer value to write.
     */
    public void writeIntValue(int value) {
        try {
            getOutputStream().write((value >> 24) & 0xFF);
            getOutputStream().write((value >> 16) & 0xFF);
            getOutputStream().write((value >> 8) & 0xFF);
            getOutputStream().write(value & 0xFF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the output stream.
     */
    public void close() {
        try {
            if (getBufferCount() > 0) {
                getOutputStream().write(getBuffer() & 0xFF);
            }
            getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the output stream used for writing data.
     * @return The output stream.
     */
    public OutputStream getOutputStream() {return outputStream;}

    /**
     * Sets the output stream used for writing data.
     * @param outputStream The output stream to set.
     */
    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Returns the current buffer value.
     * @return The buffer value.
     */
    public int getBuffer() {
        return buffer;
    }

    /**
     * Sets the buffer value.
     * @param buffer The buffer value to set.
     */
    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }

    /**
     * Returns the count of bits stored in the buffer.
     * @return The buffer count.
     */
    public int getBufferCount() {
        return bufferCount;
    }

    /**
     * Sets the count of bits stored in the buffer.
     * @param bufferCount The buffer count to set.
     */
    public void setBufferCount(int bufferCount) {
        this.bufferCount = bufferCount;
    }
}
