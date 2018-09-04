package de.alpharogroup.db.resource.bundles.domain;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.evaluators.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link BundleName}
 */
public class BundleNameTest
{

	/**
	 * Test method for {@link BundleName} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		BaseName baseName = BaseName.builder().name("foo").build();
		LanguageLocale languageLocale = LanguageLocale.builder().locale("de").build();
		BundleName model = new BundleName();
		assertNotNull(model);
		model = BundleName.builder().baseName(baseName).locale(languageLocale).build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link BundleName#equals(Object)} , {@link BundleName#hashCode()} and
	 * {@link BundleName#toString()}
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
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(BundleName.class);
		expected = true;
		assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link BundleName}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(BundleName.class);
	}
}
