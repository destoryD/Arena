package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * ���þ������ȴ�ʱ��
 * @author Er_mu
 *
 */
public class SetWaitTimeSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("��aע��:�ȴ�ʱ����ʹ������");
		// TODO Auto-generated method stub
		if(args.length<3) {
			p.sendMessage("��cָ���ʽ���� ��ȷ��ʽ/arena setwaittime ������ ʱ��");
			return;
		}
		//�жϾ������Ƿ����
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("��c�þ�����������");
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int time = Integer.valueOf(args[2]).intValue();
		//�ж�ʱ��Ϸ���
		if(time<=0) {
			p.sendMessage("��c�ȴ�ʱ���ʽ����");
		}else {
			//����
			ai.setTime(time);
			p.sendMessage("��a�ȴ�ʱ��ɹ�����Ϊ"+time+"s");
			ArSave.save(ai);
		}
		
		
	}
	
}
