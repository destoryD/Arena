package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

public class SetHourSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//��ȷ��ʽ /arena setweek ������id ʱ��
		p.sendMessage("��a-------------------");
		p.sendMessage("��aʹ��24Сʱ��ʱ��");
		p.sendMessage("��a�����������ʱ��,��ֵ��Ϊ0");
		p.sendMessage("��a-------------------");
		//�ж��Ա���
		if(args.length<3) {
			p.sendMessage("��c��ʽ���� ��ȷ��ʽ /arena setweek ������id ʱ��");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c������������");
			return;
		}
		//�ж�ʱ���Ƿ�������ȷ
		//���ַ���תΪ����
		int hour = Integer.valueOf(args[2]);
		//�ж�����
		if(hour<0||hour>24) {
			p.sendMessage("��c������һ����ȷ��ֵ");
			return;
		}
		//����
		Arena.arenaList.get(args[1]).setHour(hour);
		p.sendMessage("��a�ɹ���Сʱ���Ƶ�"+hour+"ʱ");
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
		
		
	}

}
