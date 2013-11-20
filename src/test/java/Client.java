import org.junit.Test;

import com.pousheng.generator.constant.TemplateType;
import com.pousheng.generator.core.GeneratorOneTable;
import com.pousheng.generator.core.GeneratorOneToMany;
import com.pousheng.generator.core.model.SubTableVO;
import com.pousheng.generator.core.model.TableVO;

public class Client {

	@Test
	public void generatorOneTable() {
		try {
			TableVO tableVO = new TableVO();
			tableVO.setClassName("Role");
			tableVO.setPackageName("role");
			tableVO.setTableName("t_role");
			tableVO.setTemplateType(TemplateType.MODEL);
			GeneratorOneTable oneTable = new GeneratorOneTable();
			oneTable.generatorOneTable(tableVO);
			System.out.println("生成成功");
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

			sub.setRelationKeys("TEAM_ID", "TEAM_ID");

			tableVO.addSubTable(sub);

			SubTableVO sub1 = new SubTableVO();
			sub1.setClassName("TeamCoach");
			sub1.setPackageName("team");
			sub1.setTableName("t_team_coach");
			sub1.setTemplateType(TemplateType.MODEL);

			sub1.setRelationKeys("TEAM_ID", "TEAM_ID");

			tableVO.addSubTable(sub1);

			GeneratorOneToMany table = new GeneratorOneToMany();
			table.generatorOneToMany(tableVO);
			System.out.println(table);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
