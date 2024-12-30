package lv.cebbys.mcmods.celib.api.v0.content.block;

import lv.cebbys.mcmods.celib.api.v0.content.block.properties.Property;

public interface CelibSlabBlock extends CelibBlock {
    Property<SlabLocation> SLAB_LOCATION = Property.ofEnum(SlabLocation.class);
    Property<SlabType> SLAB_TYPE = Property.ofEnum(SlabType.class);

    enum SlabType {
        SINGLE, DOUBLE
    }

    enum SlabLocation {
        TOP, BOTTOM
    }
}
