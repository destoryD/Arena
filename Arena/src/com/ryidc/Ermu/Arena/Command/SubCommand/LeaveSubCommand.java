package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.game.GameStartListener;
import com.ryidc.Ermu.game.TeamStart;

/**
 * �뿪������
 * @author Er_mu
 *
 */
public class LeaveSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		String id = Arena.playerList.get(p.getName()).getArenaId();
		//�жϴ�����Ƿ��ھ�������
		if(id==null) {
			p.sendMessage("��a�㲻�ھ�������,�����˳�!");
			return;
		}else {
			//�Ϳմ���ҵľ�����״̬
			Arena.playerList.get(p.getName()).setArenaId(null);
			//��������ɾ�������
			Arena.arenaList.get(id).getPlayerList().remove(p.getName());
			//���ô���ҵĻ�ɱ��
			Arena.playerList.get(p.getName()).setKillNumber(0);
			//��Ϊess spawnû��Ȩ�޾���һ���ӳ� ������ʱ���*Ȩ��
			p.setOp(true);
			//���䴫�͵�����
			Bukkit.dispatchCommand(p, "spawn");
			p.setOp(false);
			//�жϾ�������ʼ״̬
			if(Arena.arenaList.get(id).getStartBool()) {
				//�Ѿ���ʼ
				//�򾺼���ȫ����ҷ���һ����Ϣ
				GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a�뿪����Ϸ!", id);
				//�ж���Ϸģʽ
				if(Arena.arenaList.get(id).getMode().equals("team")) {
					//ȡ��Ϸ������
					TeamStart ts = (TeamStart)Arena.gameStartData.get(id);
					if(ts.getBlueTeam().containsKey(p.getName())) {
						//ɾ��
						ts.getBlueTeam().remove(p.getName());
					}else if(ts.getRedTeam().containsKey(p.getName())) {
						ts.getRedTeam().remove(p.getName());
					}
				}
			}
			else {
				//�򾺼���ȫ����ҷ���һ����Ϣ
				GameStartListener.senderMessageToAllPlayer("��a��ҡ�e"+p.getName()+"��a�뿪����Ϸ,����"+(Arena.arenaList.get(id).getMinPerson()-Arena.arenaList.get(id).getPlayerList().size())+"��", id);
				//�ж��Ƿ��н�����Ʒ
				if(Arena.arenaList.get(id).getJoinItem()!=null) {
					//������ҷ�����Ʒ
					p.getInventory().addItem(Arena.arenaList.get(id).getJoinItem());
					p.sendMessage("��a������Ʒ�Ѿ�����");
				}
				
			}	
			//���˳�����ҷ���һ����Ϣ
			p.sendMessage("��a���˳��˾�����");
		}
		
	}

}
