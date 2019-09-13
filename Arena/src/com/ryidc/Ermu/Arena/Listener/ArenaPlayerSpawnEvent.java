package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.Arena.PlayerInfo;
import com.ryidc.Ermu.game.SingleStart;
import com.ryidc.Ermu.game.TeamStart;

public class ArenaPlayerSpawnEvent implements Listener{
	@EventHandler
	public void playerSpawnEvent(PlayerRespawnEvent event) {
		//获取玩家
		Player p = event.getPlayer();
		//获取玩家信息
		PlayerInfo playerInfo = Arena.playerList.get(p.getName());
		//判断此玩家是否在竞技场内
		if(playerInfo.getArenaId()!=null) {
			//在竞技场内 get到对应竞技场的信息
			ArenaInfo ai = Arena.arenaList.get(playerInfo.getArenaId());
			//判断是否开始
			if(ai.getStartBool()) {
				//判断模式
				if(ai.getMode().equals("team")) {
					//获取运行中数据
					TeamStart ts = (TeamStart) Arena.gameStartData.get(ai.getId());
					//团队 出生在对应出生点
					if(ts.getBlueTeam().containsKey(p.getName())) {
						//蓝队
						event.setRespawnLocation(ts.getBlueTeamSpawn());
					}else {
						//红队
						event.setRespawnLocation(ts.getRedTeamSpawn());
					}
					
				}else {
					//个人竞技
					//获取运行时数据
					SingleStart ss = (SingleStart)Arena.gameStartData.get(ai.getId());
					//随机生成坐标
					Location loc = ss.randomLocation(ai.getLoc1(), ai.getLoc2());
					//将玩家进行随机出生
					event.setRespawnLocation(loc);
					p.sendMessage("§e你复活了!");
				}
			}
			
		}
	}
}
