package dk.itu.group12.bornholm.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.List;
import java.util.Map;

public class OsmWay extends OsmElement {

    private final List<OsmNode> nodes;
    private final Map<String, String> tags;

    public OsmWay(long id, List<OsmNode> nodes, Map<String, String> tags) {
        super(id);
        this.nodes = nodes;
        this.tags = tags;

        //Task 5: Calculate the area based on the bounding box
        if (nodes != null && nodes.size() > 0) {
            // Initialize min and max values
            double minLat = Double.POSITIVE_INFINITY;
            double maxLat = Double.NEGATIVE_INFINITY;
            double minLon = Double.POSITIVE_INFINITY;
            double maxLon = Double.NEGATIVE_INFINITY;

            // Find the bounds (min and max lat/lon)
            for (OsmNode node : nodes) {
                double lat = node.getLat();
                double lon = node.getLon();

                if (lat < minLat) minLat = lat;
                if (lat > maxLat) maxLat = lat;
                if (lon < minLon) minLon = lon;
                if (lon > maxLon) maxLon = lon;
            }

            // Calculate the area of the bounding rectangle
            double width = maxLon - minLon;
            double height = maxLat - minLat;
            double area = width * height;

            this.setArea(area);
        } else {
            this.setArea(0);
        }
    }

    public List<OsmNode> getNodes() {
        return nodes;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public void drawForTest(Graphics2D gc, Color color, Integer strokeWidth) {
        // If the way has less than two nodes, nothing should be drawn
        if (nodes == null || nodes.size() < 2) {
            return;
        }

        // Set the color
        gc.setColor(color);

        // Create a path for the way
        Path2D.Double path = new Path2D.Double();

        // Start at the first node
        OsmNode firstNode = nodes.get(0);
        path.moveTo(firstNode.getLon(), firstNode.getLat());

        // Draw lines to all subsequent nodes
        for (int i = 1; i < nodes.size(); i++) {
            OsmNode node = nodes.get(i);
            path.lineTo(node.getLon(), node.getLat());
        }

        // Task 4: Check if this is a polygon (closed way)
        // A polygon has at least 3 nodes and first node id == last node id
        boolean isPolygon = nodes.size() >= 3 &&
                firstNode.getId() == nodes.get(nodes.size() - 1).getId();

        if (isPolygon) {
            // Polygons are filled with color and have no contour
            gc.fill(path);
        } else {
            // Lines (non-closed ways) have a stroke
            gc.setStroke(new BasicStroke(strokeWidth));
            gc.draw(path);
        }
    }
}