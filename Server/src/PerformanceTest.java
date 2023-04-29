import org.junit.Rule;
import org.junit.Test;

import com.github.noconnor.junitperf.*;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import dao.PlaylistDAO;
import data.Playlist;
public class PerformanceTest {

	@Rule
	public JUnitPerfRule rule = new JUnitPerfRule(new HtmlReportGenerator("target/Performance/report.html"));
	
	@Test
	@JUnitPerfTest(durationMs = 10000, threads = 20)
	@JUnitPerfTestRequirement(executionsPerSec = 600, maxLatency = 120)	
	public void testPlaylistSaving() throws Exception{
		PlaylistDAO.getInstance().save(new Playlist("PerfTry"));
	}
	
	@Test
	@JUnitPerfTest(durationMs = 2000, threads = 10)
	@JUnitPerfTestRequirement(executionsPerSec = 10, maxLatency = 500, meanLatency = 5)	
	public void test2() throws Exception{
		new Playlist("WaWa");
	}
}
