package lv.cebbys.mcmods.celib.mod.utility;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class CelibDirections {

    public static Direction toDirection(Vec3 v) {
        return switch (getMaxComponent(v)) {
            case X -> Direction.fromAxisAndDirection(Component.X.toAxis(), toAxisDirection(v.x()));
            case Y -> Direction.fromAxisAndDirection(Component.Y.toAxis(), toAxisDirection(v.y()));
            case Z -> Direction.fromAxisAndDirection(Component.Z.toAxis(), toAxisDirection(v.z()));
        };
    }

    private static Component getMaxComponent(Vec3 v) {
        double x = Math.abs(v.x());
        double y = Math.abs(v.y());
        double z = Math.abs(v.z());
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
