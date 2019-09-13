package com.ryidc.Ermu.game;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import org.bukkit.scheduler.BukkitRunnable;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

/**
 * ���Ӹ�����ʼ����Ϸ����Ϸ״̬
 * ������״̬��������
 * ÿ������Ϸ������������ ʱ�䵽��ʱ �رմ���Ϸ
 * @author Er_mu
 *
 */
public class GameListener extends BukkitRunnable{
	//������
	private int number = 0;
	
	@Override
	public void run() {
		this.resetNumber();
		if(this.number==30) {
			this.number = 0;
		}	
		//�������е��ѿ�ʼ��Ϸ����
		Set<String> keySet = Arena.gameStartData.keySet();
		for(String temp : keySet) {
			//���Ӽ�����
			this.number++;	
			//ȡģʽ
			String mode = Arena.arenaList.get(temp).getMode();
			//�ж�ģʽ
			if(mode.equals("team")) {		
				//�ŶӾ��� תΪ��Ӧ����
				TeamStart ts = (TeamStart) Arena.gameStartData.get(temp);
				//ÿ�μ���ʱ��1s
				ts.setGameTime(ts.getGameTime()-1);
				//�жϼ������Ƿ������һ����ѵ
				if(this.number==1) {
					//ȫ�巢����Ϣ
					GameStartListener.senderMessageToAllPlayer("��c������Ϸ��������"+ts.getGameTime()+"s", temp);
				}
				//�ж�ʱ��
				if(ts.getGameTime()==0) {
					//����
					ts.stopGame();
					//������ǰ
					continue;
				}
				//����һ����������0
				if(ts.getBlueTeam().size()==0||ts.getRedTeam().size()==0) {
					GameStartListener.senderMessageToAllPlayer("��a������������,��Ϸ�Ѿ�ǿ�ƽ���,�Ѿ����������͵���ʤһ��", temp);
					//����
					ts.stopGame();
					continue;
				}
			}else {
				//���˾���
				//ȡ��Ϸ������
				SingleStart ss = (SingleStart) Arena.gameStartData.get(temp);
				//ÿ�μ���1s
				ss.setGameTime(ss.getGameTime()-1);
				if(this.number==1) {
					//ȫ�巢����Ϣ
					GameStartListener.senderMessageToAllPlayer("��c������Ϸ��������"+ss.getGameTime()+"s", temp);
				}
				//�ж�ʱ��
				if(ss.getGameTime()==0) {
					//����
					ss.stopGame();
					continue;
				}
				//�ж�����
				if(Arena.arenaList.get(temp).getPlayerList().size()<=3) {
					//����
					ss.stopGame();
					continue;
				}
			}
		}
	}
	private void resetNumber() {
		//��ȡ��ǰʱ��
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		//ʱ��
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour==23) {
			Set<String> keySet = Arena.arenaList.keySet();
			//23������
			for(String temp : keySet) {
				Arena.arenaList.get(temp).getNumber().clear();
				//����
				ArSave.save(Arena.arenaList.get(temp));
			}
		}
		
	}
	
}
