package dk.itu.group12.bornholm.renderer;
import dk.itu.group12.bornholm.model.Drawable;

import dk.itu.group12.bornholm.model.OsmNode;
import dk.itu.group12.bornholm.model.OsmWay;
import parser.Parser;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends DrawingApp {

    private final List<Drawable> drawableList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Drawing App");
        stage.setResizable(false);
        stage.setWidth(getWIDTH());
        stage.setHeight(getHEIGHT());

        stage.setScene(new Scene(new StackPane(this.imageView)));
        stage.show();

        // Parse Samsø
        Parser parser = new Parser("hc-osm-files.zip");
        parser.parse();
        System.out.println("Nodes: " + parser.getOsmNodeMap().size());
        System.out.println("Ways: " + parser.getOsmWayMap().size());

        this.draw(parser);
        this.render();
    }

    public void draw(Parser parser) {
        Graphics2D gc = getNewGraphicsContext();
        gc.setColor(Color.WHITE);
        gc.fillRect(0, 0, getWIDTH(), getHEIGHT());

        // Debug: tjek om der er ways
        System.out.println("Ways at tegne: " + parser.getOsmWayMap().size());

        // Tjek første way's koordinater
        for (OsmWay way : parser.getOsmWayMap().values()) {
            way.drawForTest(gc, new Color(0, 0, 0, 50), 1); // semi-transparent sort
        }

        DrawingUtils.applyTransformation(gc);

        for (OsmWay way : parser.getOsmWayMap().values()) {
            way.drawForTest(gc, Color.BLACK, 1);
        }
    }
}