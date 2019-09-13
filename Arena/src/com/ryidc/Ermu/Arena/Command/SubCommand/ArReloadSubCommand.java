package com.ryidc.Ermu.Arena.Command.SubCommand;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArReader;

public class ArReloadSubCommand implements ArSubCommand{
	private Plugin plug;
	public ArReloadSubCommand(Plugin plug) {
		this.plug = plug;
	}
	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("§a正在关闭所有已经开启的竞技场....");
		//清空游戏中数据
		Arena.gameStartData.clear();
		//将游戏中所有玩家请出
		Set<String> keySet = Arena.arenaList.keySet();
		for(String temp : keySet) {
			//每个竞技场的玩家列表
			Map<String,Player> player = Arena.arenaList.get(temp).getPlayerList();
			Set<String> playerKeySet = player.keySet();
			for(String playerName : playerKeySet) {
				//get到玩家
				Player thisPlayer = player.get(playerName);
				//将其传送到主城
				thisPlayer.setOp(true);
				Bukkit.dispatchCommand(thisPlayer, "spawn");
				thisPlayer.setOp(false);
				thisPlayer.sendMessage("§a由于插件重载,你被强制请出");
				//去玩家信息中重置此玩家击杀数
				Arena.playerList.get(playerName).setKillNumber(0);
				//重置竞技场状态
				Arena.playerList.get(playerName).setArenaId(null);
			}
		}
		p.sendMessage("§a关闭完成,重载中..");
		
		//重载配置文件
		this.plug.reloadConfig();
		//重载竞技场
		ArReader.read(Arena.arenaSavePath,true);
		//重载白名单列表
		Arena.whiteCommandList = this.plug.getConfig().getStringList("whiteCommand");
		//提示
		p.sendMessage("§a重载成功");
	}

}
