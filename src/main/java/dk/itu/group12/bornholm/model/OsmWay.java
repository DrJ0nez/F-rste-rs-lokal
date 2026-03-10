package dk.itu.group12.bornholm.model;

import java.util.HashMap;
import java.util.List;

public class OsmWay extends OsmElement {

    private final List<OsmNode> nodes;
    private final HashMap<String, String> tags;

    public OsmWay(long id, List<OsmNode> nodes, HashMap<String, String> tags) {
        super(id);
        this.nodes = nodes;
        this.tags = tags;
    }

    public List<OsmNode> getNodes() {
        return nodes;
    }

    public HashMap<String, String> getTags() {
        return tags;
    }
}
