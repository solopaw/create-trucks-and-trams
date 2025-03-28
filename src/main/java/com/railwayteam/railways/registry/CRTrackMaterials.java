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

 package com.railwayteam.railways.registry;

 import com.google.common.collect.ImmutableSet;
 import com.railwayteam.railways.Railways;
 import com.railwayteam.railways.content.custom_tracks.NoCollisionCustomTrackBlock;
 import com.railwayteam.railways.content.custom_tracks.monorail.MonorailTrackBlock;
 import com.railwayteam.railways.content.custom_tracks.narrow_gauge.NarrowGaugeTrackBlock;
 import com.railwayteam.railways.content.custom_tracks.phantom.PhantomTrackBlock;
 import com.railwayteam.railways.content.custom_tracks.wide_gauge.WideGaugeTrackBlock;
 import com.railwayteam.railways.mixin.AccessorBlockEntityType;
 import com.simibubi.create.content.trains.track.TrackBlock;
 import com.simibubi.create.content.trains.track.TrackMaterial;
 import com.simibubi.create.content.trains.track.TrackMaterial.TrackType;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.resources.ResourceLocation;
 import net.minecraft.world.item.Items;
 import net.minecraft.world.level.block.Block;
 import net.minecraft.world.level.block.Blocks;
 import net.minecraft.world.level.block.entity.BlockEntityType;
 import org.jetbrains.annotations.Nullable;
 
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Set;
 
 import static com.simibubi.create.content.trains.track.TrackMaterialFactory.make;
 
 public class CRTrackMaterials {
     public static final TrackMaterial
         ACACIA = make(Railways.asResource("acacia"))
             .lang("Acacia")
             .block(() -> CRBlocks.ACACIA_TRACK)
             .particle(ResourceLocation.parse("block/acacia_planks"))
             .sleeper(Blocks.ACACIA_SLAB)
             .standardModels()
             .build(),
         BIRCH = make(Railways.asResource("birch"))
             .lang("Birch")
             .block(() -> CRBlocks.BIRCH_TRACK)
             .particle(ResourceLocation.parse("block/birch_planks"))
             .sleeper(Blocks.BIRCH_SLAB)
             .standardModels()
             .build(),
         CRIMSON = make(Railways.asResource("crimson"))
             .lang("Crimson")
             .block(() -> CRBlocks.CRIMSON_TRACK)
             .particle(ResourceLocation.parse("block/crimson_planks"))
             .sleeper(Blocks.CRIMSON_SLAB)
             .rails(Items.GOLD_NUGGET)
             .standardModels()
             .build(),
         DARK_OAK = make(Railways.asResource("dark_oak"))
             .lang("Dark Oak")
             .block(() -> CRBlocks.DARK_OAK_TRACK)
             .particle(ResourceLocation.parse("block/dark_oak_planks"))
             .sleeper(Blocks.DARK_OAK_SLAB)
             .standardModels()
             .build(),
         JUNGLE = make(Railways.asResource("jungle"))
             .lang("Jungle")
             .block(() -> CRBlocks.JUNGLE_TRACK)
             .particle(ResourceLocation.parse("block/jungle_planks"))
             .sleeper(Blocks.JUNGLE_SLAB)
             .standardModels()
             .build(),
         OAK = make(Railways.asResource("oak"))
             .lang("Oak")
             .block(() -> CRBlocks.OAK_TRACK)
             .particle(ResourceLocation.parse("block/oak_planks"))
             .sleeper(Blocks.OAK_SLAB)
             .standardModels()
             .build(),
         SPRUCE = make(Railways.asResource("spruce"))
             .lang("Spruce")
             .block(() -> CRBlocks.SPRUCE_TRACK)
             .particle(ResourceLocation.parse("block/spruce_planks"))
             .sleeper(Blocks.SPRUCE_SLAB)
             .standardModels()
             .build(),
         WARPED = make(Railways.asResource("warped"))
             .lang("Warped")
             .block(() -> CRBlocks.WARPED_TRACK)
             .particle(ResourceLocation.parse("block/warped_planks"))
             .sleeper(Blocks.WARPED_SLAB)
             .rails(Items.GOLD_NUGGET)
             .standardModels()
             .build(),
         BLACKSTONE = make(Railways.asResource("blackstone"))
             .lang("Blackstone")
             .block(() -> CRBlocks.BLACKSTONE_TRACK)
             .particle(ResourceLocation.parse("block/blackstone"))
             .sleeper(Blocks.BLACKSTONE_SLAB)
             .rails(Items.GOLD_NUGGET)
             .standardModels()
             .build(),
         MANGROVE = make(Railways.asResource("mangrove"))
             .lang("Mangrove")
             .block(() -> CRBlocks.MANGROVE_TRACK)
             .particle(ResourceLocation.parse("block/mangrove_planks"))
             .sleeper(Blocks.MANGROVE_SLAB)
             .standardModels()
             .build(),
         CHERRY = make(Railways.asResource("cherry"))
             .lang("Cherry")
             .block(() -> CRBlocks.CHERRY_TRACK)
             .particle(ResourceLocation.parse("block/cherry_planks"))
             .sleeper(Blocks.CHERRY_SLAB)
             .standardModels()
             .build(),
         BAMBOO = make(Railways.asResource("bamboo"))
             .lang("Bamboo")
             .block(() -> CRBlocks.BAMBOO_TRACK)
             .particle(ResourceLocation.parse("block/bamboo_block"))
             .sleeper(Items.BAMBOO)
             .standardModels()
             .build(),
         STRIPPED_BAMBOO = make(Railways.asResource("stripped_bamboo"))
             .lang("Stripped Bamboo")
             .block(() -> CRBlocks.STRIPPED_BAMBOO_TRACK)
             .particle(ResourceLocation.parse("block/bamboo_planks"))
             .sleeper(Blocks.BAMBOO_SLAB)
             .standardModels()
             .build(),
         MONORAIL = make(Railways.asResource("monorail"))
             .lang("Monorail")
             .block(() -> CRBlocks.MONORAIL_TRACK)
             .particle(Railways.asResource("block/monorail/monorail"))
             .trackType(CRTrackMaterials.CRTrackType.MONORAIL)
             .noRecipeGen()
             .customModels(
                 () -> () -> PartialModel.of(Railways.asResource("block/monorail/monorail/monorail_half")),
                 () -> () -> PartialModel.of(Railways.asResource("block/empty")),
                 () -> () -> PartialModel.of(Railways.asResource("block/empty"))
             )
             .build(),
         ENDER = make(Railways.asResource("ender"))
             .lang("Ender")
             .block(() -> CRBlocks.ENDER_TRACK)
             .particle(ResourceLocation.parse("block/end_stone"))
             .sleeper(Blocks.END_STONE_BRICK_SLAB)
             .standardModels()
             .build(),
         TIELESS = make(Railways.asResource("tieless"))
             .lang("Tieless")
             .block(() -> CRBlocks.TIELESS_TRACK)
             .particle(ResourceLocation.parse("block/glass"))
             .sleeper(Blocks.GLASS_PANE)
             .customBlockFactory(NoCollisionCustomTrackBlock::new)
             .standardModels()
             .build(),
         PHANTOM = make(Railways.asResource("phantom"))
             .lang("Phantom")
             .block(() -> CRBlocks.PHANTOM_TRACK)
             .particle(ResourceLocation.parse("block/glass"))
             .noRecipeGen()
             .trackType(CRTrackType.UNIVERSAL)
             .customBlockFactory(PhantomTrackBlock::new)
             .standardModels()
             .build(),
         WIDE_GAUGE_ANDESITE = wideVariant(TrackMaterial.ANDESITE),
         NARROW_GAUGE_ANDESITE = narrowVariant(TrackMaterial.ANDESITE)
         ;
 
     public static final Map<TrackMaterial, TrackMaterial> WIDE_GAUGE = new HashMap<>();
     public static final Map<TrackMaterial, TrackMaterial> WIDE_GAUGE_REVERSE = new HashMap<>();
 
     public static final Map<TrackMaterial, TrackMaterial> NARROW_GAUGE = new HashMap<>();
     public static final Map<TrackMaterial, TrackMaterial> NARROW_GAUGE_REVERSE = new HashMap<>();
 
     static {
         WIDE_GAUGE.put(TrackMaterial.ANDESITE, WIDE_GAUGE_ANDESITE);
         WIDE_GAUGE_REVERSE.put(WIDE_GAUGE_ANDESITE, TrackMaterial.ANDESITE);
         for (TrackMaterial baseMaterial : TrackMaterial.allFromMod(Railways.MOD_ID)) {
             if (baseMaterial.trackType != TrackType.STANDARD)
                 continue;
 
             TrackMaterial wideMaterial = wideVariant(baseMaterial);
             WIDE_GAUGE.put(baseMaterial, wideMaterial);
             WIDE_GAUGE_REVERSE.put(wideMaterial, baseMaterial);
         }
 
         NARROW_GAUGE.put(TrackMaterial.ANDESITE, NARROW_GAUGE_ANDESITE);
         NARROW_GAUGE_REVERSE.put(NARROW_GAUGE_ANDESITE, TrackMaterial.ANDESITE);
         for (TrackMaterial baseMaterial : TrackMaterial.allFromMod(Railways.MOD_ID)) {
             if (baseMaterial.trackType != TrackType.STANDARD)
                 continue;
 
             TrackMaterial narrowMaterial = narrowVariant(baseMaterial);
             NARROW_GAUGE.put(baseMaterial, narrowMaterial);
             NARROW_GAUGE_REVERSE.put(narrowMaterial, baseMaterial);
         }
     }
 
     public static TrackMaterial getWide(TrackMaterial material) {
         return WIDE_GAUGE.get(material);
     }
 
     @Nullable
     public static TrackMaterial getBaseFromWide(TrackMaterial material) {
         if (!WIDE_GAUGE_REVERSE.containsKey(material))
             return null;
         return WIDE_GAUGE_REVERSE.get(material);
     }
 
     private static TrackMaterial wideVariant(TrackMaterial material) {
         String path = "";
         if (!material.id.getNamespace().equals(Railways.MOD_ID))
             path = material.id.getNamespace() + "_";
         path += material.id.getPath() + "_wide";
         return make(Railways.asResource(path))
             .lang("Wide " + material.langName)
             .trackType(CRTrackType.WIDE_GAUGE)
             .block(() -> CRBlocks.WIDE_GAUGE_TRACKS.get(WIDE_GAUGE.get(material)))
             .particle(material.particle)
             .noRecipeGen()
             .standardModels()
             .build();
     }
 
     public static TrackMaterial getNarrow(TrackMaterial material) {
         return NARROW_GAUGE.get(material);
     }
 
     @Nullable
     public static TrackMaterial getBaseFromNarrow(TrackMaterial material) {
         if (!NARROW_GAUGE_REVERSE.containsKey(material))
             return null;
         return NARROW_GAUGE_REVERSE.get(material);
     }
 
     private static TrackMaterial narrowVariant(TrackMaterial material) {
         String path = "";
         if (!material.id.getNamespace().equals(Railways.MOD_ID))
             path = material.id.getNamespace() + "_";
         path += material.id.getPath() + "_narrow";
         return make(Railways.asResource(path))
             .lang("Narrow " + material.langName)
             .trackType(CRTrackType.NARROW_GAUGE)
             .block(() -> CRBlocks.NARROW_GAUGE_TRACKS.get(NARROW_GAUGE.get(material)))
             .particle(material.particle)
             .noRecipeGen()
             .standardModels()
             .build();
     }
 
     public static class CRTrackType extends TrackType {
         public static final TrackType MONORAIL = new CRTrackType(Railways.asResource("monorail"), MonorailTrackBlock::new);
 
         public static final TrackType WIDE_GAUGE = new CRTrackType(Railways.asResource("wide_gauge"), WideGaugeTrackBlock::new);
 
         public static final TrackType NARROW_GAUGE = new CRTrackType(Railways.asResource("narrow_gauge"), NarrowGaugeTrackBlock::new);
 
         public static final TrackType UNIVERSAL = new CRTrackType(Railways.asResource("universal"), TrackBlock::new);
 
         public CRTrackType(ResourceLocation id, TrackBlockFactory factory) {
             super(id, factory);
         }
     }
 
     public static void register() {}
 
     public static void addToBlockEntityType(TrackBlock block) {
         BlockEntityType<?> type;
         try {
             type = block.getBlockEntityType();
         } catch (NullPointerException ignored) {
             return;
         }
         Set<Block> validBlocks = ((AccessorBlockEntityType) type).getValidBlocks();
         validBlocks = new ImmutableSet.Builder<Block>()
             .add(validBlocks.toArray(Block[]::new))
             .add(block)
             .build();
         ((AccessorBlockEntityType) type).setValidBlocks(validBlocks);
     }
 }