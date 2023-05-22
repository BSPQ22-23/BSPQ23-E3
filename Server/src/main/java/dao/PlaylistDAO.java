package dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import data.Playlist;

public class PlaylistDAO extends DataAccessObjectBase implements IDataAccessObject<Playlist>{
	

	private static PlaylistDAO instance;
	private PlaylistDAO() {}
	public static PlaylistDAO getInstance() {
		if(instance == null) {
			instance = new PlaylistDAO();
		}
		return instance;
	}
	
	@Override
	public void save(Playlist object) {
		super.saveObject(object);
		
	}

	@Override
	public void delete(Playlist object) {
		super.deleteObject(object);
		
	}

	@Override
	public List<Playlist> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Playlist> users = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Playlist> extent = pm.getExtent(Playlist.class, true);

			for (Playlist category : extent) {
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
	public Playlist find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Playlist result = null; 

		try {
			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Playlist.class.getName() + " WHERE name == '"+param+"'");
			query.setUnique(true);
			result = (Playlist) query.execute();
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
	
	

}
