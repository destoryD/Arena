package com.ryidc.Ermu.Arena.Command.SubCommand;

import org.bukkit.entity.Player;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.save.ArSave;

public class SetHourSubCommand implements ArSubCommand{

	@Override
	public void command(Player p, String[] args) {
		// TODO Auto-generated method stub
		//正确格式 /arena setweek 竞技场id 时间
		p.sendMessage("§a-------------------");
		p.sendMessage("§a使用24小时计时法");
		p.sendMessage("§a如果不想限制时间,将值设为0");
		p.sendMessage("§a-------------------");
		//判断自变量
		if(args.length<3) {
			p.sendMessage("§c格式错误 正确格式 /arena setweek 竞技场id 时间");
			return;
		}
		//判断竞技场是否存在
		if(!Arena.arenaList.containsKey(args[1])) {
			p.sendMessage("§c竞技场不存在");
			return;
		}
		//判断时间是否输入正确
		//将字符串转为数字
		int hour = Integer.valueOf(args[2]);
		//判断数字
		if(hour<0||hour>24) {
			p.sendMessage("§c请输入一个正确的值");
			return;
		}
		//设置
		Arena.arenaList.get(args[1]).setHour(hour);
		p.sendMessage("§a成功将小时限制到"+hour+"时");
		//保存
		ArSave.save(Arena.arenaList.get(args[1]));
		
		
	}

}
