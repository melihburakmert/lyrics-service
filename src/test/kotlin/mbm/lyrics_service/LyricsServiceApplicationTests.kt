package mbm.lyrics_service

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.modulith.core.ApplicationModules

@SpringBootTest
class LyricsServiceApplicationTests {

	@Test
	fun test_modulith() {
		val modules = ApplicationModules.of(LyricsServiceApplication::class.java)
		modules.forEach(::println)
		modules.verify()
	}

	@Test
	fun contextLoads() {
	}

}
