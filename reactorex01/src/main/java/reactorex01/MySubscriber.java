package reactorex01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

//<T> 구독할 데이터 타입 //구독
public class MySubscriber implements Subscriber<Integer>{

	private Integer size = 3; //nobounded
	private Subscription s;
	
	@Override
	public void onSubscribe(Subscription s) {
		this.s = s;
		System.out.println("3. MySubscriber - 구독 정보 돌려 받음");

		
		// 신문줘!!
		System.out.println("4. MySubscriber - 구독 시작 지시");
		s.request(size); // 내가 하루에 읽을 수 있는 글자수의 한계 (백프레셔 = Backpressure)		
	}

	
	//데이터를 돌려받는 함수 //신문을 주는것
	@Override
	public void onNext(Integer t) {
		System.out.println("onNext - 신문 "+t);
		size --; //1
		if(size == 0) {
			size = 3;
			s.request(size);
		}
	}

	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onComplete() {
		System.out.println("신문 다 받음");
		
	}

}
