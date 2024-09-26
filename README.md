# README: BMP_ImageCompressor
## Project Title: Bean BMP Image Compressor
### Author: Emiliano Hinojosa Guzmán
### Date: February 8, 2024

# **Overview**
This project provides a simple image compressor and decompressor for BMP (bitmap) files. The compression reduces the color depth of BMP images by using fewer bits for each RGB color channel, resulting in smaller file sizes. The compressed images are stored in a custom .bean format and can be decompressed back into BMP images.

# **Features**
- Compression: Reduces the size of BMP images by lowering color depth, providing options for different compression levels.
- Decompression: Restores .bean compressed files to their original BMP format.
- Command-Line Interface (CLI): Allows users to interactively compress or decompress images through a terminal.

# How it Works
## Compression
The compressor works by scaling down RGB values, reducing the color depth of an image and storing it in a custom format (.bean).
Users can choose compression levels from 0% to 50%, which affect the number of bits used for each color component.

## Decompression
The decompressor reads the .bean file, restores the RGB values, and reconstructs the original BMP image.
