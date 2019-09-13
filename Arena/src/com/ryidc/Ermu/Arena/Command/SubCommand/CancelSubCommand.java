package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;

/**
 * 取消创建状态
 * @author Er_mu
 *
 */
public class CancelSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//取消掉该玩家的选点状态
		Arena.playerList.get(p.getName()).setBool(false);
		p.sendMessage("§c取消成功");
	}
	
}
