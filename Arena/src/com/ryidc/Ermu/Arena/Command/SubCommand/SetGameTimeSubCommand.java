package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetGameTimeSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//arena setgametime ������id ʱ��(����)
		if(args.length<3) {
			p.sendMessage("��c��ָ��������� ��ȷ��ʽ/arena setgametime ������id ʱ��[����]");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�þ�����������");
			return;
		}		
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int time = Integer.valueOf(args[2]).intValue();
		if(time<=0) {
			p.sendMessage("��c�ȴ�ʱ���ʽ����");
		}else {
			//����
			ai.setGameTime(time);
			p.sendMessage("��a��Ϸʱ���ɹ�����Ϊ"+time+"m");
			ArSave.save(ai);
		}
	}

}
