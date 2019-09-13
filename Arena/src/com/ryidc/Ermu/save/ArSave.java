package com.ryidc.Ermu.save;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ryidc.Ermu.Arena.Arena;
import com.ryidc.Ermu.Arena.ArenaInfo;

/**
 * ��ĳ�����������浽�����ļ���
 * @author Er_mu
 *
 */
public class ArSave{
	public static void save(ArenaInfo element) {
		//����·�� �ж��Ƿ����
		File saveDir = new File(Arena.arenaSavePath);
		if(!saveDir.exists()) {
			//�½�
			saveDir.mkdirs();
		}
		String id = element.getId();
		//�½�config�ļ�
		File config = new File(saveDir,id+".yml");
		//�ж��ļ��Ƿ����
		if(!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileConfiguration fc = YamlConfiguration.loadConfiguration(config);
		//�������� ���䴦��Ϊlist
		//�ȴ������������� world x y z
		List<String> locationArr = new ArrayList<>();
		//�Խ�������
		List<String> locationArr2 = new ArrayList<>();
		List<String> locationArr3 = new ArrayList<>();
		if(element.getWaitLocation()!=null) {
			locationArr.add(element.getWaitLocation().getWorld().getName());
			locationArr.add(element.getWaitLocation().getX()+"");
			locationArr.add(element.getWaitLocation().getY()+"");
			locationArr.add(element.getWaitLocation().getZ()+"");
		}
		//����Խ�����������
		locationArr2.add(element.getLoc1().getWorld().getName());
		locationArr2.add(element.getLoc1().getX()+"");
		locationArr2.add(element.getLoc1().getY()+"");
		locationArr2.add(element.getLoc1().getZ()+"");
		locationArr3.add(element.getLoc2().getWorld().getName());
		locationArr3.add(element.getLoc2().getX()+"");
		locationArr3.add(element.getLoc2().getY()+"");
		locationArr3.add(element.getLoc2().getZ()+"");
		//��ʼ�����Էֱ�д��
		fc.set(id+".id", id);
		//��սģʽ
		fc.set(id+".mode", element.getMode());
		//�����ս����
		fc.set(id+".playerNumber", element.getNumber());
		//������
		fc.set(id+".maxNumber", element.getMaxNumber());
		//�������
		fc.set(id+".minPerson",element.getMinPerson());
		//�������
		fc.set(id+".maxPerson",element.getMaxPerson());
		//д�뽱���������
		fc.set(id+".winmoney",element.getWinMoney());
		fc.set(id+".losemoney", element.getLoseMoney());
		//��Ҫ�ȴ�ʱ��
		fc.set(id+".time", element.getTime());
		//��Ϸʱ��
		fc.set(id+".gametime", element.getGameTime());
		//���Ƶ�������Сʱ
		fc.set(id+".week", element.getWeek());
		fc.set(id+".hour", element.getHour());
		//�Խ���
		fc.set(id+".location1",locationArr2);
		fc.set(id+".location2",locationArr3);
		//�ȴ��������� ��������������
		fc.set(id+".waitlocation", locationArr);
		//�ж�ģʽ
		if(element.getMode().equals("team")) {
			List<String> firstLocation = new ArrayList<String>();
			List<String> secondLocation = new ArrayList<String>();
			if(element.getFirstLocation()!=null) {
				firstLocation.add(element.getFirstLocation().getWorld().getName());
				firstLocation.add(element.getFirstLocation().getX()+"");
				firstLocation.add(element.getFirstLocation().getY()+"");
				firstLocation.add(element.getFirstLocation().getZ()+"");
			}
			if(element.getSecondLocation()!=null) {
				secondLocation.add(element.getSecondLocation().getWorld().getName());
				secondLocation.add(element.getSecondLocation().getX()+"");
				secondLocation.add(element.getSecondLocation().getY()+"");
				secondLocation.add(element.getSecondLocation().getZ()+"");
			}
			//д����������
			fc.set(id+".firstLocation", firstLocation);
			fc.set(id+".secondLocation", secondLocation);
			//д����Ʒ
			fc.set(id+".winitem", element.getWinItem());
			fc.set(id+".loseitem", element.getLoseItem());
		}else {
			//���˾��� д����Ʒ
			fc.set(id+".firstitem", element.getFirstItem());
			fc.set(id+".seconditem", element.getSecondItem());
			fc.set(id+".thirditem", element.getThirdItem());
		}
		//������Ʒ
		fc.set(id+".joinitem", element.getJoinItem());
		try {
			fc.save(config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
