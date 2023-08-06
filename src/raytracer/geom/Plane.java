package raytracer.geom;

import raytracer.core.Hit;
import raytracer.core.Obj;
import raytracer.core.def.LazyHitTest;
import raytracer.math.Constants;
import raytracer.math.Point;
import raytracer.math.Ray;
import raytracer.math.Vec2;
import raytracer.math.Vec3;

public class Plane extends BBoxedPrimitive {
    private final Point m;
    private final Vec3 normal;

    public Plane(final Point a, final Point b, final Point c) {
        super(BBox.create(a.min(b, c), a.max(b, c)));
        this.m = a;
        Vec3 u = b.sub(a);
        Vec3 v = c.sub(a);
        this.normal = v.cross(u).normalized();
    }

    public Plane(final Vec3 normal, final Point supp) {
        this.m = supp;

        this.normal = normal;

    }

    @Override
    public Hit hitTest(Ray ray, Obj obj, float tmin, float tmax) {

        return new LazyHitTest(obj) {
            private Point point = null;
            private float r, s, t, distance, deno, checker;

            @Override
            public float getParameter() {

                return r;
            }

            @Override
            public Point getPoint() {

                if (point == null)
                    point = ray.eval(r).add(normal.scale(0.0001f));
                return point;
            }

            @Override
            public Vec3 getNormal() {

                return normal;
            }

            @Override
            public Vec2 getUV() {

                return Util.computePlaneUV(normal, m, point); /// ask doubt
            }

            @Override
            protected boolean calculateHit() {
                distance = getPoint().dot(normal);
                r = ray.base().dot(normal);
                deno = ray.dir().dot(normal);
                checker = (distance - r) / deno;
                if (checker < tmin || checker > tmax)
                    return false;
                if (deno == Constants.EPS) {
                    return false;
                }
                if (checker < 0) {
                    return false;
                }

                return true;

            }

        };

    }

    @Override
    public int hashCode() {
        return m.hashCode() ^ normal.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof Plane) {
            final Plane cobj = (Plane) other;
            return cobj.m.equals(m) && cobj.normal.equals(normal);
        }
        return false;
    }

}