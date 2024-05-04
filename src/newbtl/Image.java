package newbtl;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Image {
    private String[] imagePaths={"src\\newbtl\\img\\puzzle1.jpg",
        "src\\newbtl\\img\\puzzle2.jpg",
        "src\\newbtl\\img\\puzzle3.jpg",
        "src\\newbtl\\img\\puzzle4.jpg"};
    private int indexImage;
    private BufferedImage[] imageP;

    public Image() {
        this.indexImage = 0;
        this.imageP = new BufferedImage[9];
    }

    public void switchImage(JLabel jb) throws IOException {
        indexImage++;
        if (indexImage >= imagePaths.length) {
            indexImage = 0; 
        }
        ImageIcon picture = new ImageIcon(imagePaths[indexImage]);
        jb.setIcon(picture);
        bufferedImage();
    }

    public void bufferedImage() throws IOException {
        File file = new File(imagePaths[indexImage]);
        BufferedImage originalImage = ImageIO.read(file);
        int rows = 3, cols = 3;
        int pieceWidth = originalImage.getWidth() / cols;
        int pieceHeight = originalImage.getHeight() / rows;

        int count = 0;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                imageP[count] = new BufferedImage(pieceWidth, pieceHeight, originalImage.getType());
                java.awt.Graphics2D g = imageP[count++].createGraphics();
                g.drawImage(
                    originalImage,
                    0,
                    0,
                    pieceWidth,
                    pieceHeight,
                    pieceWidth * x,
                    pieceHeight * y,
                    pieceWidth * x + pieceWidth,
                    pieceHeight * y + pieceHeight,
                    null
                );

                g.dispose();
            }
        }
    }
    public void ShuffleIfNeeded(boolean gameStarted,piece pe) {
            if (gameStarted) {
                pe.shuffle();
            }
    }  
    public BufferedImage[] getImagePieces() {
        return imageP;
    }
}

