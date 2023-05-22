package dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import data.Song;

public class SongDAO extends DataAccessObjectBase implements IDataAccessObject<Song>{
	private static SongDAO instance;
	private SongDAO() {}
	public static SongDAO getInstance() {
		if(instance == null) {
			instance = new SongDAO();
		}
		return instance;
	}
	@Override
	public void save(Song object) {
		super.saveObject(object);
		
	}
	@Override
	public void delete(Song object) {
		super.deleteObject(object);
		
	}
	@Override
	public List<Song> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Song> users = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Song> extent = pm.getExtent(Song.class, true);

			for (Song category : extent) {
				users.add(category);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return users;
	}
	@Override
	public Song find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Song result = null; 

		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Song.class.getName() + " WHERE name == '"+param+"'");
			query.setUnique(true);
			result = (Song) query.execute();
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
	public void setPmf(PersistenceManagerFactory pmf) {
        SongDAO.pmf = pmf;
    }
}
