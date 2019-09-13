package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.PlayerInfo;
import com.ryidc.Ermu.game.TeamStart;
/**
 * ���������¼� ȡ����ɱ��
 * ���Ϊ�Ŷ�ģʽ��������Ŷ�����
 * ���������������
 * @author 14713
 *
 */
public class ArenaPlayerKillEvent implements Listener{
	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event) {
		//��ȡ��ɱ��
		Player killer = event.getEntity().getKiller();
		Player death = event.getEntity();
		if(killer==null) {
			return;
		}
		//ȡ��������ڵľ�����id
		String id = Arena.playerList.get(killer.getName()).getArenaId();
		if(id!=null) {
			//�ж��Ƿ�ʼ
			if(Arena.arenaList.get(id).getStartBool()) {
				//�ж�ģʽ
				if(Arena.arenaList.get(id).getMode().equals("team")) {
					//get��Ӧ�ľ�������������
					TeamStart ts = (TeamStart)Arena.gameStartData.get(id);
					//�ж���������
					if(ts.getBlueTeam().containsKey(killer.getName())) {
						//����Ӧ����+1
						ts.setBlueKillNumber(ts.getBlueKillNumber()+1);
						killer.sendMessage("��b���ɱ��"+death.getName()+",��ǰ�����ɱ��:"+ts.getBlueKillNumber()+",�Է������ɱ��"+ts.getRedKillNumber());
						
					}else if(ts.getRedTeam().containsKey(killer.getName())){
						ts.setRedKillNumber(ts.getRedKillNumber()+1);
						killer.sendMessage("��c���ɱ��"+death.getName()+",��ǰ�����ɱ��:"+ts.getRedKillNumber()+",�Է������ɱ��"+ts.getBlueKillNumber());
					}
				}else if(Arena.arenaList.get(id).getMode().equals("single")) {
					PlayerInfo pi = Arena.playerList.get(killer.getName());
					//���˾��� ������һ�ɱ��+1
					pi.setKillNumber(pi.getKillNumber()+1);
					killer.sendMessage("��e���ɱ�ˡ�a"+death.getName()+"��e,��ǰ��ɱ��"+pi.getKillNumber());
				}
			}
			
			
		}
		
		
	}
}
