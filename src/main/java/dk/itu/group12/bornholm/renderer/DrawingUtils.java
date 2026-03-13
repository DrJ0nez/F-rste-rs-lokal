package dk.itu.group12.bornholm.renderer;
import dk.itu.group12.bornholm.model.OsmElement;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
//import static java.awt.image.ImageObserver.HEIGHT;
import java.util.Comparator;
import java.util.List;

public class DrawingUtils {

    public static void sortOsmElementsByArea(List<OsmElement> elements) {
        // Using negative area to get biggest to smallest
        elements.sort(Comparator.comparingDouble(o -> -o.getArea()));
    }

    public static void applyTransformation(Graphics2D gc) {
        double minLat = 55.7639, maxLat = 56.0041;
        double minLon = 10.4013;
        double latSpan = maxLat - minLat;
        double sf = DrawingApp.getHEIGHT() / latSpan;

        System.out.println("sf: " + sf);
        System.out.println("sf * 0.56: " + (sf * 0.56));

        AffineTransform transform = new AffineTransform();
        transform.scale(sf * 0.56, -sf);
        transform.translate(-minLon, -maxLat);

        System.out.println("Transform: " + transform);
        gc.setTransform(transform);
    }
}