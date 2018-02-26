package de.alpharogroup.db.resource.bundles.entities;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.evaluations.EqualsHashCodeAndToStringEvaluator;


public class BaseNamesTest
{

	/**
	 * Test method for {@link BaseNames#equals(Object)} , {@link BaseNames#hashCode()} and
	 * {@link BaseNames#toString()}.
	 *
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		boolean expected;
		boolean actual;
		final BaseNames first = BaseNames.builder().build();
		first.setId(1);
		final BaseNames second = new BaseNames("Hello");
		final BaseNames third = (BaseNames)BeanUtils.cloneBean(first);
		final BaseNames fourth = (BaseNames)BeanUtils.cloneBean(first);

		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(first, second,
			third, fourth);
		expected = true;
		assertEquals(expected, actual);
	}
}
