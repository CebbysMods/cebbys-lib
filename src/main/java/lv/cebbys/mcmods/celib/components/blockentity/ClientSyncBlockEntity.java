package lv.cebbys.mcmods.celib.components.blockentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public abstract class ClientSyncBlockEntity extends BlockEntity {
    public ClientSyncBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return writeToClientNbt(new NbtCompound());
    }

    @Override
    public void markDirty() {
        this.markSync();
        super.markDirty();
    }

    protected void markSync() {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.getChunkManager().markForUpdate(pos);
        } else if (world instanceof ClientWorld) {
            reloadModel();
        }
    }

    public NbtCompound writeToClientNbt(NbtCompound nbt) {
        writeNbt(nbt);
        markDirty();
        return nbt;
    }

    @Environment(EnvType.CLIENT)
    public void reloadModel() {
        MinecraftClient.getInstance().worldRenderer.scheduleBlockRenders(
                pos.getX(), pos.getY(), pos.getZ(),
                pos.getX(), pos.getY(), pos.getZ()
        );
    }
}
