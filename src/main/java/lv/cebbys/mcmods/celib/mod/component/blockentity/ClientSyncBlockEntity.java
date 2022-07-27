package lv.cebbys.mcmods.celib.mod.component.blockentity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class ClientSyncBlockEntity extends BlockEntity {
    public ClientSyncBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveClientMetadata(new CompoundTag());
    }

    @Override
    public void setChanged() {
        this.synchronizeChanged();
        super.setChanged();
    }

    protected void synchronizeChanged() {
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getChunkSource().blockChanged(getBlockPos());
        } else if (getLevel() instanceof ClientLevel) {
            reloadModel();
        }
    }

    public CompoundTag saveClientMetadata(CompoundTag nbt) {
        saveAdditional(nbt);
        setChanged();
        return nbt;
    }

    @Environment(EnvType.CLIENT)
    public void reloadModel() {
        Minecraft.getInstance().levelRenderer.setBlocksDirty(
                getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(),
                getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ()
        );
    }
}
