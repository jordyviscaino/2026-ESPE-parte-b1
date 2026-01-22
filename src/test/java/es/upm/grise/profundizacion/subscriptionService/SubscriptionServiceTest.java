package es.upm.grise.profundizacion.subscriptionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import es.upm.grise.profundizacion.exceptions.ExistingUserException;
import es.upm.grise.profundizacion.exceptions.NullUserException;
import es.upm.grise.profundizacion.exceptions.LocalUserDoesNotHaveNullEmailException;

public class SubscriptionServiceTest {
	
	@Test
	public void smokeTest() {}
	
	@Test
	public void TestExistenUsuarios(){
       SubscriptionService service = new SubscriptionService();
        User user = new User();
        user.setEmail("test@test");
        user.setDelivery(Delivery.DO_NOT_DELIVER);
        assertDoesNotThrow(() -> {
            service.addSubscriber(user);
        });
	
	}

	@Test
	public void TestNoExistenUsuarios(){
	    SubscriptionService service = new SubscriptionService();
	   User user = null;
	   assertThrows(es.upm.grise.profundizacion.exceptions.NullUserException.class, () -> {
		   service.addSubscriber(user);
	   });
	}

	@Test 
	public void TestUsuarioyaSuscrito(){
	      SubscriptionService service = new SubscriptionService();
        User user = new User();
        user.setEmail("test@test");
        user.setDelivery(Delivery.DO_NOT_DELIVER);
        assertDoesNotThrow(() -> service.addSubscriber(user));
        assertThrows(ExistingUserException.class, () -> service.addSubscriber(user));
	}	

	@Test
	public void TestDeliveryLocalConEmail(){
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setEmail("test@test");
		user.setDelivery(Delivery.LOCAL);
		assertThrows(es.upm.grise.profundizacion.exceptions.LocalUserDoesNotHaveNullEmailException.class, () -> {
				service.addSubscriber(user);
		});
	}
	@Test
	public void TestDeliveryLocalSinEmail(){
		SubscriptionService service = new SubscriptionService();
        User user = new User();
        user.setEmail(null);
        user.setDelivery(Delivery.LOCAL);
        assertDoesNotThrow(() -> service.addSubscriber(user));
		}

    @Test 
	public void TestDeliveryNoLocalConEmail(){
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setEmail("test@test");
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		assertDoesNotThrow(() -> {
			service.addSubscriber(user);
		});
	}
	@Test
	public void TestDeliveryNoLocalSinEmail(){
		SubscriptionService service = new SubscriptionService();
		User user = new User();
		user.setEmail(null);
		user.setDelivery(Delivery.DO_NOT_DELIVER);
		assertDoesNotThrow(() -> {
			service.addSubscriber(user);
		});
	}
}
