package dk.itu.group12.bornholm.renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.IntBuffer;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelBuffer;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;

public abstract class DrawingApp extends Application {

    private static final int WIDTH = 800, HEIGHT = 800;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    /* GRAPHICAL COMPONENTS */
    // A buffer is pretty much an array/list. This buffer contains pixels displayed as integers.
    private final PixelBuffer<IntBuffer> pixelBuffer = new PixelBuffer<>(
            WIDTH, HEIGHT, // Size of buffer
            IntBuffer.allocate(WIDTH * HEIGHT), // Size of buffer
            PixelFormat.getIntArgbPreInstance() // How to interpret the values in the buffer (ARGB => any color with opacity)
    );
    // A buffered image is an image, which is formatted by a buffer
    private final BufferedImage bufferedImage = new BufferedImage(
            WIDTH, // Width
            HEIGHT, // Height
            BufferedImage.TYPE_INT_ARGB // How to interpret the values in the buffer (ARGB => any color with opacity)
    );
    // Main component displaying map
    protected final ImageView imageView = new ImageView();

    /**
     * Returns a Graphics2D instance used to draw unto the screen.
     */
    public final Graphics2D getNewGraphicsContext() {
        return bufferedImage.createGraphics();
    }

    /**
     * Updates the screen with whatever was drawn using the Graphics Context.
     */
    public final void render() {
        // This takes the BufferedImage, and gets the pixels representation
        int[] pixels = ((DataBufferInt) this.bufferedImage.getRaster().getDataBuffer()).getData();
        // This copies our pixels into the PixelBuffer
        System.arraycopy(pixels, 0, this.pixelBuffer.getBuffer().array(), 0, pixels.length);
        // This updates our graphical component with the buffer
        this.imageView.setImage(new WritableImage(this.pixelBuffer));
    }
}
