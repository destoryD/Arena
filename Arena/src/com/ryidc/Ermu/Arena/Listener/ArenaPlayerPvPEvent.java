package com.ryidc.Ermu.Arena.Listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.game.TeamStart;

/**
 * �������pvp�¼�
 * �ڴ���׼��ʱ �� Ϊͬ�������ʱ
 * ����������˺�
 * @author Er_mu
 *
 */
public class ArenaPlayerPvPEvent implements Listener{
	
	@EventHandler
	public void PlayerPvPEvent(EntityDamageByEntityEvent event) {
		//ȡ�˺����뱻�˺���
		Entity att = event.getDamager();
		if(att instanceof Player) {
			//����˺���
			Player p = (Player) att;
			//��ȡ���˺���
			Entity damage = event.getEntity();
			if(damage instanceof Player) {
				Player p2 = (Player) damage;
				//ȡ��������о�����״̬
				if(Arena.playerList.get(p.getName()).getArenaId()!=null) {
					//�ھ�������
					//�ж��Ƿ���׼���ڼ�
					if(!Arena.arenaList.get(Arena.playerList.get(p.getName()).getArenaId()).getStartBool()) {
						//δ��ʼ ׼���ڼ�
						p.sendMessage("��a׼���ڼ䲻������˺�!");
						event.setCancelled(true);
					}else {
						//�Ѿ���ʼ �ж�ģʽ�Ƿ�Ϊteam
						ArenaInfo ai = Arena.arenaList.get(Arena.playerList.get(p.getName()).getArenaId());
						if(ai.getMode().equals("team")) {
							//ȡ����ʱ����
							TeamStart ts = (TeamStart)Arena.gameStartData.get(ai.getId());
							//�жϹ��������ڶ���
							if(ts.getBlueTeam().containsKey(p.getName())) {
								if(ts.getBlueTeam().containsKey(p2.getName())) {
									//ͬʱ�˶������б�������
									p.sendMessage("��aͬ������Ҳ�������˺�");
									event.setCancelled(true);
								}
							}else if(ts.getRedTeam().containsKey(p.getName())) {
								if(ts.getRedTeam().containsKey(p2.getName())) {
									p.sendMessage("��aͬ������Ҳ�������˺�");
									event.setCancelled(true);
								}
							}
						}
					}
				}
			}
			
		}
	}
}
