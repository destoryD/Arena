package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

public class SetWeekSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//��ȷ��ʽ /arena setweek ������id ʱ��
		p.sendMessage("��a-------------------");
		p.sendMessage("��aע�� ������Ϊ1 �Դ�����");
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
		int week = Integer.valueOf(args[2]);
		//�ж�����
		if(week<0||week>7) {
			p.sendMessage("��c������һ����ȷ��ֵ");
			return;
		}
		//����
		Arena.arenaList.get(args[1]).setWeek(week);
		p.sendMessage("��a�ɹ��������Ƶ�"+week);
		//����
		ArSave.save(Arena.arenaList.get(args[1]));
		
		
	}

}
