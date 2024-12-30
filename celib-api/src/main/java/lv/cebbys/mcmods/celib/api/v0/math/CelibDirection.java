package lv.cebbys.mcmods.celib.api.v0.math;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CelibDirection {
    DOWN(CelibAxisDirection.NEGATIVE, CelibAxis.Y),
    UP(CelibAxisDirection.POSITIVE, CelibAxis.Y),
    NORTH(CelibAxisDirection.NEGATIVE, CelibAxis.Z),
    SOUTH(CelibAxisDirection.POSITIVE, CelibAxis.Z),
    WEST(CelibAxisDirection.NEGATIVE, CelibAxis.X),
    EAST(CelibAxisDirection.POSITIVE, CelibAxis.X);

    private final CelibAxisDirection direction;
    private final CelibAxis axis;

    public static CelibDirection from(CelibAxis axis, CelibAxisDirection direction) {
        return Arrays.stream(CelibDirection.values())
                .filter((var d) -> d.axis == axis && d.direction == direction)
                .findFirst()
                .orElseThrow();
    }
}
