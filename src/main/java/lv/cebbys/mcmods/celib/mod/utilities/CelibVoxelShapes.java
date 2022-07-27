package lv.cebbys.mcmods.celib.mod.utilities;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class CelibVoxelShapes {

    public static VoxelShape rotatePosY(VoxelShape shape) {
        List<AABB> boxes = shape.toAabbs();
        VoxelShape rotated = Shapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            AABB box = boxes.get(i);
            double[] dMin = {box.minX - 0.5, box.minY, box.minZ - 0.5};
            double[] dMax = {box.maxX - 0.5, box.maxY, box.maxZ - 0.5};
            double[] rp1 = {0.5 - dMin[2], box.minY, 0.5 + dMax[0]};
            double[] rp2 = {0.5 - dMax[2], box.maxY, 0.5 + dMin[0]};
            if (i == 0) rotated = shapeFromPoints(rp1, rp2);
            else rotated = Shapes.or(rotated, shapeFromPoints(rp1, rp2));
        }
        return rotated;
    }

    public static VoxelShape rotateNegY(VoxelShape shape) {
        List<AABB> boxes = shape.toAabbs();
        VoxelShape rotated = Shapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            AABB box = boxes.get(i);
            double[] dMin = {box.minX - 0.5, box.minY, box.minZ - 0.5};
            double[] dMax = {box.maxX - 0.5, box.maxY, box.maxZ - 0.5};
            double[] rp1 = {0.5 + dMin[2], box.minY, 0.5 - dMax[0]};
            double[] rp2 = {0.5 + dMax[2], box.maxY, 0.5 - dMin[0]};
            if (i == 0) rotated = shapeFromPoints(rp1, rp2);
            else rotated = Shapes.or(rotated, shapeFromPoints(rp1, rp2));
        }
        return rotated;
    }

    public static VoxelShape mirrorAxisY(VoxelShape shape) {
        List<AABB> boxes = shape.toAabbs();
        VoxelShape mirrored = Shapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            AABB box = boxes.get(i);
            double[] p1 = {box.minX, 1.0 - box.minY, box.minZ};
            double[] p2 = {box.maxX, 1.0 - box.maxY, box.maxZ};
            if (i == 0) mirrored = shapeFromPoints(p1, p2);
            else mirrored = Shapes.or(mirrored, shapeFromPoints(p1, p2));
        }
        return mirrored;
    }

    private static VoxelShape shapeFromPoints(double[] p1, double[] p2) {
        return Shapes.create(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2]);
    }
}
