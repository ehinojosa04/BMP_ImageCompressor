package edu.up.cg.bmpCompressor.Tools;

import java.io.*;

/**
 * NBitReader is a class that provides functionality to read integers from a file using an N amount of bits.
 * This class allows reading integer values with a specified number of bits from an input stream.
 * @author EmilianoH
 */
public class NBitReader {
    private InputStream inputStream; // The input stream used for reading data from the file
    private int buffer; // Buffer to store binary data temporarily
    private int bufferCount; // Count of bits stored in the buffer

    /**
     * Constructs a new NBitReader object with the specified file path.
     * @param filePath The path of the file to read data from.
     */
    public NBitReader(String filePath){
        try {
            setInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            setBuffer(0);
            setBufferCount(0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the next integer value using the specified number of bits.
     * @param bits The number of bits to use for reading the value.
     * @return The integer value read.
     * @throws EOFException If the end of file is reached.
     */
    public int readNValues(int bits) throws EOFException {
        if (getBufferCount() < bits) {
            try {
                int data = getInputStream().read();
                if (data == -1) {throw new EOFException("End of file reached.");}
                setBuffer(buffer |= data << getBufferCount());
                setBufferCount(getBufferCount() + 8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int value = getBuffer() & ((1 << bits) - 1);
        setBuffer(buffer >>= bits);
        setBufferCount(getBufferCount() - bits);
        return value;
    }

    /**
     * Reads the next integer value as a 32-bit integer.
     * @return The integer value read.
     */
    public int readIntValue() {
        int value = 0;
        try {
            value |= (getInputStream().read() << 24);
            value |= (getInputStream().read() << 16);
            value |= (getInputStream().read() << 8);
            value |= getInputStream().read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * Closes the input stream.
     * @throws IOException If an I/O error occurs while closing the stream.
     */
    public void close() throws IOException {
        getInputStream().close();
    }

    /**
     * Returns the input stream used for reading data.
     * @return The input stream.
     */
    public InputStream getInputStream() {return inputStream;}

    /**
     * Sets the input stream used for reading data.
     * @param inputStream The input stream to set.
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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
