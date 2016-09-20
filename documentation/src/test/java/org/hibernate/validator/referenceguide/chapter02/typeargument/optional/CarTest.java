package org.hibernate.validator.referenceguide.chapter02.typeargument.optional;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarTest {

	private static Validator validator;

	@BeforeClass
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.byProvider( HibernateValidator.class )
				.configure()
				.buildValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void validateOptionalTypeArgumentConstraint() {
		//tag::validateOptionalTypeArgumentConstraint[]
		Car car = new Car();
		car.setTowingCapacity( 100 );

		Set<ConstraintViolation<Car>> constraintViolations = validator.validate( car );

		assertEquals( 1, constraintViolations.size() );
		assertEquals( "Not enough towing capacity.",
				constraintViolations.iterator().next().getMessage() );
		assertEquals( "towingCapacity",
				constraintViolations.iterator().next().getPropertyPath().toString() );
		//end::validateOptionalTypeArgumentConstraint[]
	}

}