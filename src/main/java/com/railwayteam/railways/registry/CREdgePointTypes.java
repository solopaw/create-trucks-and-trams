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

 import com.railwayteam.railways.Railways;
 import com.railwayteam.railways.content.buffer.TrackBuffer;
 import com.railwayteam.railways.content.coupling.coupler.TrackCoupler;
 import com.railwayteam.railways.content.switches.TrackSwitch;
 import com.simibubi.create.content.trains.graph.EdgePointType;
 
 public class CREdgePointTypes {
     public static final EdgePointType<TrackCoupler> COUPLER = EdgePointType.register(Railways.asResource("coupler"), TrackCoupler::new);
     public static final EdgePointType<TrackSwitch> SWITCH = EdgePointType.register(Railways.asResource("switch"), TrackSwitch::new);
     public static final EdgePointType<TrackBuffer> BUFFER = EdgePointType.register(Railways.asResource("buffer"), TrackBuffer::new);
 
     @SuppressWarnings("EmptyMethod")
     public static void register() {}
 }