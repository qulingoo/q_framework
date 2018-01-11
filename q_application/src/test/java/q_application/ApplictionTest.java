package q_application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shuyun.Application;
import com.shuyun.core.dao.TableModelDao;
import com.shuyun.core.entity.TableModel;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplictionTest {
	@Autowired
	TableModelDao tableModelDao;

	@Test
	public void testShow() {
//		TableModel tableModel = new TableModel();
//		tableModel.setComment("abc");
//		tableModel.setId("123");
//		tableModel.setIs_exist(1);
//		tableModel.setTable_name("2222");
//		tableModel.setCreated_date("2017-11-2 17:34:02");
//		tableModelDao.delete("123");
//		tableModelDao.insert(tableModel);
		
		
	}
}
