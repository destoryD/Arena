package com.ryidc.Ermu.Arena.Listener;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.PlayerInfo;

public class ArenaPlayerJoinEvent implements Listener {
	@EventHandler
	//进入一个玩家就将其放到容器中
	public void PlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		//玩家容器中没有 就将其放入
		if(!(Arena.playerList.containsKey(p.getName()))) {
			//放入容器
			Arena.playerList.put(p.getName(), new PlayerInfo());
		}
		//为防止此玩家在竞技场内 判断玩家位置是否在竞技场中
		//遍历所有竞技场数据
		Set<String> keyset = Arena.arenaList.keySet();
		for(String temp : keyset) {
			//取坐标
			Location loc1 = Arena.arenaList.get(temp).getLoc1();
			
			//首先判定是否在一个世界中
			if(p.getWorld().getName().equals(loc1.getWorld().getName())) {
				//新建另一个坐标对象
				Location loc2 = Arena.arenaList.get(temp).getLoc2();
				//玩家坐标
				Location loc = p.getLocation();
				//将各个坐标 从大到小排列 判定X和Z 不判定Y
				Double minX = loc1.getX()<loc2.getY()?loc1.getX():loc2.getX();
				Double maxX = loc1.getX()>loc2.getY()?loc1.getX():loc2.getX();
				Double minZ = loc1.getZ()<loc2.getZ()?loc1.getZ():loc2.getZ();
				Double maxZ = loc1.getZ()>loc2.getZ()?loc1.getZ():loc2.getZ();
				//判定坐标
				if(loc.getX()>minX&&loc.getX()<maxX) {
					if(loc.getZ()>minZ&&loc.getZ()<maxZ) {
						//因为ess spawn没有权限具有一定延迟 所以临时添加*权限
						p.setOp(true);
						//将其传送到主城
						Bukkit.dispatchCommand(p, "spawn");
						p.setOp(false);
						//发送消息
						p.sendMessage("§a由于您上次退出时在竞技场,已经将您传送至主城!");
						break;
					}
				}
				
			}
		}
	}
	
}
