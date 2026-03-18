package dk.itu.group12.bornholm;

import parser.osmParser;

public class Main {
    public static void main(String[] args) {
        // files can be reached like samso/samso.osm or bondeholm/bondeholm.osm osv.
        osmParser parser = new osmParser("samso/samso.osm");
        parser.parse();
        // get bounding box of osmParser with parser.getBoundingBox()
        System.out.println(parser.getBoundingBox());
    }
}
