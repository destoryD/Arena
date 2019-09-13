package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

/**
 * 设置竞技场等待时间
 * @author Er_mu
 *
 */
public class SetWaitTimeSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		p.sendMessage("§a注意:等待时间请使用秒数");
		// TODO Auto-generated method stub
		if(args.length<3) {
			p.sendMessage("§c指令格式错误 正确格式/arena setwaittime 竞技场 时间");
			return;
		}
		//判断竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该竞技场不存在");
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int time = Integer.valueOf(args[2]).intValue();
		//判断时间合法性
		if(time<=0) {
			p.sendMessage("§c等待时间格式错误");
		}else {
			//设置
			ai.setTime(time);
			p.sendMessage("§a等待时间成功设置为"+time+"s");
			ArSave.save(ai);
		}
		
		
	}
	
}
