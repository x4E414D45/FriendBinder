package recommender;

import com.websystique.springmvc.model.User;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
		User a = new User();
		User b = new User();
		User[] allUsers = new User[1];

		Double d_v[] = {1.0, 2.0, 3.0};
		RealVector v = new ArrayRealVector(d_v);

		a.setVectorRepr(v);
		b.setVectorRepr(v);

		allUsers[0] = b;
		User[] retUsers = r.mostSimilarUsers(a, allUsers);
		assertEquals(retUsers[0], allUsers[0]);
	}

	@Test
	public void testMostSimilarUsers1() {
		Recommender r = new Recommender();
		User a = new User();
		User b = new User();
		User[] allUsers = new User[2];

		Double d_v[] = {1.0, 2.0, 3.0};
		Double d_w[] = {1.0, 1.0, 3.0};
		RealVector v = new ArrayRealVector(d_v);
		RealVector w = new ArrayRealVector(d_w);

		a.setVectorRepr(v);
		b.setVectorRepr(w);

		allUsers[0] = b;
		allUsers[1] = a;
		User[] retUsers = r.mostSimilarUsers(a, allUsers);
		assertEquals(retUsers[0], a);
	}
	
}
