package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;
/**
 * �����������
 * @author Er_mu
 *
 */
public class SetMaxPersonSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		if(args.length<3) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ/arena setminplayer ������ ����");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�þ�����������");
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int number = Integer.valueOf(args[2]).intValue();
		//�ж������Ϸ���
		if(number<2) {
			p.sendMessage("��c������ʽ����");
		}else {
			//����
			ai.setMaxPerson(number);
			p.sendMessage("��a��������ɹ�����Ϊ"+number+"��");
			ArSave.save(ai);
		}
	
	}
}
