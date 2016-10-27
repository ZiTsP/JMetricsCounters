package zitsp.jmetricscounter.cyclomaticcomplexity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class CyclomaticComplexity {

	private CyclomaticComplexity() {
	}
	
	public static long count(Path path) {
		return (!Files.exists(path) || Files.isDirectory(path)) ? -1 : countType1(path);
	}

//	private static final String IF = "^\\s*if";
//	private static final String ELSE_IF = "^\\s*else\\s+if";
//	private static final String SWITCH = "^\\s*case";
//	private static final String FOR = "^\\s*for";
//	private static final String WHILE = "\\s*while\\s*\\(";
//	private static final String SELECT_OPERATOR = "^.+\\?.+\\:.+\\;";
//	private static final String IFDEF = "^\\#ifdef";
//	private static final String ELIF = "^\\#elif";
	private static final String COMPLEXITY_KEYWORDS_STRING 
		= "^\\s*if|^\\s*else\\s+if|^\\s*case|^.+\\?.+\\:.+\\;|^\\s*for\\s*\\(|\\s*while\\s*|^\\#ifdef|^\\#elif";

	private static final Pattern COMPLEXITY_KEYWORDS = Pattern.compile(COMPLEXITY_KEYWORDS_STRING);

	private static long countType1(Path path) {
		try {
			return Files.lines(path).map(String::trim).filter(e -> COMPLEXITY_KEYWORDS.matcher(e).find()).count();
		} catch (IOException e) {
			return -1;
		}
	}

	private static final String AND_OPERATOR = "\\&\\&";
	private static final String OR_OPERATOR = "\\|\\|";
	private static final String LOGICAL_OPERATORS_STRING 
		= ".+\\?\\?.+|.+\\|\\|.+";

	private static final Pattern LOGICAL_OPERATORS = Pattern.compile(LOGICAL_OPERATORS_STRING);
	
	private static long countType2(Path path) {
		try {
			long type1 = countType1(path);
			long type2 = Files.lines(path).map(String::trim).filter(e -> LOGICAL_OPERATORS.matcher(e).find())
					.mapToLong(e -> (e.split(AND_OPERATOR).length + e.split(OR_OPERATOR).length -2)).sum();
			return type1 + type2;
		} catch (IOException e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		// This File
		System.out.println(CyclomaticComplexity.count(Paths.get("./src/zitsp/jmetricscounter/cyclomaticcomplexity/CyclomaticComplexity.java")));
		System.out.println(CyclomaticComplexity.countType2(Paths.get("./src/zitsp/jmetricscounter/linesofcode/LinesOfCode.java")));
	}
}
