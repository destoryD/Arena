package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetGameTimeSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//arena setgametime 竞技场id 时间(分钟)
		if(args.length<3) {
			p.sendMessage("§c子指令输入错误 正确格式/arena setgametime 竞技场id 时间[分钟]");
			return;
		}
		//判断竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该竞技场不存在");
			return;
		}		
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int time = Integer.valueOf(args[2]).intValue();
		if(time<=0) {
			p.sendMessage("§c等待时间格式错误");
		}else {
			//设置
			ai.setGameTime(time);
			p.sendMessage("§a游戏时长成功设置为"+time+"m");
			ArSave.save(ai);
		}
	}

}
