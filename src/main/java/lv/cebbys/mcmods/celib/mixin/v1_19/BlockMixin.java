package lv.cebbys.mcmods.celib.mixin.v1_19;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class BlockMixin extends BlockBehaviour {
    private BlockMixin(Properties properties) {
        super(properties);
    }
}
