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
	 * ����id get����Ӧ�������������Ϣ
	 * @param id
	 */
	public Map<String,Player> getPlayerList(String id) {
		return Arena.arenaList.get(id).getPlayerList();
	}
	/**
	 * ����һ���������
	 * @param loc1����������1
	 * @param loc2����������2
	 * @return Location ���ɵ�������
	 * @throws InterruptedException 
	 */
	public Location randomLocation(Location loc1,Location loc2){
		//��ȡ�Խ��߸�������λ�� ������Ӵ�С��������
		double minX = loc1.getX()>loc2.getX()?loc2.getX():loc1.getX();
		double maxX = loc1.getX()>loc2.getX()?loc1.getX():loc2.getX();
		double minZ = loc1.getZ()>loc2.getZ()?loc2.getZ():loc1.getZ();
		double maxZ = loc1.getZ()>loc2.getZ()?loc1.getZ():loc2.getZ();
		double Y = loc1.getY()>loc2.getY()?loc1.getY():loc2.getY();
		double X = this.randomNumber(minX, maxX);
		double Z = this.randomNumber(minZ, maxZ);
		while(true) {
			
			//�жϽ��·���
			Location temp = new Location(loc1.getWorld(),X,Y-1,Z);
			if(temp.getBlock()!=null&&!temp.getBlock().getType().equals(Material.AIR)) {
				break;
			}
			//��������
			//��y������-1
			Y--;
		}
		//��������
		return new Location(loc1.getWorld(),X,Y,Z);
		
	}
	/**
	 * �����������~����֮�����
	 */
	private double randomNumber(double min,double max) {
		Random r = new Random();
		//����X
		double X;
		//ȫ��Ϊ����
		if(min<=0&&max<=0) {
			//ֱ��ȡ������Χ Ȼ��ȡ��
			X = r.nextInt((int)(Math.abs(min)-Math.abs(max)))+Math.abs(max);
			X = -1*X;
		}
		//��СֵΪ����
		else if(min<0) {
			X = r.nextInt(Math.abs((int)min)+Math.abs((int)max))-Math.abs(min);
					
		}
		else {
			//���ɷ�Χ����
			X = r.nextInt((int)(max-min))+min;
		}	
		return X;
	}
}
