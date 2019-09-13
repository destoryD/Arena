package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;
/**
 * ������ս����
 * @author Er_mu
 *
 */
public class SetNumberSubCommand implements ArSubCommand{
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
		if(number<1) {
			p.sendMessage("��c������ʽ����");
		}else {
			//����
			ai.setMaxNumber(number);
			p.sendMessage("��a�����ս�����ɹ�����Ϊ"+number+"��");
			ArSave.save(ai);
		}
	}
}
