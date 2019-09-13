package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.PlayerInfo;
import com.ryidc.Ermu.game.TeamStart;
/**
 * 监听死亡事件 取到击杀者
 * 如果为团队模式则给整个团队增加
 * 个人则给个人增加
 * @author 14713
 *
 */
public class ArenaPlayerKillEvent implements Listener{
	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event) {
		//获取击杀者
		Player killer = event.getEntity().getKiller();
		Player death = event.getEntity();
		if(killer==null) {
			return;
		}
		//取此玩家所在的竞技场id
		String id = Arena.playerList.get(killer.getName()).getArenaId();
		if(id!=null) {
			//判断是否开始
			if(Arena.arenaList.get(id).getStartBool()) {
				//判断模式
				if(Arena.arenaList.get(id).getMode().equals("team")) {
					//get对应的竞技场运行数据
					TeamStart ts = (TeamStart)Arena.gameStartData.get(id);
					//判断所处队伍
					if(ts.getBlueTeam().containsKey(killer.getName())) {
						//给对应队伍+1
						ts.setBlueKillNumber(ts.getBlueKillNumber()+1);
						killer.sendMessage("§b你击杀了"+death.getName()+",当前队伍击杀数:"+ts.getBlueKillNumber()+",对方队伍击杀数"+ts.getRedKillNumber());
						
					}else if(ts.getRedTeam().containsKey(killer.getName())){
						ts.setRedKillNumber(ts.getRedKillNumber()+1);
						killer.sendMessage("§c你击杀了"+death.getName()+",当前队伍击杀数:"+ts.getRedKillNumber()+",对方队伍击杀数"+ts.getBlueKillNumber());
					}
				}else if(Arena.arenaList.get(id).getMode().equals("single")) {
					PlayerInfo pi = Arena.playerList.get(killer.getName());
					//个人竞技 给此玩家击杀数+1
					pi.setKillNumber(pi.getKillNumber()+1);
					killer.sendMessage("§e你击杀了§a"+death.getName()+"§e,当前击杀数"+pi.getKillNumber());
				}
			}
			
			
		}
		
		
	}
}
