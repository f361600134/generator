com.kepe.dragon.persistent.provider

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kepe.asyncdb.PersistentAddProvider;
import com.kepe.dragon.persistent.dao.${entityName}DAO;
import com.kepe.dragon.persistent.domain.${entityName};

/**
* @author Jeremy
*/
@Component
public class ${entityName}PersistentProvider implements PersistentAddProvider<${entityName}>{

	@Autowired
	private ${entityName}DAO dao;
	
	@Override
	public void save(${entityName} data) throws Exception {
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
	public void add(${entityName} data) throws Exception {
		this.save(data);
	}
	
}
