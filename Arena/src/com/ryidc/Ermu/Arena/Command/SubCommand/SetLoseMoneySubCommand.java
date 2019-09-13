package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;

public class SetLoseMoneySubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		//判定自变量长度
		if(args.length<3) {
			p.sendMessage("§c指令格式错误 正确格式: /arena setwinmoney 竞技场id 金币数");
			return;
		}
		//查看竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c此竞技场不存在");
			//不存在
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int number = Integer.valueOf(args[2]).intValue();
		//判断人数合法性
		if(number<=0) {
			p.sendMessage("§c金币格式错误");
			return;
		}
		ai.setLoseMoney(number);
		p.sendMessage("§a失败方金币成功设置为"+number);
		ArSave.save(ai);
		
	}
	
}
