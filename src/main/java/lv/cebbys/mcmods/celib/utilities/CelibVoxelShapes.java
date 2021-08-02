package lv.cebbys.mcmods.celib.utilities;

import java.util.List;

import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class CelibVoxelShapes {

    public static VoxelShape rotatePosY(VoxelShape shape) {
        List<Box> boxes = shape.getBoundingBoxes();
        VoxelShape rotated = VoxelShapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            double[] dMin = {box.minX - 0.5, box.minY, box.minZ - 0.5};
            double[] dMax = {box.maxX - 0.5, box.maxY, box.maxZ - 0.5};
            double[] rp1 = {0.5 - dMin[2], box.minY, 0.5 + dMax[0]};
            double[] rp2 = {0.5 - dMax[2], box.maxY, 0.5 + dMin[0]};
            if (i == 0) rotated = shapeFromPoints(rp1, rp2);
            else rotated = VoxelShapes.union(rotated, shapeFromPoints(rp1, rp2));
        }
        return rotated;
    }

    public static VoxelShape rotateNegY(VoxelShape shape) {
        List<Box> boxes = shape.getBoundingBoxes();
        VoxelShape rotated = VoxelShapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            double[] dMin = {box.minX - 0.5, box.minY, box.minZ - 0.5};
            double[] dMax = {box.maxX - 0.5, box.maxY, box.maxZ - 0.5};
            double[] rp1 = {0.5 + dMin[2], box.minY, 0.5 - dMax[0]};
            double[] rp2 = {0.5 + dMax[2], box.maxY, 0.5 - dMin[0]};
            if (i == 0) rotated = shapeFromPoints(rp1, rp2);
            else rotated = VoxelShapes.union(rotated, shapeFromPoints(rp1, rp2));
        }
        return rotated;
    }

    public static VoxelShape mirrorAxisY(VoxelShape shape) {
        List<Box> boxes = shape.getBoundingBoxes();
        VoxelShape mirrored = VoxelShapes.empty();
        for (int i = 0; i < boxes.size(); i++) {
            Box box = boxes.get(i);
            double[] p1 = {box.minX, 1.0 - box.minY, box.minZ};
            double[] p2 = {box.maxX, 1.0 - box.maxY, box.maxZ};
            if (i == 0) mirrored = shapeFromPoints(p1, p2);
            else mirrored = VoxelShapes.union(mirrored, shapeFromPoints(p1, p2));
        }
        return mirrored;
    }

    private static VoxelShape shapeFromPoints(double[] p1, double[] p2) {
        return VoxelShapes.cuboid(p1[0], p1[1], p1[2], p2[0], p2[1], p2[2]);
    }
}
