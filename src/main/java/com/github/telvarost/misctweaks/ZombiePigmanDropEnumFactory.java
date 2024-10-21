package com.github.telvarost.misctweaks;

import com.google.common.collect.ImmutableMap;
import net.glasslauncher.mods.gcapi3.api.*;
import net.glasslauncher.mods.gcapi3.impl.SeptFunction;
import net.glasslauncher.mods.gcapi3.impl.object.ConfigEntryHandler;
import net.glasslauncher.mods.gcapi3.impl.object.entry.EnumConfigEntryHandler;

import java.lang.reflect.*;
import java.util.function.*;

public class ZombiePigmanDropEnumFactory implements ConfigFactoryProvider {

    @Override
    public void provideLoadFactories(ImmutableMap.Builder<Type, SeptFunction<String, ConfigEntry, Field, Object, Boolean, Object, Object, ConfigEntryHandler<?>>> immutableBuilder) {
        immutableBuilder.put(ZombiePigmanDropEnum.class, ((id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrOrdinal, defaultEnum) ->
        {
            int enumOrdinal;
            if(enumOrOrdinal instanceof Integer ordinal) {
                enumOrdinal = ordinal;
            }
            else {
                enumOrdinal = ((ZombiePigmanDropEnum) enumOrOrdinal).ordinal();
            }
            return new EnumConfigEntryHandler<ZombiePigmanDropEnum>(id, configEntry, parentField, parentObject, isMultiplayerSynced, enumOrdinal, ((ZombiePigmanDropEnum) defaultEnum).ordinal(), ZombiePigmanDropEnum.class);
        }));
    }

    @Override
    public void provideSaveFactories(ImmutableMap.Builder<Type, Function<Object, Object>> immutableBuilder) {
        immutableBuilder.put(ZombiePigmanDropEnum.class, enumEntry -> enumEntry);
    }
}
