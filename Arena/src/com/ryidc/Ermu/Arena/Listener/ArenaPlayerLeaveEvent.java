package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;
import com.ryidc.Ermu.game.TeamStart;

/**
 * 玩家离开事件
 * @author Er_mu
 *
 */
public class ArenaPlayerLeaveEvent implements Listener{
	@EventHandler
	public void PlayerLeaveEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		String id = Arena.playerList.get(p.getName()).getArenaId();
		//判断此玩家是否在竞技场中
		if(id==null) {
			return;
		}else {
			//滞空此玩家的竞技场状态
			Arena.playerList.get(p.getName()).setArenaId(null);
			//在容器中删除此玩家
			Arena.arenaList.get(id).getPlayerList().remove(p.getName());
			//重置此玩家的击杀数
			Arena.playerList.get(p.getName()).setKillNumber(0);
			//判断竞技场开始状态
			if(Arena.arenaList.get(id).getStartBool()) {
				//已经开始
				//向竞技场全部玩家发送一条消息
				GameStartListener.senderMessageToAllPlayer("§a玩家§e"+p.getName()+"§a离开了游戏!", id);
				//判断游戏模式
				if(Arena.arenaList.get(id).getMode().equals("team")) {
					//取游戏中数据
					TeamStart ts = (TeamStart)Arena.gameStartData.get(id);
					if(ts.getBlueTeam().containsKey(p.getName())) {
						//删除
						ts.getBlueTeam().remove(p.getName());
					}else if(ts.getRedTeam().containsKey(p.getName())) {
						ts.getRedTeam().remove(p.getName());
					}
				}
			}
			else {
				//向竞技场全部玩家发送一条消息
				GameStartListener.senderMessageToAllPlayer("§a玩家§e"+p.getName()+"§a离开了游戏,还差"+(Arena.arenaList.get(id).getMinPerson()-Arena.arenaList.get(id).getPlayerList().size())+"人", id);
				
			}	
		}
	}
}
