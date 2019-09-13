package com.ryidc.Ermu.game;

import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * ����Ϸ��״̬���м���
 * ��������ز���
 * @author Er_mu
 *
 */
public class GameStartListener extends BukkitRunnable{
	GameHandle ts;
	public GameStartListener() {
	}
	@Override
	public void run() {
		//����ȫ��������
		Set<String> keySet = Arena.arenaList.keySet();
		//����
		for(String tempId : keySet) {
			ArenaInfo temp = Arena.arenaList.get(tempId);
			//���ж��������
			if(temp.getPlayerList().size()>=temp.getMinPerson()) {
				//�жϿ�ʼ״̬
				if(!temp.getStartBool()) {
					if(temp.getWaitSeconds()==temp.getTime()||temp.getWaitSeconds()==temp.getTime()/2||temp.getWaitSeconds()==15||temp.getWaitSeconds()<=5) {
						//��˾�������ȫ����ҷ�����Ϣ
						senderMessageToAllPlayer("��a������Ϸ��ʼ���С�e"+temp.getWaitSeconds()+"s!",tempId);
					}	
					if(temp.getWaitSeconds()==0) {
						senderMessageToAllPlayer("��a��Ϸ��ʼ!", tempId);
						//��ʼ��Ϸ 
						temp.setStartBool(true);
						//���ж�Ӧģʽ��start
						if(temp.getMode().equals("team")) {
							GameHandle ts = new TeamStart(tempId);
							//�½���Ӧ���� ����������
							Arena.gameStartData.put(tempId, ts);
							//����run����
							ts.runGame();
						}else if(temp.getMode().equals("single")) {
							GameHandle ts = new SingleStart(tempId);
							//�½���Ӧ���� ����������
							Arena.gameStartData.put(tempId, ts);
							//����run����
							ts.runGame();
						}else if(temp.getMode().equals("guard")) {
							GameHandle ts = new SingleStart(tempId);
							//�½���Ӧ���� ����������
							Arena.gameStartData.put(tempId, ts);
							//����run����
							ts.runGame();
						}
						else if(temp.getMode().equals("boss")) {
							GameHandle ts = new SingleStart(tempId);
							//�½���Ӧ���� ����������
							Arena.gameStartData.put(tempId, ts);
							//����run����
							ts.runGame();
						}
					}
					//δ��ʼ�����ʱ�� ÿ�μ���1s ��ʱ��ʹ��1s
					temp.setWaitSeconds(temp.getWaitSeconds()-1);
				}
			}else if(temp.getPlayerList().size()<temp.getMinPerson()&&temp.getWaitSeconds()!=temp.getTime()&&!temp.getStartBool()) {
				//����������㲢��ʱ�䲻�������ȴ�ʱ�� ������Ϸδ��ʼ ����ʱ��
				senderMessageToAllPlayer("��a������Ҳ���,���¿�ʼ�ȴ�!",tempId);
				temp.setWaitSeconds(temp.getTime());
			}
			//��ȡ�Ѿ���ʼ�ı���
			if(temp.getStartBool()) {
				//�������ս������bossս
				if(temp.getMode().equals("guard")||temp.getMode().equals("boss")) {
					for(Entry<String, Player> entry : temp.getPlayerList().entrySet()) {
						Player p=entry.getValue();
						Inventory inv=p.getInventory();
						for(ItemStack is:inv.getContents()) {
							if(is.equals(temp.getWinnerItem())) {
								ts.stopGame();
							}
						}
					}
				}
			}
		}
		
	}
	/**
	 * ��ָ��������ȫ����ҷ�����Ϣ
	 * @param ��Ϣ
	 * @param ������id
	 */
	public static void senderMessageToAllPlayer(String mess,String id) {
		Set<String> keySet = Arena.arenaList.get(id).getPlayerList().keySet();
		for(String temp : keySet) {
			Player p = Arena.arenaList.get(id).getPlayerList().get(temp);
			p.sendMessage(mess);
		}
		
	}
	
}
