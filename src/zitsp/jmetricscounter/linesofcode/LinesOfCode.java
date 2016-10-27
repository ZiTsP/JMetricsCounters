package zitsp.jmetricscounter.linesofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LinesOfCode {
	
	private LinesOfCode(){
	}

	public static long count(Path path) {
		return (!Files.exists(path) || Files.isDirectory(path)) ? -1 : countWithoutBlanks(path);
	}
	
	public static long countTotal(Path path) {
		try {
			return Files.lines(path).count();
		} catch (IOException e) {
			return -1;
		}
	}
	
	public static long countWithoutBlanks(Path path) {
		try {
			return Files.lines(path).filter(e -> !e.trim().isEmpty()).count();
		} catch (IOException e) {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		// This File
		System.out.println(LinesOfCode.count(Paths.get("./src/zitsp/jmetricscounter/linesofcode/LinesOfCode.java")));
	}

}
