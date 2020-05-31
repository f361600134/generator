com.kepe.dragon.persistent.provider

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kepe.asyncdb.PersistentAddProvider;
import com.kepe.dragon.persistent.dao.ItemDAO;
import com.kepe.dragon.persistent.domain.Item;

/**
* @author Jeremy
*/
@Component
public class ItemPersistentProvider implements PersistentAddProvider<Item>{

	@Autowired
	private ItemDAO dao;
	
	@Override
	public void save(Item data) throws Exception {
		if(data!=null) {
			if (data.isDirty()) {
				data.setDirty(false);
				data.beforeSave();
				dao.insert(data);
			}
			else 
				dao.update(data);
		}
	}

	@Override
	public void add(Item data) throws Exception {
		this.save(data);
	}
	
}
