package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.ryidc.Ermu.Arena.Arena;

/**
 * 监听玩家执行指令
 * @author Er_mu
 *
 */
public class ArenaPlayerCommandEvent implements Listener{
	@EventHandler
	public void playerCommandEvent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		//如果是op 直接无视
		if(p.hasPermission("*")) {
			return;
		}
		//判断玩家是否在竞技场
		if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
			//取玩家当前执行指令 判断白名单内是否有此
			if(!Arena.whiteCommandList.contains(e.getMessage())) {
				//取消
				e.setCancelled(true);
				p.sendMessage("§c竞技场内禁止执行指令!");
			}
		}
	}
}
