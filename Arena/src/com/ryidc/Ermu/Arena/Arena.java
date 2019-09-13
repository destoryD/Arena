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
	//��������Ϣ ʹ��HashMap
	public static Map<String,ArenaInfo> arenaList = new HashMap<>();
	//��Ϸ��ʼ�������
	public static Map<String,GameHandle> gameStartData = new HashMap<>();
	//�����Ϣ
	public static Map<String,PlayerInfo> playerList = new HashMap<>();
	//����������ʹ�õ�ָ��
	public static List<String> whiteCommandList = new ArrayList<>();
	//Set���ϴ洢����������
	public static final Set<String> ARENA_LIST = new HashSet<>();
	//����������·��
	public static String arenaSavePath;
	public static Economy economy = null;
	@Override
	public void onEnable() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		economy = economyProvider.getProvider();
		//��ʼ��·��
		arenaSavePath = getDataFolder().toString()+"/data";
		//Ϊ��ֹ���� �ڲ��������ʱ����ȡ�����������
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			playerList.put(player.getName(), new PlayerInfo());		
		}
		//��ʼ������������
		ARENA_LIST.add("team");
		ARENA_LIST.add("single");
		ARENA_LIST.add("guard");
		ARENA_LIST.add("boss");
		//����Ĭ�������ļ�
		saveDefaultConfig();
		//ע�������
		Bukkit.getPluginManager().registerEvents(new ArenaInteractEvent(this), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerJoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerLeaveEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerCommandEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerPvPEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerKillEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ArenaPlayerSpawnEvent(), this);
		//ע������
		getCommand("Arena").setExecutor(new ArenaCommand(this));
		//���ؾ������ļ�
		ArReader.read(arenaSavePath,true);
		//����ָ�������
		whiteCommandList = getConfig().getStringList("whiteCommand");
		//������Ϸ������ʱ��
		new GameStartListener().runTaskTimer(this, 0L, 20L);
		new GameListener().runTaskTimer(this, 0L, 20L);
		getLogger().info("�Զ��徺���������ɹ�");
	}
	@Override
	public void onDisable() {
		getLogger().info("�Զ��徺�����رճɹ�");
	}
}