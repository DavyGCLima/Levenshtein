package com.davylima.normaliser_job_title;

import com.davylima.normaliser_job_title.implementations.NormaliseLevenshtein;
import com.davylima.normaliser_job_title.interfaces.Normaliser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NormaliserJobTitleApplicationTests {
	private final List<String> dataAccess = new DataAccess().retrieveData();

	@Test
	void normalizeSample() {
		//sample code
		String jt = "Java engineer";
		Normaliser n = new NormaliseLevenshtein();
		String normalisedTitle = n.normalise(jt);
		//output normalisedTitle
		Assertions.assertEquals(dataAccess.get(0), normalisedTitle);

		jt = "C# engineer";
		normalisedTitle = n.normalise(jt);
		//output normalisedTitle
		Assertions.assertEquals(dataAccess.get(0), normalisedTitle);

		jt = "Chief Accountant";
		normalisedTitle = n.normalise(jt);
		//output normalisedTitle
		Assertions.assertEquals(dataAccess.get(1), normalisedTitle);
	}

}
