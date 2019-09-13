package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.game.TeamStart;

/**
 * 监听玩家pvp事件
 * 在大厅准备时 或 为同队伍玩家时
 * 不允许造成伤害
 * @author Er_mu
 *
 */
public class ArenaPlayerPvPEvent implements Listener{
	
	@EventHandler
	public void PlayerPvPEvent(EntityDamageByEntityEvent event) {
		//取伤害者与被伤害者
		Entity att = event.getDamager();
		if(att instanceof Player) {
			//造成伤害者
			Player p = (Player) att;
			//获取被伤害者
			Entity damage = event.getEntity();
			if(damage instanceof Player) {
				Player p2 = (Player) damage;
				//取玩家容器中竞技场状态
				if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
					//在竞技场内
					//判断是否在准备期间
					if(!Arena.arenaList.get(Arena.playerList.get(p.getName()).getArenaId()).getStartBool()) {
						//未开始 准备期间
						p.sendMessage("§a准备期间不可造成伤害!");
						event.setCancelled(true);
					}else {
						//已经开始 判断模式是否为team
						ArenaInfo ai = Arena.arenaList.get(Arena.playerList.get(p.getName()).getArenaId());
						if(ai.getMode().equals("team")) {
							//取运行时数据
							TeamStart ts = (TeamStart)Arena.gameStartData.get(ai.getId());
							//判断攻击者所在队伍
							if(ts.getBlueTeam().containsKey(p.getName())) {
								if(ts.getBlueTeam().containsKey(p2.getName())) {
									//同时此队伍中有被攻击者
									p.sendMessage("§a同队伍玩家不可造成伤害");
									event.setCancelled(true);
								}
							}else if(ts.getRedTeam().containsKey(p.getName())) {
								if(ts.getRedTeam().containsKey(p2.getName())) {
									p.sendMessage("§a同队伍玩家不可造成伤害");
									event.setCancelled(true);
								}
							}
						}
					}
				}
			}
			
		}
	}
}
