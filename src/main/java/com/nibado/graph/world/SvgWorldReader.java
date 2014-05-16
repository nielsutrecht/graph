package com.nibado.graph.world;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;

public class SvgWorldReader extends WorldReader<InputStream> {


    public SvgWorldReader() {

    }
    @Override
    World read(final InputStream input) throws Exception {
        return readDocument(new SAXBuilder().build(input));
    }

    private World readDocument(final Document doc) throws Exception {
        final World world = new World();
        world.setNavMesh(new NavMesh());
        world.getNavMesh().setPolygons(new ArrayList<NavMeshPoly>());
        world.setWalls(new ArrayList<Wall>());
        final String width = doc.getRootElement().getAttributeValue("width");
        final String height = doc.getRootElement().getAttributeValue("height");

        if (width == null || height == null) {
            throw new IOException("<svg> must constain both height and width attributes");
        }

        for (final Element e : doc.getDescendants(new ElementFilter("rect"))) {
            final String clazz = e.getAttributeValue("class");
            float x;
            float y;
            float w;
            float h;
            switch (clazz) {
                case "wall":
                    x = getFloatAtt(e, "x");
                    y = getFloatAtt(e, "y");
                    w = getFloatAtt(e, "width");
                    h = getFloatAtt(e, "height");
                    world.getWalls().add(new Wall(x, y, w, h));
                    break;
                case "mesh":
                    x = getFloatAtt(e, "x");
                    y = getFloatAtt(e, "y");
                    w = getFloatAtt(e, "width");
                    h = getFloatAtt(e, "height");

                    addRectPoly(world, x, y, w, h);
                    break;
            }
        }

        return world;
    }

    private float getFloatAtt(final Element e, final String att) {
        return Float.parseFloat(e.getAttributeValue(att));
    }

}
