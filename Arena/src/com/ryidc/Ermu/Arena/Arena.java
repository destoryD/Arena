package com.ryidc.Ermu.Arena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.ryidc.Ermu.Arena.Command.ArenaCommand;
import com.ryidc.Ermu.Arena.Listener.ArenaInteractEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerCommandEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerJoinEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerKillEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerLeaveEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerPvPEvent;
import com.ryidc.Ermu.Arena.Listener.ArenaPlayerSpawnEvent;
import com.ryidc.Ermu.game.GameHandle;
import com.ryidc.Ermu.game.GameListener;
import com.ryidc.Ermu.game.GameStartListener;
import com.ryidc.Ermu.save.ArReader;

import net.milkbowl.vault.economy.Economy;

public class Arena extends JavaPlugin{
	//竞技场信息 使用HashMap
	public static Map<String,ArenaInfo> arenaList = new HashMap<>();
	//游戏开始后的数据
	public static Map<String,GameHandle> gameStartData = new HashMap<>();
	//玩家信息
	public static Map<String,PlayerInfo> playerList = new HashMap<>();
	//竞技场可以使用的指令
	public static List<String> whiteCommandList = new ArrayList<>();
	//Set集合存储竞技场类型
	public static final Set<String> ARENA_LIST = new HashSet<>();
	//竞技场保存路径
	public static String arenaSavePath;
	public static Economy economy = null;
	@Override
	public void onEnable() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		economy = economyProvider.getProvider();
		//初始化路径
		arenaSavePath = getDataFolder().toString()+"/data";
		//为防止问题 在插件开启的时候拉取所有在线玩家
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			playerList.put(player.getName(), new PlayerInfo());		
		}
		//初始化竞技场类型
		ARENA_LIST.add("team");
		ARENA_LIST.add("single");
		ARENA_LIST.add("guard");
		ARENA_LIST.add("boss");
		//加载默认配置文件
		saveDefaultConfig();
		//注册监听器
		Bukkit.getPluginManager().registerEvents(new ArenaInteractEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerJoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerLeaveEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerCommandEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerPvPEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerKillEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerSpawnEvent(), this);
		//注册命令
		getCommand("Arena").setExecutor(new ArenaCommand(this));
		//加载竞技场文件
		ArReader.read(arenaSavePath,true);
		//加载指令白名单
		whiteCommandList = getConfig().getStringList("whiteCommand");
		//开启游戏监听定时器
		new GameStartListener().runTaskTimer(this, 0L, 20L);
		new GameListener().runTaskTimer(this, 0L, 20L);
		getLogger().info("自定义竞技场开启成功");
	}
	@Override
	public void onDisable() {
		getLogger().info("自定义竞技场关闭成功");
	}
}