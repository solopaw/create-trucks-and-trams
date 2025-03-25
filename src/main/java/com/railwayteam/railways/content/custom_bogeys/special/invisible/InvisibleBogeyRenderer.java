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

 package com.railwayteam.railways.content.custom_bogeys.special.invisible;

 import com.mojang.blaze3d.vertex.PoseStack;
 import com.mojang.blaze3d.vertex.VertexConsumer;
 import com.simibubi.create.content.trains.bogey.BogeyBlockEntityRenderer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.StandardBogeyRenderer;
import com.simibubi.create.content.trains.entity.CarriageBogey;

import dev.engine_room.flywheel.api.material.Material;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
 
 public class InvisibleBogeyRenderer extends StandardBogeyRenderer  {
     @Override
     public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, boolean inContraption){
        super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, packedLight, packedOverlay, inContraption);
     }
 
     public BogeySizes.BogeySize getSize() {
         return BogeySizes.SMALL;
     }
 
     @Override
     public void initialiseContraptionModelData(Material materialManager, CarriageBogey carriageBogey) { }
 }