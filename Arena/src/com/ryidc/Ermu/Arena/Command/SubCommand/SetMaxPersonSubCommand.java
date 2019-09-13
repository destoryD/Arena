package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;
import com.ryidc.Ermu.save.ArSave;
/**
 * 设置最大人数
 * @author Er_mu
 *
 */
public class SetMaxPersonSubCommand implements ArSubCommand{
	@Override
	public void command(Player p, String[] args) {
		if(args.length<3) {
			p.sendMessage("§c指令格式错误 正确格式/arena setminplayer 竞技场 人数");
			return;
		}
		//判断竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该竞技场不存在");
			return;
		}
		ArenaInfo ai = Arena.arenaList.get(args[1]);
		int number = Integer.valueOf(args[2]).intValue();
		//判断人数合法性
		if(number<2) {
			p.sendMessage("§c人数格式错误");
		}else {
			//设置
			ai.setMaxPerson(number);
			p.sendMessage("§a最大人数成功设置为"+number+"人");
			ArSave.save(ai);
		}
	
	}
}
