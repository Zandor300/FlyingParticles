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
package com.zandor300.flyingparticles.commands;

import com.zandor300.flyingparticles.FlyingParticles;
import com.zandor300.zsutilities.commandsystem.Command;
import org.bukkit.command.CommandSender;

/**
 * @author Zandor Smith
 * @since 1.0.0
 */
public class FlyingParticlesCommand extends Command {

	public FlyingParticlesCommand() {
		super("flyingparticles", "");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		FlyingParticles.getChat().sendMessage(sender, "FlyingParticles 1.0.0 by Zandor300");
	}
}
