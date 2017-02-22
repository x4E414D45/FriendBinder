package recommender;

import edu.cpp.Rafikie.data.UserDetails;
import java.util.ArrayList;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
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
		RealVector v = new ArrayRealVector(d_v);

		a.setVectorRepr(v);
		b.setVectorRepr(v);

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
		RealVector v = new ArrayRealVector(d_v);
		RealVector w = new ArrayRealVector(d_w);

		a.setVectorRepr(v);
		b.setVectorRepr(w);

		allUsers.add(b);
		allUsers.add(a);
		ArrayList<UserDetails> retUsers = r.mostSimilarUsers(a, allUsers);
		assertEquals(retUsers.get(0), a);
	}

}
