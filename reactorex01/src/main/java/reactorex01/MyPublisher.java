package reactorex01;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

// <T> 발행정보 데이터의 타입 //발행자
public class MyPublisher implements Publisher<Integer> {

	//데이터베이스 - 정보를 가지고 있음.
	Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	
	//구독
	@Override
	public void subscribe(Subscriber<? super Integer> s) {
		System.out.println("1. MyPublisher - 구독");
		s.onSubscribe(new MySubscription((MySubscriber)s, its)); //응답
	}
}