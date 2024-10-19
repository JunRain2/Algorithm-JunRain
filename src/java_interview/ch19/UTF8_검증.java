package java_interview.ch19;

/**
 * TODO 맨 처음 문자를 통해서 몇 바이트가 UTF-8 인지 파악
 */
public class UTF8_검증 {
	public boolean check(int[] data, int start, int size) {
		// size 만큼 이동하면서 각 바이트가 10으로 시작 여부 확인
		for (int i = start + 1; i < start + size + 1; i++) {
			if (i >= data.length || data[i] >> 6 != 0b10) {
				return false;
			}
		}
		return true;
	}

	public boolean validUtf8(int[] data) {
		int start = 0;
		// 문자 전체 길이만큼 순회
		while (start < data.length) {
			// 문자 시작 바이트
			int first = data[start];
			// 11110으로 시작하면 나머지 비트가 3바이트여야 한다.
			if (first >> 3 == 0b11110 && check(data, start, 3)) {
				start += 4;
			}
			// 1110으로 시작하면 나머지가 2바이트여야 한다.
			else if (first >> 4 == 0b1110 && check(data, start, 2)) {
				start += 3;
			}
			// 110으로 시작하면 나머지가 1바이트여야 한다.
			else if (first >> 5 == 0b110 && check(data, start, 1)) {
				start += 2;
			}
			// 1바이트 문자
			else if (first >> 7 == 0) {
				start++;
			} else {
				return false;
			}
		}
		return true;
	}
}
