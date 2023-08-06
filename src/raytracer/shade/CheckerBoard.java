package raytracer.shade;

import raytracer.core.Hit;
import raytracer.core.Shader;
import raytracer.core.Trace;
import raytracer.math.Color;

public class CheckerBoard implements Shader {
    private final Shader a;
    private final Shader b;
    private final float scale;

    public CheckerBoard(final Shader a, final Shader b, final float scale) {
        this.a = a;
        this.b = b;
        this.scale = scale;
    }

    @Override
    public Color shade(Hit hit, Trace trace) {

        float u = hit.getUV().x();
        float v = hit.getUV().y();
        int result = (int) (Math.floor(u / scale) + Math.floor(v / scale));
        // int newResult = (int) Math.floor(result);
        if (result % 2 != 0) {
            return b.shade(hit, trace);
        } else {
            return a.shade(hit, trace);
        }
    }
}
