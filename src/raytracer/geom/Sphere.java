package raytracer.geom;

import raytracer.core.Hit;
import raytracer.core.Obj;
import raytracer.core.def.LazyHitTest;
import raytracer.math.Point;
import raytracer.math.Ray;
import raytracer.math.Vec2;
import raytracer.math.Vec3;

public class Sphere extends BBoxedPrimitive {
    private final float distance;
    private final Point centre1;

    public Sphere(final Point centre1, final float distance) {

        super(BBox.create(new Point(centre1.x() - distance, centre1.y() - distance, centre1.z() - distance),
                new Point(centre1.x() + distance, centre1.y() + distance, centre1.z() + distance)));

        this.distance = distance;
        this.centre1 = centre1;
    }

    @Override
    public Hit hitTest(Ray ray, Obj obj, float tmin, float tmax) {
        // TODO Auto-generated method stub
        return new LazyHitTest(obj) {

            private Point point = null;
            private float r, a, b, c, sqrt1, fin1, fin2;

            @Override
            public float getParameter() {

                return r;
            }

            @Override
            public Point getPoint() {

                if (point == null)
                    point = ray.eval(r);
                return point;
            }

            @Override
            public Vec3 getNormal() {
                // ask doubt
                return getPoint().sub(centre1).normalized();
            }

            @Override

            public Vec2 getUV() {

                return Util.computePlaneUV(point.sub(centre1).normalized(), centre1, point);
            }

            @Override
            protected boolean calculateHit() {
                a = 1;
                b = 2 * ray.dir().dot(ray.base().sub(centre1));
                Vec3 d = ray.base().sub(centre1);
                c = (d.dot(d)) - (distance * distance);

                sqrt1 = (float) ((b * b) - (4 * a * c));
                if (sqrt1 < 0) {
                    return false;
                }
                sqrt1 = (float) Math.sqrt(sqrt1);
                fin1 = (float) (((b * -1) + sqrt1) * 0.5);
                fin2 = (float) (((b * -1) - sqrt1) * 0.5);

                r = Math.min(fin1, fin2);
                if (fin1 < 0 || fin2 < 0) {
                    return false;

                }

                if (fin1 < tmin || fin1 > tmax || fin2 < tmin || fin2 > tmax) {
                    return false;
                }
                return true;

            }
        };
    }

    @Override
    public int hashCode() {
        return centre1.hashCode() ^ Float.hashCode(distance);
    }

    @Override
    public boolean equals(final Object other) {
        if (other instanceof Sphere) {
            final Sphere cobj = (Sphere) other;
            return cobj.centre1.equals(centre1) && Float.valueOf(cobj.distance).equals(Float.valueOf(distance));
        }
        return false;
    }

}
