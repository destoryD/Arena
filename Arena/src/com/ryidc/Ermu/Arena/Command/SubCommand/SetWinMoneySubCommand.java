package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetWinMoneySubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//�ж��Ա�������
		if(args.length<3) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ: /arena setwinmoney ������id �����");
			return;
		}
		//�鿴�������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�˾�����������");
			//������
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int number = Integer.valueOf(args[2]).intValue();
		//�жϽ�ҺϷ���
		if(number<=0) {
			p.sendMessage("��c��Ҹ�ʽ����");
			return;
		}
		ai.setWinMoney(number);
		p.sendMessage("��aʤ������ҳɹ�����Ϊ"+number);
		ArSave.save(ai);
		
	}
	
}
