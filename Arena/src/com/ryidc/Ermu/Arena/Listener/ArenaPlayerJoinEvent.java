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
	//����һ����Ҿͽ���ŵ�������
	public void PlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		//���������û�� �ͽ������
		if(!(Arena.playerList.containsKey(p.getName()))) {
			//��������
			Arena.playerList.put(p.getName(), new PlayerInfo());
		}
		//Ϊ��ֹ������ھ������� �ж����λ���Ƿ��ھ�������
		//�������о���������
		Set<String> keyset = Arena.arenaList.keySet();
		for(String temp : keyset) {
			//ȡ����
			Location loc1 = Arena.arenaList.get(temp).getLoc1();
			
			//�����ж��Ƿ���һ��������
			if(p.getWorld().getName().equals(loc1.getWorld().getName())) {
				//�½���һ���������
				Location loc2 = Arena.arenaList.get(temp).getLoc2();
				//�������
				Location loc = p.getLocation();
				//���������� �Ӵ�С���� �ж�X��Z ���ж�Y
				Double minX = loc1.getX()<loc2.getY()?loc1.getX():loc2.getX();
				Double maxX = loc1.getX()>loc2.getY()?loc1.getX():loc2.getX();
				Double minZ = loc1.getZ()<loc2.getZ()?loc1.getZ():loc2.getZ();
				Double maxZ = loc1.getZ()>loc2.getZ()?loc1.getZ():loc2.getZ();
				//�ж�����
				if(loc.getX()>minX&&loc.getX()<maxX) {
					if(loc.getZ()>minZ&&loc.getZ()<maxZ) {
						//��Ϊess spawnû��Ȩ�޾���һ���ӳ� ������ʱ���*Ȩ��
						p.setOp(true);
						//���䴫�͵�����
						Bukkit.dispatchCommand(p, "spawn");
						p.setOp(false);
						//������Ϣ
						p.sendMessage("��a�������ϴ��˳�ʱ�ھ�����,�Ѿ���������������!");
						break;
					}
				}
				
			}
		}
	}
	
}
