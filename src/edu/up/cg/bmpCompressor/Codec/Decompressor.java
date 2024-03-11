package edu.up.cg.bmpCompressor.Codec;

import edu.up.cg.bmpCompressor.Tools.NBitReader;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;

/**
 * Decompressor is a class that provides functionality to decompress BMP files.
 * This class extends Codec and implements methods for decompressing BMP files.
 */
public class Decompressor extends Codec {
    private NBitReader nBitReader;
    private String fileToDecompress, fileDirectory, fileName;

    /**
     * Constructs a new Decompressor object and performs the decompression operation.
     */
    public Decompressor(){
        setParameters();
        setNBitReader(getFileToDecompress());
        operateFile();
    }

    /**
     * Reads RGB color values from the NBitReader.
     * @param fileReader The NBitReader object to read from.
     * @param divisor The divisor used for scaling the color values.
     * @param bits The number of bits used for representing each color component.
     * @return The Color object representing the read RGB color values.
     */
    private Color readRGB(NBitReader fileReader, int divisor, int bits){
        int r, g, b;

        try {
            r = fileReader.readNValues(bits);
            g = fileReader.readNValues(bits);
            b = fileReader.readNValues(bits);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }

        return new Color(r*divisor, g*divisor, b*divisor);
    }

    @Override
    public void operateFile() {

        try {
            int width = getNBitReader().readIntValue();
            int height = getNBitReader().readIntValue();
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Color color;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    color = readRGB(getNBitReader(), getDivisor(), getBits());
                    bufferedImage.setRGB(x, y, color.getRGB());
                }
            }
            getNBitReader().close();

            File bmpFile = new File(getFileDirectory()+"\\"+getFileName()+ ".bmp");
            getInputOutput().showInfo("The file "+ bmpFile.getName()+ " was created successfully");
            ImageIO.write(bufferedImage, "bmp", bmpFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setParameters(){
        setFileToDecompress(getInputOutput().getFile("Enter the path to the file you would like to decompress", "Please enter a valid path"));
        setFileDirectory(getInputOutput().getFile("Enter the directory where you would like to save your BMP", "Please enter a valid path"));
        setFileName(getInputOutput().getFileName("Enter the name for your BMP", "Please enter a valid name"));
        setDivisor(Integer.parseInt(getFileToDecompress().split(".bean")[1]));
        setBits(Integer.bitCount(Math.floorDiv(255, getDivisor())));
    }

    /**
     * Returns the NBitReader used for reading the input file.
     * @return The NBitReader object.
     */
    public NBitReader getNBitReader() {
        return nBitReader;
    }

    private void setNBitReader(String fileName){
        nBitReader = new NBitReader(fileName);
    }

    public String getFileToDecompress() {
        return fileToDecompress;
    }

    public void setFileToDecompress(String fileToDecompress) {
        this.fileToDecompress = fileToDecompress;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
