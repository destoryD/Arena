package com.ryidc.Ermu.save;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * 将某个竞技场保存到配置文件中
 * @author Er_mu
 *
 */
public class ArSave{
	public static void save(ArenaInfo element) {
		//保存路径 判断是否存在
		File saveDir = new File(Arena.arenaSavePath);
		if(!saveDir.exists()) {
			//新建
			saveDir.mkdirs();
		}
		String id = element.getId();
		//新建config文件
		File config = new File(saveDir,id+".yml");
		//判断文件是否存在
		if(!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileConfiguration fc = YamlConfiguration.loadConfiguration(config);
		//处理坐标 将其处理为list
		//等待大厅坐标容器 world x y z
		List<String> locationArr = new ArrayList<>();
		//对角线坐标
		List<String> locationArr2 = new ArrayList<>();
		List<String> locationArr3 = new ArrayList<>();
		if(element.getWaitLocation()!=null) {
			locationArr.add(element.getWaitLocation().getWorld().getName());
			locationArr.add(element.getWaitLocation().getX()+"");
			locationArr.add(element.getWaitLocation().getY()+"");
			locationArr.add(element.getWaitLocation().getZ()+"");
		}
		//保存对角线两点坐标
		locationArr2.add(element.getLoc1().getWorld().getName());
		locationArr2.add(element.getLoc1().getX()+"");
		locationArr2.add(element.getLoc1().getY()+"");
		locationArr2.add(element.getLoc1().getZ()+"");
		locationArr3.add(element.getLoc2().getWorld().getName());
		locationArr3.add(element.getLoc2().getX()+"");
		locationArr3.add(element.getLoc2().getY()+"");
		locationArr3.add(element.getLoc2().getZ()+"");
		//开始对属性分别写入
		fc.set(id+".id", id);
		//挑战模式
		fc.set(id+".mode", element.getMode());
		//玩家挑战次数
		fc.set(id+".playerNumber", element.getNumber());
		//最大次数
		fc.set(id+".maxNumber", element.getMaxNumber());
		//最低人数
		fc.set(id+".minPerson",element.getMinPerson());
		//最大人数
		fc.set(id+".maxPerson",element.getMaxPerson());
		//写入奖励金币数量
		fc.set(id+".winmoney",element.getWinMoney());
		fc.set(id+".losemoney", element.getLoseMoney());
		//需要等待时间
		fc.set(id+".time", element.getTime());
		//游戏时长
		fc.set(id+".gametime", element.getGameTime());
		//限制的周数和小时
		fc.set(id+".week", element.getWeek());
		fc.set(id+".hour", element.getHour());
		//对角线
		fc.set(id+".location1",locationArr2);
		fc.set(id+".location2",locationArr3);
		//等待大厅坐标 将坐标容器放入
		fc.set(id+".waitlocation", locationArr);
		//判断模式
		if(element.getMode().equals("team")) {
			List<String> firstLocation = new ArrayList<String>();
			List<String> secondLocation = new ArrayList<String>();
			if(element.getFirstLocation()!=null) {
				firstLocation.add(element.getFirstLocation().getWorld().getName());
				firstLocation.add(element.getFirstLocation().getX()+"");
				firstLocation.add(element.getFirstLocation().getY()+"");
				firstLocation.add(element.getFirstLocation().getZ()+"");
			}
			if(element.getSecondLocation()!=null) {
				secondLocation.add(element.getSecondLocation().getWorld().getName());
				secondLocation.add(element.getSecondLocation().getX()+"");
				secondLocation.add(element.getSecondLocation().getY()+"");
				secondLocation.add(element.getSecondLocation().getZ()+"");
			}
			//写入队伍出生点
			fc.set(id+".firstLocation", firstLocation);
			fc.set(id+".secondLocation", secondLocation);
			//写入物品
			fc.set(id+".winitem", element.getWinItem());
			fc.set(id+".loseitem", element.getLoseItem());
		}else {
			//个人竞技 写入物品
			fc.set(id+".firstitem", element.getFirstItem());
			fc.set(id+".seconditem", element.getSecondItem());
			fc.set(id+".thirditem", element.getThirdItem());
		}
		//进入物品
		fc.set(id+".joinitem", element.getJoinItem());
		try {
			fc.save(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
