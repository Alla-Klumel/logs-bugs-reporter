package telran.logs.bugs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import telran.logs.bugs.dto.LogDto;
import telran.logs.bugs.dto.LogType;
import telran.logs.bugs.random.RandomLogs;

public class RandomLogsTest {
	static Logger LOG = LoggerFactory.getLogger(RandomLogsTest.class);
	private static final String AUTHENTICATION_ARTIFACT ="authentication";
	private static final String AUTHORIZATION_ARTIFACT ="authorization";
	private static final String CLASS_ARTIFACT ="class";
	private static final long N_LOGS = 100000;
	@Autowired
	RandomLogs randomLogs;
	
	@Test
	void logTypeArtifactTest() throws Exception{
		EnumMap<LogType, String> logTypeArtifactsMap = getMapForTest();
		logTypeArtifactsMap.forEach((k,v)->{
			switch(k) {
			case AUTHENTICATION_EXCEPTION:
				assertEquals(AUTHENTICATION_ARTIFACT, v);
				break;
			case AUTHORIZATION_EXCEPTION:
				assertEquals(AUTHORIZATION_ARTIFACT, v);
				break;
				default: testClassArtifact(v);
				
			}
		});
		
		
	}


	private EnumMap<LogType, String> getMapForTest()
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Method getMapMethod = randomLogs.getClass()
				.getDeclaredMethod("getLogArtifactMap");
		getMapMethod.setAccessible(true);
		@SuppressWarnings("unchecked")
		EnumMap<LogType, String> logTypeArtifactsMap =
				(EnumMap<LogType, String>) getMapMethod.invoke(randomLogs);
		return logTypeArtifactsMap;
	}
	@Test
	void generation ()throws Exception{
		List<LogDto>logs = Stream.generate(()->randomLogs.createRandomLog()).limit(N_LOGS).collect(Collectors.toList());
		testLogContent(logs);
		Map<LogType, Long> logTypeOccurrences =
				logs.stream().collect(Collectors.groupingBy(l ->l.logType, Collectors.counting()));
		logTypeOccurrences.forEach((k,v)->{
			LOG.debug("log type: {};count of occurrences:{}", k, v);
		});
		
		
	}

	private void testLogContent(List<LogDto> logs) {
		logs.forEach(log->{
			switch(log.logType) {
			case AUTHENTICATION_EXCEPTION:
				assertEquals(AUTHENTICATION_ARTIFACT, log.artifact);
				assertEquals(0, log.responseTime);
				assertTrue(log.result.isEmpty());
				break;
			case AUTHORIZATION_EXCEPTION:
				assertEquals(AUTHORIZATION_ARTIFACT, log.artifact);
				assertEquals(0, log.responseTime);
				assertTrue(log.result.isEmpty());
				break;
			case NO_EXCEPTION:
				testNoException(log);
				break;
				default:
					testNonSecException(log);
					break;
				
			}
		});
		
	}

	private void testNonSecException(LogDto log) {
		testClassArtifact(log.artifact);
		assertEquals(0, log.responseTime);
		assertTrue(log.result.isEmpty());
		
	
	}
	private void testClassArtifact(String artifact) {
		assertEquals(CLASS_ARTIFACT, artifact.substring(0,5));
		int classNumber = Integer.parseInt(artifact.substring(5));
		assertTrue(classNumber >= 1&& classNumber <=randomLogs.getnClasses());
	}

	private void testNoException(LogDto log) {
		testClassArtifact(log.artifact);
		assertTrue(log.responseTime>0);
		assertTrue(log.result.isEmpty());
		
	}
	

}
