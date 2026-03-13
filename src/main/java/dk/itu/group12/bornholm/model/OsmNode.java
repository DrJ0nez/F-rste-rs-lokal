package dk.itu.group12.bornholm.model;

import java.awt.Color;
import java.awt.Graphics2D;

public class OsmNode extends OsmElement {

    private final double lat, lon;

    public OsmNode(long id, double lat, double lon) {
        super(id);
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public void drawForTest(Graphics2D gc, Color color, Integer strokeWidth) {
        /* Not yet implemented */ }
}
