package java_interview.ch05;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MostCommonWord {
	static public String mostCommonWord(String paragraph, String[] banned) {
		String[] words = paragraph
			// 정규식에서 \W는 단어 문자가 아닌 것을 뜻한다. 단어 문자일 경우 \w
			// 문자 뒤 +는 연속적인 값을 의미 -> a+는 a와 aa를 의미
			// \W+ 는 연속적으로 단어 문자가 아닌 값을 의미
			// replaceAll 에서는 \ 앞에 \를 하나 더 붙여서 \\W+ 와 같은 형태로 처리
			.replaceAll("\\W+", " ")
			.toLowerCase()
			.split(" ");

		Map<String, Integer> stringCount = new HashMap<>();

		for (String w : words) {
			if (!Arrays.stream(banned).anyMatch(b -> b.equals(w))) {
				if (stringCount.containsKey(w)) {
					int count = stringCount.get(w);
					stringCount.put(w, ++count);
				} else {
					stringCount.put(w, 1);
				}
			}
		}
		// entrySet()을 통해 Map의 각 요소에 대한 Map.Entry 객체를 담은 Set을 반환
		// Map.Entry.comparingByValue() 즉 Entry의 값을 기준으로 비교
		return Collections.max(stringCount.entrySet(), Map.Entry.comparingByValue()).getKey();
	}

	public static void main(String[] args) {
		String[] banned = new String[1];
		banned[0] = "hit";
		System.out.println(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", banned));
	}
}

