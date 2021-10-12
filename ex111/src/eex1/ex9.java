package eex1;

public class ex9 {
	int num;
	int count;
	int price;
	int depart;
	int sum ;
	int rank=1;
	String numn;
	String departn;
	
	public ex9(int num,int count, int price,int depart) {
		this.num= num;
		this.count= count;
		this.price= price;
		this.depart= depart;
		
		num();
		depart();
		sum = price*count;
		
	}

	public ex9() {
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "제품번호-" +num+" 제품명-"+numn + " 수량-"+count+ " 단가-" +price+ " 금액-"+sum+" 부서명-"+departn;
	}
	public String num() {
		String[] m = {"TV","갤러시노트","냉장고","컴퓨터","세탁기"};
		this.numn=m[num/10-1];
		return this.numn;
	}
	public String depart() {
		String[] k = {"TV","갤러시노트","냉장고","컴퓨터","세탁기"};
		this.departn=k[depart/10-1];
		return departn;
	}
	
	public int getsum() {
		return this.sum;
	}


}
