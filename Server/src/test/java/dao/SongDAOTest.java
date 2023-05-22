package dao;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import data.Song;

public class SongDAOTest {
    private SongDAO songDAO;
    private PersistenceManager pm;
    private Transaction tx;
    private PersistenceManagerFactory pmf;

    @Before
    public void setup() {
        songDAO = SongDAO.getInstance();
        pm = mock(PersistenceManager.class);
        pmf = mock(PersistenceManagerFactory.class);
        tx = mock(Transaction.class);
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.currentTransaction()).thenReturn(tx);
        songDAO.setPmf(pmf);
    }

    @Test
    public void testSave() {
        Song song = new Song();
        songDAO.save(song);
        verify(pm).makePersistent(song);
    }

    @Test
    public void testDelete() {
        Song song = new Song();
        songDAO.delete(song);
        verify(pm).deletePersistent(song);
    }

    @Test
    public void testGetAll() {
        songDAO.getAll();
        verify(pm).getExtent(Song.class, true);
    }

    @Test
    public void testFind() {
        String name = "test";
        songDAO.find(name);
        verify(pm).newQuery("SELECT FROM " + Song.class.getName() + " WHERE name == '"+name+"'");
    }
}
