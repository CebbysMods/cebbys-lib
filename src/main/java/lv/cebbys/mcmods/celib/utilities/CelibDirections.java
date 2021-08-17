package lv.cebbys.mcmods.celib.utilities;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CelibDirections {

    public static Direction toDirection(Vec3d v) {
        switch (getMaxComponent(v)) {
            case X:
                return Direction.from(Component.X.toAxis(), toAxisDirection(v.getX()));
            case Y:
                return Direction.from(Component.Y.toAxis(), toAxisDirection(v.getY()));
            case Z:
                return Direction.from(Component.Z.toAxis(), toAxisDirection(v.getZ()));
            default:
                throw new RuntimeException("Invalid result at CelibDirections#getMaxComponent()");
        }
    }

    private static Component getMaxComponent(Vec3d v) {
        double x = Math.abs(v.getX());
        double y = Math.abs(v.getY());
        double z = Math.abs(v.getZ());
        if (x >= y && x >= z) {
            return Component.X;
        } else if (z >= x && z >= y) {
            return Component.Z;
        } else {
            return Component.Y;
        }
    }

    private static Direction.AxisDirection toAxisDirection(double d) {
        if (d >= 0) {
            return Direction.AxisDirection.POSITIVE;
        }
        return Direction.AxisDirection.NEGATIVE;
    }

    private enum Component {
        X(Direction.Axis.X), Y(Direction.Axis.Y), Z(Direction.Axis.Z);

        private Direction.Axis a;

        Component(Direction.Axis axis) {
            this.a = axis;
        }

        public Direction.Axis toAxis() {
            return this.a;
        }
    }

}
