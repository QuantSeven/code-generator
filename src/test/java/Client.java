import org.junit.Test;

import com.pousheng.generator.constant.TemplateType;
import com.pousheng.generator.core.GeneratorOneTable;
import com.pousheng.generator.core.GeneratorOneToMany;
import com.pousheng.generator.core.model.SubTableVO;
import com.pousheng.generator.core.model.TableVO;

public class Client {

	//@Test
	public void generator() {
		try {
			TableVO tableVO = new TableVO();
			tableVO.setClassName("Team");
			tableVO.setPackageName("team");
			tableVO.setTableName("t_team");
			tableVO.setTemplateType(TemplateType.MODEL);
			GeneratorOneTable table = new GeneratorOneTable();
			table.generatorOneTable(tableVO);
			System.out.println(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void generator2() {
		try {
			TableVO tableVO = new TableVO();
			tableVO.setClassName("Team");
			tableVO.setPackageName("team");
			tableVO.setTableName("t_team");
			tableVO.setTemplateType(TemplateType.MODEL);
			
			SubTableVO sub = new SubTableVO();
			sub.setClassName("TeamPlayer");
			sub.setPackageName("team");
			sub.setTableName("t_team_player");
			sub.setTemplateType(TemplateType.MODEL);
			
			tableVO.addSubTable(sub);
			
			GeneratorOneToMany table = new GeneratorOneToMany();
			table.generatorOneToMany(tableVO);
			System.out.println(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
