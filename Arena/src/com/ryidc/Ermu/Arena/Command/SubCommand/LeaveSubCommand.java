package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;
import com.ryidc.Ermu.game.TeamStart;

/**
 * 离开竞技场
 * @author Er_mu
 *
 */
public class LeaveSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		String id = Arena.playerList.get(p.getName()).getArenaId();
		//判断此玩家是否在竞技场中
		if(id==null) {
			p.sendMessage("§a你不在竞技场中,不能退出!");
			return;
		}else {
			//滞空此玩家的竞技场状态
			Arena.playerList.get(p.getName()).setArenaId(null);
			//在容器中删除此玩家
			Arena.arenaList.get(id).getPlayerList().remove(p.getName());
			//重置此玩家的击杀数
			Arena.playerList.get(p.getName()).setKillNumber(0);
			//因为ess spawn没有权限具有一定延迟 所以临时添加*权限
			p.setOp(true);
			//将其传送到主城
			Bukkit.dispatchCommand(p, "spawn");
			p.setOp(false);
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
				//判断是否有进入物品
				if(Arena.arenaList.get(id).getJoinItem()!=null) {
					//给此玩家返还物品
					p.getInventory().addItem(Arena.arenaList.get(id).getJoinItem());
					p.sendMessage("§a进入物品已经返还");
				}
				
			}	
			//给退出的玩家发送一条消息
			p.sendMessage("§a你退出了竞技场");
		}
		
	}

}
