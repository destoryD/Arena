package com.ryidc.Ermu.save;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * ��ȡ����Ŀ¼��ȫ���������ļ� ��������뵽��������
 * @author Er_mu
 *
 */
public class ArReader{
	public static void read(String path,boolean bool) {
		//Ϊ��֤��ȡ ÿ����վ��������� �ж��Ƿ���Ҫ���
		if(bool) {
			Arena.arenaList.clear();
		}
		//ѡ���ļ�
		File f = new File(path);
		if(!f.exists()) {
			return;
		}
		//�ж�
		if(f.isDirectory()) {
			//Ŀ¼ ����Ŀ¼��
			for(File temp : f.listFiles()) {
				//�ݹ�
				read(temp.getAbsolutePath(),false);
			}
		}else {

			//id
			String id = f.getName().split("\\.")[0];
			//�½�Yaml�����ļ�
			FileConfiguration config = YamlConfiguration.loadConfiguration(f);
			//���л� ����������
			ArenaInfo temp = new ArenaInfo();
			//��������
			List<String> locationArr = config.getStringList(id+".waitlocation");
			List<String> locationArr2 = config.getStringList(id+".location1");
			List<String> locationArr3 = config.getStringList(id+".location2");
			Location waitLocation;
			Location loc1 = new Location(Bukkit.getWorld(locationArr2.get(0)),Double.valueOf(locationArr2.get(1)),Double.valueOf(locationArr2.get(2)),Double.valueOf(locationArr2.get(3)));
			Location loc2 = new Location(Bukkit.getWorld(locationArr3.get(0)),Double.valueOf(locationArr3.get(1)),Double.valueOf(locationArr3.get(2)),Double.valueOf(locationArr3.get(3)));
			if(locationArr.size()==0) {
				waitLocation = null;
			}else {
				//�½�location����
				waitLocation = new Location(Bukkit.getWorld(locationArr.get(0)),Double.valueOf(locationArr.get(1)),Double.valueOf(locationArr.get(2)),Double.valueOf(locationArr.get(3)));
			}
			//������ս����map
			Map<String,Integer> number = new HashMap<>();
			//��ȡ��Ӧ����ѡ��
			ConfigurationSection cs = config.getConfigurationSection(id+".playerNumber");
			//����
			for(String a : cs.getKeys(false)) {
				number.put(a, config.getInt(id+".playerNumber."+a));
			}
			temp.setId(config.getString(id+".id"));
			temp.setMode(config.getString(id+".mode"));
			//�����ս����
			temp.setMaxNumber(config.getInt(id+".maxNumber"));
			temp.setMinPerson(config.getInt(id+".minPerson"));
			temp.setMaxPerson(config.getInt(id+".maxPerson"));
			//���ý��
			temp.setWinMoney(config.getInt(id+".winmoney"));
			temp.setLoseMoney(config.getInt(id+".losemoney"));
			temp.setTime(config.getInt(id+".time"));
			//������Ϸʱ��
			temp.setGameTime(config.getInt(id+".gametime"));
			temp.setWeek(config.getInt(id+".week"));
			temp.setHour(config.getInt(id+".hour"));
			//�ж�ģʽ
			if(temp.getMode().equals("team")) {
				List<String> LocationArr4 = config.getStringList(id+".firstLocation");
				List<String> LocationArr5 = config.getStringList(id+".secondLocation");
				Location firstLocation = null;
				Location secondLocation = null;
				if(LocationArr4.size()!=0) {
					firstLocation = new Location(Bukkit.getWorld(LocationArr4.get(0)),Double.valueOf(LocationArr4.get(1)),Double.valueOf(LocationArr4.get(2)),Double.valueOf(LocationArr4.get(3)));
				}
				if(LocationArr5.size()!=0) {
					secondLocation = new Location(Bukkit.getWorld(LocationArr5.get(0)),Double.valueOf(LocationArr5.get(1)),Double.valueOf(LocationArr5.get(2)),Double.valueOf(LocationArr5.get(3)));
				}
				temp.setFirstLocation(firstLocation);
				temp.setSecondLocation(secondLocation);
				//������Ʒ �ж���·���Ƿ����
				if(config.isSet(id+".winitem")) {
					//д��
					temp.setWinItem(config.getItemStack(id+".winitem"));
				}
				if(config.isSet(id+".loseitem")) {
					temp.setLoseItem(config.getItemStack(id+".loseitem"));
				}
				
			}else {
				//���˾���д�� һ����������
				if(config.isSet(id+".firstitem")){
					temp.setFirstItem(config.getItemStack(id+".firstitem"));
				}
				if(config.isSet(id+".seconditem")) {
					temp.setSecondItem(config.getItemStack(id+".seconditem"));
				}
				if(config.isSet(id+".thirditem")) {
					temp.setThirdItem(config.getItemStack(id+".thirditem"));
				}
			}
			//���������ս����
			temp.setNumber(number);
			//���õȴ��������� ����������
			temp.setWaitLocation(waitLocation);
			//��������
			temp.setLoc1(loc1);
			temp.setLoc2(loc2);
			//���ý�����Ʒ
			if(config.isSet(id+".joinitem")) {
				temp.setJoinItem(config.getItemStack(id+".joinitem"));
			}
			
			//������� ��idΪkey��������
			Arena.arenaList.put(id, temp);
		}
	}
}
