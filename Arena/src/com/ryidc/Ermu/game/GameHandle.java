package com.ryidc.Ermu.game;

import java.util.Map;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;

public abstract class GameHandle {
	public abstract void runGame();
	public abstract void stopGame();
	/**
	 * 根据id get到对应竞技场的玩家信息
	 * @param id
	 */
	public Map<String,Player> getPlayerList(String id) {
		return Arena.arenaList.get(id).getPlayerList();
	}
	/**
	 * 生成一个随机坐标
	 * @param loc1竞技场坐标1
	 * @param loc2竞技场坐标2
	 * @return Location 生成的坐标结果
	 * @throws InterruptedException 
	 */
	public Location randomLocation(Location loc1,Location loc2){
		//获取对角线各个坐标位置 并将其从大到小进行排序
		double minX = loc1.getX()>loc2.getX()?loc2.getX():loc1.getX();
		double maxX = loc1.getX()>loc2.getX()?loc1.getX():loc2.getX();
		double minZ = loc1.getZ()>loc2.getZ()?loc2.getZ():loc1.getZ();
		double maxZ = loc1.getZ()>loc2.getZ()?loc1.getZ():loc2.getZ();
		double Y = loc1.getY()>loc2.getY()?loc1.getY():loc2.getY();
		double X = this.randomNumber(minX, maxX);
		double Z = this.randomNumber(minZ, maxZ);
		while(true) {
			
			//判断脚下方块
			Location temp = new Location(loc1.getWorld(),X,Y-1,Z);
			if(temp.getBlock()!=null&&!temp.getBlock().getType().equals(Material.AIR)) {
				break;
			}
			//继续生成
			//将y轴坐标-1
			Y--;
		}
		//返回坐标
		return new Location(loc1.getWorld(),X,Y,Z);
		
	}
	/**
	 * 随机生成正数~负数之间的数
	 */
	private double randomNumber(double min,double max) {
		Random r = new Random();
		//生成X
		double X;
		//全都为负数
		if(min<=0&&max<=0) {
			//直接取正数范围 然后取反
			X = r.nextInt((int)(Math.abs(min)-Math.abs(max)))+Math.abs(max);
			X = -1*X;
		}
		//最小值为负数
		else if(min<0) {
			X = r.nextInt(Math.abs((int)min)+Math.abs((int)max))-Math.abs(min);
					
		}
		else {
			//生成范围即可
			X = r.nextInt((int)(max-min))+min;
		}	
		return X;
	}
}
