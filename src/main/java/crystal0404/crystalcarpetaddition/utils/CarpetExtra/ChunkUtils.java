/*
 * This file is part of the Crystal Carpet Addition project, licensed under the
 * GNU General Public License v3.0
 *
 * Copyright (C) 2024  Crystal0404 and contributors
 *
 * Crystal Carpet Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Crystal Carpet Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Crystal Carpet Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

package crystal0404.crystalcarpetaddition.utils.CarpetExtra;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;

public class ChunkUtils {
    public static final ChunkTicketType<ChunkPos> ENDER_PEARL_TICKET = ChunkTicketType.create("ender_pearl", Comparator.comparingLong(ChunkPos::toLong), 2);

    public static void addEnderPearlChunkTicket(Entity entity) {
        World world = entity.getWorld();
        Vec3d velocity = entity.getVelocity();
        if (world instanceof ServerWorld &&
                (Math.abs(velocity.x) > 0.001 || Math.abs(velocity.z) > 0.001))
        {
            Vec3d pos = entity.getPos();
            double nx = pos.x + velocity.x;
            double nz = pos.z + velocity.z;
            ChunkPos cp = new ChunkPos(MathHelper.floor(nx) >> 4, MathHelper.floor(nz) >> 4);
            ((ServerWorld) world).getChunkManager().addTicket(ENDER_PEARL_TICKET, cp, 2, cp);
        }
    }
}
