package dao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Extent;
import javax.jdo.Query;

import data.User;

import java.util.Arrays;

public class UserDAOTest {
 /*   private UserDAO userDAO;
    private PersistenceManager pm;
    private Transaction tx;
    private PersistenceManagerFactory pmf;

    @Before
    public void setup() {
        userDAO = UserDAO.getInstance();
        pm = mock(PersistenceManager.class);
        pmf = mock(PersistenceManagerFactory.class);
        tx = mock(Transaction.class);
        when(pmf.getPersistenceManager()).thenReturn(pm);
        when(pm.currentTransaction()).thenReturn(tx);
        userDAO.setPmf(pmf);
    }

    @Test
    public void testSave() {
        User user = new User();
        userDAO.save(user);

        verify(pm, times(1)).makePersistent(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        userDAO.updateUser(user);

        verify(pm, times(1)).makePersistent(user);
    }

    @Test
    public void testDelete() {
        User user = new User();
        userDAO.delete(user);

        verify(pm, times(1)).deletePersistent(user);
    }

    @Test
    public void testGetAll() {
        @SuppressWarnings("unchecked")
		Extent<User> mockExtent = mock(Extent.class);
        when(pm.getExtent(User.class, true)).thenReturn(mockExtent);
        when(mockExtent.iterator()).thenReturn(Arrays.asList(new User()).iterator());

        assertNotNull(userDAO.getAll());
        assertTrue(userDAO.getAll().size() > 0);
    }

    @Test
    public void testFind() {
        String username = "test";
        User user = new User();
        user.setUsername(username);

        @SuppressWarnings("unchecked")
		Query<User> mockQuery = mock(Query.class);
        when(pm.newQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.execute()).thenReturn(user);

        User result = userDAO.find(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }*/
}
