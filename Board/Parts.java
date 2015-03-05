package Board;

abstract public class Parts {
	int[] sq = null;
	
	public Parts(int length) {
		sq = new int[length];
	}
	
	public boolean inArray(int val) {
		for(int i = 0; i < sq.length; i++) {
			if(sq[i] == val)
				return true;
		}
		return false;
	}
	
	public void input(int val) {
		for(int i = 0; i < sq.length; i++) {
			if(sq[i] == 0) {
				sq[i] = val;
				break;
			}
		}
	}
	
	public void remove(int val) {
		for(int i = 0; i < sq.length; i++) {
			if(sq[i] == val) {
				sq[i] = 0;
				 break;
			}
		}
	}
}
