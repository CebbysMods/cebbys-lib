package lv.cebbys.mcmods.celib.mod.utilities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class CelibBlockPos extends BlockPos {

    public CelibBlockPos(int x, int y, int z) {
        super(x, y, z);
    }

    public static CelibBlockPos of(BlockPos p) {
        return new CelibBlockPos(p.getX(), p.getY(), p.getZ());
    }

    public CelibBlockPos add(BlockPos p) {
        return new CelibBlockPos(this.getX() + p.getX(), this.getY() + p.getY(), this.getZ() + p.getZ());
    }

    public CelibBlockPos sub(BlockPos p) {
        return new CelibBlockPos(this.getX() - p.getX(), this.getY() - p.getY(), this.getZ() - p.getZ());
    }

    public CelibBlockPos offset(Direction d) {
        return this.offset(d.getStepX(), d.getStepY(), d.getStepZ());
    }

    @Override
    public CelibBlockPos offset(int x, int y, int z) {
        return new CelibBlockPos(this.getX() + x, this.getY() + y, this.getZ() + z);
    }
}
