package com.ryidc.Ermu.save;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * 读取保存目录下全部竞技场文件 并将其放入到主类容器
 * @author Er_mu
 *
 */
public class ArReader{
	public static void read(String path,boolean bool) {
		//为保证读取 每次清空竞技场容器 判断是否需要清空
		if(bool) {
			Arena.arenaList.clear();
		}
		//选择文件
		File f = new File(path);
		if(!f.exists()) {
			return;
		}
		//判断
		if(f.isDirectory()) {
			//目录 遍历目录树
			for(File temp : f.listFiles()) {
				//递归
				read(temp.getAbsolutePath(),false);
			}
		}else {

			//id
			String id = f.getName().split("\\.")[0];
			//新建Yaml配置文件
			FileConfiguration config = YamlConfiguration.loadConfiguration(f);
			//序列化 并放入容器
			ArenaInfo temp = new ArenaInfo();
			//处理坐标
			List<String> locationArr = config.getStringList(id+".waitlocation");
			List<String> locationArr2 = config.getStringList(id+".location1");
			List<String> locationArr3 = config.getStringList(id+".location2");
			Location waitLocation;
			Location loc1 = new Location(Bukkit.getWorld(locationArr2.get(0)),Double.valueOf(locationArr2.get(1)),Double.valueOf(locationArr2.get(2)),Double.valueOf(locationArr2.get(3)));
			Location loc2 = new Location(Bukkit.getWorld(locationArr3.get(0)),Double.valueOf(locationArr3.get(1)),Double.valueOf(locationArr3.get(2)),Double.valueOf(locationArr3.get(3)));
			if(locationArr.size()==0) {
				waitLocation = null;
			}else {
				//新建location对象
				waitLocation = new Location(Bukkit.getWorld(locationArr.get(0)),Double.valueOf(locationArr.get(1)),Double.valueOf(locationArr.get(2)),Double.valueOf(locationArr.get(3)));
			}
			//处理挑战次数map
			Map<String,Integer> number = new HashMap<>();
			//获取对应的子选项
			ConfigurationSection cs = config.getConfigurationSection(id+".playerNumber");
			//遍历
			for(String a : cs.getKeys(false)) {
				number.put(a, config.getInt(id+".playerNumber."+a));
			}
			temp.setId(config.getString(id+".id"));
			temp.setMode(config.getString(id+".mode"));
			//最大挑战次数
			temp.setMaxNumber(config.getInt(id+".maxNumber"));
			temp.setMinPerson(config.getInt(id+".minPerson"));
			temp.setMaxPerson(config.getInt(id+".maxPerson"));
			//设置金币
			temp.setWinMoney(config.getInt(id+".winmoney"));
			temp.setLoseMoney(config.getInt(id+".losemoney"));
			temp.setTime(config.getInt(id+".time"));
			//设置游戏时长
			temp.setGameTime(config.getInt(id+".gametime"));
			temp.setWeek(config.getInt(id+".week"));
			temp.setHour(config.getInt(id+".hour"));
			//判断模式
			if(temp.getMode().equals("team")) {
				List<String> LocationArr4 = config.getStringList(id+".firstLocation");
				List<String> LocationArr5 = config.getStringList(id+".secondLocation");
				Location firstLocation = null;
				Location secondLocation = null;
				if(LocationArr4.size()!=0) {
					firstLocation = new Location(Bukkit.getWorld(LocationArr4.get(0)),Double.valueOf(LocationArr4.get(1)),Double.valueOf(LocationArr4.get(2)),Double.valueOf(LocationArr4.get(3)));
				}
				if(LocationArr5.size()!=0) {
					secondLocation = new Location(Bukkit.getWorld(LocationArr5.get(0)),Double.valueOf(LocationArr5.get(1)),Double.valueOf(LocationArr5.get(2)),Double.valueOf(LocationArr5.get(3)));
				}
				temp.setFirstLocation(firstLocation);
				temp.setSecondLocation(secondLocation);
				//设置物品 判断其路径是否存在
				if(config.isSet(id+".winitem")) {
					//写入
					temp.setWinItem(config.getItemStack(id+".winitem"));
				}
				if(config.isSet(id+".loseitem")) {
					temp.setLoseItem(config.getItemStack(id+".loseitem"));
				}
				
			}else {
				//个人竞技写入 一二三名数据
				if(config.isSet(id+".firstitem")){
					temp.setFirstItem(config.getItemStack(id+".firstitem"));
				}
				if(config.isSet(id+".seconditem")) {
					temp.setSecondItem(config.getItemStack(id+".seconditem"));
				}
				if(config.isSet(id+".thirditem")) {
					temp.setThirdItem(config.getItemStack(id+".thirditem"));
				}
			}
			//设置玩家挑战次数
			temp.setNumber(number);
			//设置等待大厅坐标 竞技场坐标
			temp.setWaitLocation(waitLocation);
			//设置坐标
			temp.setLoc1(loc1);
			temp.setLoc2(loc2);
			//设置进入物品
			if(config.isSet(id+".joinitem")) {
				temp.setJoinItem(config.getItemStack(id+".joinitem"));
			}
			
			//设置完成 以id为key放入容器
			Arena.arenaList.put(id, temp);
		}
	}
}
