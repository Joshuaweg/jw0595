import static org.junit.Assert.assertEquals;
import org.junit.Test;
//runs the test cases specified in documentation
public class RentalAgreementTest{
	private RentalAgreement rentalAgreement = new RentalAgreement();

	//Test 1
	//code JAKR
	//checkout date 9/3/15
	//rental days 5
	//Discount 101%
	@Test
	public void test1(){
		System.out.println("Test1");
		rentalAgreement = new RentalAgreement("JAKR","09/03/15",5,101);
		assertEquals(false,rentalAgreement.isAccepted());
		rentalAgreement.print();
	}
	//Test 2
	//code LADW
	//checkout date 7/2/20
	//rental days 3
	//Discount 10%
	@Test
	public void test2(){
		System.out.println("Test2");
		float delta = 0.0001f;
		rentalAgreement=new RentalAgreement("LADW","07/02/20",3,10);
		assertEquals(true,rentalAgreement.isAccepted());
		assertEquals(2, rentalAgreement.getChargeDays());
		assertEquals((float)1.99*2,rentalAgreement.getPreDiscount(),delta);
		assertEquals((float)1.99*2*0.9,rentalAgreement.getFinalCharge(),delta);
                rentalAgreement.print();
	}
	//Test 3
	//code CHNS
	//checkout date 7/2/15
	//rental days 5
	//Discount 25%
	@Test
	public void test3(){
		System.out.println("Test3");
		rentalAgreement=new RentalAgreement("CHNS","07/02/15",5,25);
		float delta = 0.0001f;
		assertEquals(true,rentalAgreement.isAccepted());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals((float)1.49*3,rentalAgreement.getPreDiscount(),delta);
		assertEquals((float)1.49*3*0.75,rentalAgreement.getFinalCharge(),delta);
        rentalAgreement.print();
	}
	//Test 4
	//code JAKD
	//checkout date 9/3/15
	//rental days 6
	//Discount 0%
	@Test
	public void test4(){
		System.out.println("Test4");
		float delta = 0.0001f;
		rentalAgreement=new RentalAgreement("JAKD","09/03/15",6,0);
		assertEquals(true,rentalAgreement.isAccepted());
		assertEquals(3, rentalAgreement.getChargeDays());
		assertEquals((float)2.99*3,rentalAgreement.getPreDiscount(),delta);
		assertEquals((float)2.99*3,rentalAgreement.getFinalCharge(),delta);
        rentalAgreement.print();
	}
	//Test 5
	//code JAKR
	//checkout date 7/2/15
	//rental days 9
	//Discount 0%
	@Test
	public void test5(){
		System.out.println("Test5");
		float delta = 0.0001f;
		rentalAgreement=new RentalAgreement("JAKR","07/02/15",9,0);
		assertEquals(true,rentalAgreement.isAccepted());
		assertEquals(5, rentalAgreement.getChargeDays());
		assertEquals((float)2.99*5,rentalAgreement.getPreDiscount(),delta);
		assertEquals((float)2.99*5,rentalAgreement.getFinalCharge(),delta);
        rentalAgreement.print();
	}
	//Test 6
	//code JAKR
	//checkout date 7/2/20
	//rental days 4
	//Discount 50%
	@Test
	public void test6(){
		System.out.println("Test6");
		float delta = 0.0001f;
		rentalAgreement=new RentalAgreement("JAKR","07/02/20",4,50);
		assertEquals(true,rentalAgreement.isAccepted());
		assertEquals(1, rentalAgreement.getChargeDays());
		assertEquals((float)2.99*1,rentalAgreement.getPreDiscount(),delta);
		assertEquals((float)2.99*1*0.5,rentalAgreement.getFinalCharge(),delta);
        rentalAgreement.print();
	}

}
