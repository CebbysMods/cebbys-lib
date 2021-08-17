package lv.cebbys.mcmods.celib.utilities;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class CelibBlockPos extends BlockPos {

    public CelibBlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public static CelibBlockPos of(BlockPos p) {
        return new CelibBlockPos(p.getX(), p.getY(), p.getZ());
    }

    public static Vec3d toVec3d(BlockPos p) {
        return new Vec3d(p.getX(), p.getY(), p.getZ());
    }

    public CelibBlockPos add(BlockPos p) {
        return new CelibBlockPos(this.getX() + p.getX(), this.getY() + p.getY(), this.getZ() + p.getZ());
    }

    public CelibBlockPos sub(BlockPos p) {
        return new CelibBlockPos(this.getX() - p.getX(), this.getY() - p.getY(), this.getZ() - p.getZ());
    }

    @Override
    public CelibBlockPos offset(Direction d) {
        return this.offset(d.getOffsetX(), d.getOffsetY(), d.getOffsetZ());
    }

    public CelibBlockPos offset(int x, int y, int z) {
        return new CelibBlockPos(this.getX() + x, this.getY() + y, this.getZ() + z);
    }

    public Vec3d toVec3d() {
        return new Vec3d(this.getX(), this.getY(), this.getZ());
    }

//	public CelibBlockPos(int x, int y, int z) {
//		super(x, y, z);
//	}
//
//	public CelibBlockPos(double x, double y, double z) {
//		super(x, y, z);
//	}
//
//	public BlockPos add(BlockPos p) {
//		this.setX(this.getX() + p.getX());
//		this.setY(this.getY() + p.getY());
//		this.setZ(this.getZ() + p.getZ());
//		return this;
//	}
//
//	public BlockPos add(Vec3d p) {
//		this.setX((int) (this.getX() + p.x));
//		this.setY((int) (this.getY() + p.y));
//		this.setZ((int) (this.getZ() + p.z));
//		return this;
//	}
//
//	public BlockPos sub(BlockPos p) {
//		this.setX(this.getX() - p.getX());
//		this.setY(this.getY() - p.getY());
//		this.setZ(this.getZ() - p.getZ());
//		return this;
//	}
//
//	public BlockPos sub(Vec3d p) {
//		this.setX((int) (this.getX() - p.x));
//		this.setY((int) (this.getY() - p.y));
//		this.setZ((int) (this.getZ() - p.z));
//		return this;
//	}
//
//	public BlockPos sub(Vec3i v) {
//		return this.sub(v.getX(), v.getY(), v.getZ());
//	}
//
//	public BlockPos sub(int x, int y, int z) {
//		this.setX(this.getX() - x);
//		this.setY(this.getY() - y);
//		this.setZ(this.getZ() - z);
//		return this;
//	}
//
//	public BlockPos sub(double x, double y, double z) {
//		setX((int) (getX() - x));
//		setY((int) (getY() - y));
//		setZ((int) (getZ() - z));
//		return this;
//	}
//
//	public Vec3d toVec3d() {
//		return new Vec3d(getX(), getY(), getZ());
//	}
//
//	public Vec3i toVec3i() {
//		return new Vec3i(getX(), getY(), getZ());
//	}
//
//	public static CelibBlockPos of(Vec3d v) {
//		return new CelibBlockPos(v.getX(), v.getY(), v.getZ());
//	}
//
//	public static CelibBlockPos of(Vec3i v) {
//        return new CelibBlockPos(v.getX(), v.getY(), v.getZ());
//    }
//	
//	
//	public static CelibBlockPos of(Position p) {
//		return new CelibBlockPos(p.getX(), p.getY(), p.getZ());
//	}
}
