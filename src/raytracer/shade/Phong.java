package raytracer.shade;

import java.util.Collection;

import raytracer.core.Camera;
import raytracer.core.Hit;
import raytracer.core.LightSource;
import raytracer.core.Shader;
import raytracer.core.Trace;
import raytracer.core.def.PointLightSource;
import raytracer.math.Color;
import raytracer.math.Ray;
import raytracer.math.Vec3;

public class Phong implements Shader {
    private final Shader inner;
    private final Color ambient;
    private final float diffuse;
    private final float specular;
    private final float shininess;

    public Phong(Shader inner, Color ambient, float diffuse, float specular, float shininess) {
        this.inner = inner;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;

    }

    @Override
    public Color shade(Hit hit, Trace trace) {

        // Collection<LightSource> lightSources = getLightSources();
        // for (LightSource lightSource : lightSources) {
        // float resultOfMax = Math.max(0.0f,
        // hit.getNormal().dot(lightSource.getLocation()));
        // Color first = lightSource.getColor().mul(inner.shade(hit, trace));
        // float second = resultOfMax * (diffuse);
        // Color new1 = second.scale(first);
        // Color finaColor = finaColor.add(new1);
        // }

        // Collection<LightSource> lightSources1 = getLightSources();
        // for (LightSource lightSource : lightSources1) {
        // Vec3 dirsctionOfv = lightSource.getLocation().sub(hit.getPoint());
        // Vec3 refVector = reflect(lightSource.getLocation().sub(hit.getPoint().neg(),
        // hit.getNormal()));
        // float result = Math.pow(Math.max(0.0f, dirsctionOfv.dot(refVector)),
        // shininess);
        // Float color = specular * (result);
        // Color color1 = inner.shade(hit, trace).scale(color);
        // Color finaColor1 = finaColor1.add(color1);
        // }

        // Color end = color1.add(finaColor);
        // end = end.add(ambient);
        // return end;
        return ambient;
    }

}
