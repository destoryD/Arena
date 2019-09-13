package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

public class SetSecondLocationSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//arena setFirstLocation 竞技场
		if(args.length<2) {
			p.sendMessage("§c格式错误 正确格式 /arena setSecondLocation 竞技场");
			return;
		}
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c该竞技场不存在");
			return;
		}
		//判断竞技场类型
		if(!Arena.arenaList.get(args[1]).getMode().equals("team")) {
			p.sendMessage("§c竞技场类型必须为团队竞技才可设置坐标");
			return;
		}
		p.sendMessage("§a设置成功");
		//设置第二个坐标
		Arena.arenaList.get(args[1]).setSecondLocation(p.getLocation());
		//保存
		ArSave.save(Arena.arenaList.get(args[1]));
	}

}
