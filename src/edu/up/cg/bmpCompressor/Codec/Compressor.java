package edu.up.cg.bmpCompressor.Codec;

import edu.up.cg.bmpCompressor.Tools.NBitWriter;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Compressor is a class that provides functionality to compress BMP images.
 * This class extends Codec and implements methods for compressing BMP images.
 */
public class Compressor extends Codec {
    private NBitWriter nBitWriter;
    private String fileDirectory,fileName;
    private BufferedImage bufferedImage;

    /**
     * Constructs a new Compressor object and performs the compression operation.
     */
    public Compressor(){
        setParameters();
        operateFile();
    }

    /**
     * Writes RGB color values to the NBitWriter.
     * @param fileWriter The NBitWriter object to write to.
     * @param color The Color object representing the RGB color values to write.
     * @param bits The number of bits used for representing each color component.
     */
    private void writeRGB(NBitWriter fileWriter, Color color, int bits){
        int r,g,b;
        r = Math.floorDiv(color.getRed(),getDivisor());
        g = Math.floorDiv(color.getGreen(),getDivisor());
        b = Math.floorDiv(color.getBlue(),getDivisor());

        fileWriter.writeValue(r,bits);
        fileWriter.writeValue(g,bits);
        fileWriter.writeValue(b,bits);

    }

    @Override
    public void operateFile() {
        Color color;

        getNBitWriter().writeIntValue(getBufferedImg().getWidth());
        getNBitWriter().writeIntValue(getBufferedImg().getHeight());

        for (int y = 0; y < getBufferedImg().getHeight(); y++) {
            for (int x = 0; x < getBufferedImg().getWidth(); x++) {
                color = new Color(getBufferedImg().getRGB(x,y));
                writeRGB(getNBitWriter(), color, getBits());
            }
        }

        getNBitWriter().close();
        getInputOutput().showInfo("The file "+getFileName()+".bean"+getDivisor()+" was created successfully");
    }


    @Override
    public void setParameters() {
        int[] options = {1, 2, 3, 4};
        setBufferedImg(inputOutput.getBMP("Enter the path for the image you would like to compress", "Invalid path or image isn't bmp"));
        setFileDirectory(inputOutput.getFile("Enter the directory where you'll save your compressed image", "Please enter a valid directory"));
        setFileName(inputOutput.getFileName("Enter the name for your new file", "Please enter a valid name"));
        int option = inputOutput.getOption("1. 0%\n2. 12.5%\n3. 25%\n4. 50%", "Please enter a valid option", options);

        switch (option) {
            case 1 -> setDivisor(1);
            case 2 -> setDivisor(2);
            case 3 -> setDivisor(4);
            case 4 -> setDivisor(8);
        }

        setBits(Integer.bitCount(Math.floorDiv(255, getDivisor())));
        setNBitWriter(getFileDirectory()+"\\"+getFileName()+".bean"+getDivisor());
    }

    private void setNBitWriter(String fileName){
        nBitWriter = new NBitWriter(fileName);
    }

    /**
     * Returns the NBitWriter used for writing the compressed file.
     * @return The NBitWriter object.
     */
    public NBitWriter getNBitWriter() {
        return nBitWriter;
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

    public BufferedImage getBufferedImg() {
        return bufferedImage;
    }

    public void setBufferedImg(BufferedImage bufferedImg) {
        this.bufferedImage = bufferedImg;
    }
}
