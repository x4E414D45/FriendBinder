package edu.cpp.Rafikie.recommender;
import edu.cpp.Rafikie.data.UserDetails;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RecommenderTest {

	public RecommenderTest() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testMostSimilarUsers() {
		Recommender r = new Recommender();
		UserDetails a = new UserDetails();
		UserDetails b = new UserDetails();
		ArrayList<UserDetails> allUsers = new ArrayList<>();

		Double d_v[] = {1.0, 2.0, 3.0};

		a.setVectorRepr(d_v);
		b.setVectorRepr(d_v);

		allUsers.add(b);
		ArrayList<UserDetails> retUsers = r.mostSimilarUsers(a, allUsers);
		assertEquals(retUsers.get(0), allUsers.get(0));
	}

	@Test
	public void testMostSimilarUsers1() {
		Recommender r = new Recommender();
		UserDetails a = new UserDetails();
		UserDetails b = new UserDetails();
		ArrayList<UserDetails> allUsers = new ArrayList<>();

		Double d_v[] = {1.0, 2.0, 3.0};
		Double d_w[] = {1.0, 1.0, 3.0};

		a.setVectorRepr(d_v);
		b.setVectorRepr(d_w);

		allUsers.add(b);
		allUsers.add(a);
		ArrayList<UserDetails> retUsers = r.mostSimilarUsers(a, allUsers);
		assertEquals(retUsers.get(0), a);
	}

}

