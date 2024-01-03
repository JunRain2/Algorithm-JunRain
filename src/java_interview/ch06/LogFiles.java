package java_interview.ch06;

import java.util.ArrayList;
import java.util.List;

public class LogFiles {

	String[] solution(List<String> logs) {
		// 문자 로그를 저장
		List<String> letterList = new ArrayList<>();
		// 숫자 로그를 저장
		List<String> digitList = new ArrayList<>();

		/**
		 * log.split(" ") 을 통해 공백으로 log를 분리
		 * [1] 을 통해 공백 후 첫번째, 즉 식별자 다음의 문자열을 가져옴
		 * charAt(0)을 통해 식별자 다음의 문자열 중 제일 첫번째 문자(char)를 가져옴
		 * Character.isDigit()를 통해 해당 문자가 숫자면 ture를 아니면 false를 반환한다.
		 **/
		for (String log : logs) {
			if (Character.isDigit(log.split(" ")[1].charAt(0))) {
				digitList.add(log);
			} else {
				letterList.add(log);
			}
		}

		letterList.sort((s1, s2) -> {
			// 문자열을 공백을 기준으로 2개로 나눈다.
			String[] s1x = s1.split(" ", 2);
			String[] s2x = s2.split(" ", 2);

			// 문자열끼리 비교
			int compared = s1x[1].compareTo(s2x[1]);
			if (compared == 0) {
				// 같으면 식별자를 비교
				return s1x[0].compareTo(s2x[0]);
			} else {
				return compared;
			}
		});

		letterList.addAll(digitList);

		/**
		 * new String[0]는 초기 크기가 0인 빈 배열을 만들어서
		 * 나중에 toArray 메서드에 의해 필요한 크기로 조절되고
		 * 리스트의 모든 요소가 해당 배열에 복사
		 * 자바 11부터는 String[]::new 를 사용
		 */
		return letterList.toArray(new String[0]);


	}

}
// https://leetcode.com/problems/reorder-data-in-log-files