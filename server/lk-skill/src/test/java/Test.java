
public class Test {

	public static void main(String[] args) {
		for(int i =1 ;i>0 ;i++){
			if(i%8==1 && i%6==3 && i%5==1 && i%4==1 && i%7==0){
				System.out.println(i);break;
			}
		}
	}
}
