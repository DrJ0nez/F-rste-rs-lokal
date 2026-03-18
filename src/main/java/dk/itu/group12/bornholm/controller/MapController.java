package dk.itu.group12.bornholm.controller;

import dk.itu.group12.bornholm.view.MapFunctions;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.Node;

public class MapController {
    private final MapFunctions mapFunctions;
    private final Runnable redrawRequest;

    private double lastMouseX;
    private double lastMouseY;

    public MapController(MapFunctions mapFunctions, Runnable redrawRequest) {
        this.mapFunctions = mapFunctions;
        this.redrawRequest = redrawRequest;
    }

    /**
     * Call this once to "hook up" the mouse to the screen.
     */
    public void initEvents(Node target) {
        target.setOnMousePressed(this::handleMousePressed);
        target.setOnMouseDragged(this::handleMouseDragged);
        target.setOnScroll(this::handleScroll);
    }

    private void handleMousePressed(MouseEvent event) {
        this.lastMouseX = event.getX();
        this.lastMouseY = event.getY();
    }

    private void handleMouseDragged(MouseEvent event) {
        double dx = event.getX() - this.lastMouseX;
        double dy = event.getY() - this.lastMouseY;

        mapFunctions.pan(dx, dy);

        this.lastMouseX = event.getX();
        this.lastMouseY = event.getY();

        redrawRequest.run();
    }

    private void handleScroll(ScrollEvent event) {
        double delta = event.getDeltaY();
        if (delta == 0) return; // Ignore "ghost" events

        double factor = (delta > 0) ? 1.05 : 1/1.05;

        // Zoom
        mapFunctions.zoom(factor, event.getX(), event.getY());

        redrawRequest.run();
    }
}