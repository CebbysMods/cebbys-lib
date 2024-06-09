package lv.cebbys.mcmods.celib.api.component.identifier;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@Data
@RequiredArgsConstructor
public class CelibResourceLocation {
    private final String namespace;
    private final String path;

    public <T> T as(BiFunction<String, String, T> mapper) {
        return mapper.apply(namespace, path);
    }
}
