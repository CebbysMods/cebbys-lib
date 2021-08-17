package lv.cebbys.mcmods.celib.handlers.datagen.resources.asset.model.item;

import lv.cebbys.mcmods.celib.processor.api.ArrayResourceElement;
import lv.cebbys.mcmods.celib.processor.api.Resource;

@Resource
public class DisplayElement {
    @ArrayResourceElement(split = true, elements = {"x", "y", "z"})
    private Integer[] rotation;
    @ArrayResourceElement(split = true, elements = {"x", "y", "z"})
    private Float[] translation;
    @ArrayResourceElement(split = true, elements = {"x", "y", "z"})
    private Float[] scale;
}
