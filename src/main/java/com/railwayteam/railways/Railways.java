/*
 * Steam 'n' Rails
 * Copyright (c) 2022-2024 The Railways Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

 package com.railwayteam.railways;

 import com.mojang.brigadier.CommandDispatcher;
 import com.railwayteam.railways.base.data.CRTagGen;
 import com.railwayteam.railways.base.data.RailwaysHatOffsetGenerator;
 import com.railwayteam.railways.base.data.compat.emi.EmiExcludedTagGen;
 import com.railwayteam.railways.base.data.compat.emi.EmiRecipeDefaultsGen;
 import com.railwayteam.railways.base.data.lang.CRLangGen;
 import com.railwayteam.railways.base.data.recipe.RailwaysMechanicalCraftingRecipeGen;
 import com.railwayteam.railways.base.data.recipe.RailwaysSequencedAssemblyRecipeGen;
 import com.railwayteam.railways.base.data.recipe.RailwaysStandardRecipeGen;
 import com.railwayteam.railways.compat.Mods;
 import com.railwayteam.railways.config.CRConfigs;
 import com.railwayteam.railways.multiloader.Loader;
 import com.railwayteam.railways.registry.CRCommands;
 import com.railwayteam.railways.registry.CRPackets;
 import com.railwayteam.railways.util.MethodVarHandleUtils;
 import com.railwayteam.railways.util.Utils;
 import com.simibubi.create.Create;
 import com.simibubi.create.foundation.data.CreateRegistrate;
 import com.simibubi.create.foundation.item.ItemDescription;
 import com.simibubi.create.foundation.item.KineticStats;
 import com.simibubi.create.foundation.item.TooltipHelper;
 import com.simibubi.create.foundation.item.TooltipModifier;
 import com.tterrag.registrate.providers.ProviderType;

import net.createmod.ponder.foundation.registration.PonderLocalization;
import net.minecraft.commands.CommandSourceStack;
 import net.minecraft.data.DataGenerator;
 import net.minecraft.resources.ResourceLocation;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.spongepowered.asm.mixin.MixinEnvironment;
 
 import java.io.FileWriter;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.util.function.BiConsumer;
 import java.util.function.Function;
 
 public class Railways {
   public static final String MOD_ID = "railways";
   public static final String ID_NAME = "Railways";
   public static final String NAME = "Steam 'n' Rails";
   public static final Logger LOGGER = LoggerFactory.getLogger(ID_NAME);
   // Only used for datafixers, bump whenever a block changes id etc. (should not be bumped multiple times within a release)
   public static final int DATA_FIXER_VERSION = 2;
 
   private static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);
 
   static {
     REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
         .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
   }
 
   private static void migrateConfig(Path path, Function<String, String> converter) {
     Convert: try {
 
       String str = new String(Files.readAllBytes(path));
       if (str.contains("#General settings") || str.contains("[general]")) { // we found a legacy config
         String migrated;
         try {
           migrated = converter.apply(new String(Files.readAllBytes(path)));
         } catch (IOException e) {
           break Convert;
         }
         try (FileWriter writer = new FileWriter(path.toFile())) {
           writer.write(migrated);
         }
       }
     } catch (IOException ignored) {}
   }
 
   public static void init() {
     String createVersion = MethodVarHandleUtils.getStaticField(Create.class, "VERSION", String.class, "UNKNOWN");
     LOGGER.info("{} v{} initializing! Commit hash: {} on Create version: {} on platform: {}", NAME, RailwaysBuildInfo.VERSION, RailwaysBuildInfo.GIT_COMMIT, createVersion, Loader.getFormatted());
     
     Path configDir = Utils.configDir();
     Path clientConfigDir = configDir.resolve(MOD_ID + "-client.toml");
     migrateConfig(clientConfigDir, CRConfigs::migrateClient);
 
     Path commonConfigDir = configDir.resolve(MOD_ID + "-common.toml");
     migrateConfig(commonConfigDir, CRConfigs::migrateCommon);
     
     ModSetup.register();
     finalizeRegistrate();
 
     registerCommands(CRCommands::register);
     CRPackets.PACKETS.registerC2SListener();
 
     if (Utils.isDevEnv() && !Mods.BYG.isLoaded && !Mods.SODIUM.isLoaded && !Utils.isEnvVarTrue("DATAGEN")) // force all mixins to load in dev
       MixinEnvironment.getCurrentEnvironment().audit();
   }
 
   public static ResourceLocation asResource(String name) {
     return  ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
   }
 
   public static void gatherData(DataGenerator.PackGenerator gen) {
     REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CRTagGen::generateBlockTags);
     REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CRTagGen::generateItemTags);
     REGISTRATE.addDataGenerator(ProviderType.LANG, CRLangGen::generate);
     PonderLocalization.provideRegistrateLang(REGISTRATE);
     gen.addProvider(RailwaysSequencedAssemblyRecipeGen::new);
     gen.addProvider(RailwaysStandardRecipeGen::new);
     gen.addProvider(RailwaysMechanicalCraftingRecipeGen::create);
     gen.addProvider(EmiExcludedTagGen::new);
     gen.addProvider(EmiRecipeDefaultsGen::new);
     gen.addProvider(RailwaysHatOffsetGenerator::new);
   }
 
   public static CreateRegistrate registrate() {
     return REGISTRATE;
   }
 
   public static void finalizeRegistrate() {
     throw new AssertionError();
   }
 
   public static void registerCommands(BiConsumer<CommandDispatcher<CommandSourceStack>, Boolean> consumer) {
     throw new AssertionError();
   }
 }