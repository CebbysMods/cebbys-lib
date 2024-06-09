package lv.cebbys.mcmods.celib.api.component.material;

import lombok.Builder;
import lombok.Data;
import lv.cebbys.mcmods.celib.api.component.material.phase.CelibMatterState;

@Data
@Builder
public class CelibMaterial {
    private final CelibMatterState materState;

    public boolean isMatter(CelibMatterState state) {
        return materState.equals(state);
    }

    public boolean isSolidMatter() {
        return isMatter(CelibMatterState.SOLID);
    }

    public boolean isLiquidMatter() {
        return isMatter(CelibMatterState.LIQUID);
    }
}
