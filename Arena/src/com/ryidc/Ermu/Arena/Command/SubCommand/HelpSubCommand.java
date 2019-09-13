package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;


/**
 * 提示类
 * @author Er_mu
 *
 */
public class HelpSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("§a提示暂时不想写了,看文档");
	}

}
