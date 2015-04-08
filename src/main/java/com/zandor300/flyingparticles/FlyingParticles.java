/**
 * Copyright 2015 Zandor Smith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zandor300.flyingparticles;

import com.zandor300.flyingparticles.commands.FlyingParticlesCommand;
import com.zandor300.flyingparticles.utilities.ParticleEffect;
import com.zandor300.zsutilities.commandsystem.CommandManager;
import com.zandor300.zsutilities.utilities.Chat;
import com.zandor300.zsutilities.ZSUtilities;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Zandor Smith
 * @since 1.0.0
 */
public class FlyingParticles extends JavaPlugin {

	private static Chat chat = new Chat("FlyingParticles");
	private static FlyingParticles plugin;

	public static Chat getChat() {
		return chat;
	}

	public static FlyingParticles getPlugin() {
		return plugin;
	}

	@Override
	public void onEnable() {
		chat.sendConsoleMessage("Setting things up...");

		plugin = this;
		ZSUtilities.addDependency(this);

		chat.sendConsoleMessage("Sending metrics...");
		try {
			new Metrics(this).start();
			chat.sendConsoleMessage("Submitted stats to MCStats.org.");
		} catch (IOException e) {
			chat.sendConsoleMessage("Couldn't submit stats to MCStats.org...");
		}

		chat.sendConsoleMessage("Starting timers...");
		Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers())
					if(player.hasPermission("flyingparticles.particles"))
						if(player.isFlying()) {
							if(player.getLocation().getBlock().getType().equals(Material.WATER) || player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER)) {
								ParticleEffect.sendToLocation(ParticleEffect.AIR_BUBBLE, player.getLocation(), 1, 0.5f, 1, 0, 20);
								//com.zandor300.zsutilities.utilities.particles.ParticleEffect.WATER_BUBBLE.display(1, 0.5f, 1, 0, 20, player.getLocation(), 16);
							} else {
								ParticleEffect.sendToLocation(ParticleEffect.CLOUD, player.getLocation(), 1, 0.5f, 1, 0, 20);
								//com.zandor300.zsutilities.utilities.particles.ParticleEffect.CLOUD.display(1, 0.5f, 1, 0, 50, player.getLocation(), 16);
							}
						}
			}
		}, 20l, 2l);
		chat.sendConsoleMessage("Timers started.");

		CommandManager cm = new CommandManager();
		cm.registerCommand(new FlyingParticlesCommand(), this);

		chat.sendConsoleMessage("Everything is setup!");
		chat.sendConsoleMessage("Enabled.");
	}
}
