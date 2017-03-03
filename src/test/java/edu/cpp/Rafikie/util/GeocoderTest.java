package edu.cpp.Rafikie.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeocoderTest {
	
	public GeocoderTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void testGeocodeLA() {
		String location = "Los Angeles";
		Double[] expResult = {34.054935,-118.2444759};
		Double[] result = Geocoder.geocode(location);
		assertArrayEquals(expResult, result);
	}
	
	@Test
	public void testGeocodeLACA() {
		String location = "Los Angeles, California";
		Double[] expResult = {34.054935,-118.2444759};
		Double[] result = Geocoder.geocode(location);
		assertArrayEquals(expResult, result);
	}

	@Test
	public void testGetDistanceMeters() {
		Double[] a = {34.055903, -117.820416};
		Double[] b = {34.057540, -117.751457};
		Double dist = Geocoder.getDistanceMeters(a, b);
		assertEquals(dist, 6370.0, 5);
	}

}
