/*
 * Copyright 2016 michael-simons.eu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.bootifultodos.feiertage;

import de.bootifultodos.feiertage.FeiertagsBerechnung;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Michael J. Simons, 2016-12-06
 */
@RunWith(Parameterized.class)
public class FeiertagsformelnTest {

	@Parameters
	public static Collection<Object[]> data() {
		final Function<Integer, LocalDate> berechneOstersonntag = new FeiertagsBerechnung.Ostersonntag();
		final Function<Integer, LocalDate> berechneBussUndBettag = new FeiertagsBerechnung.BussUndBettag();

		return Arrays.asList(new Object[][]{
			{berechneOstersonntag, 1979, LocalDate.of(1979, 4, 15)},
			{berechneOstersonntag, 2015, LocalDate.of(2015, 4, 5)},
			{berechneOstersonntag, 2016, LocalDate.of(2016, 3, 27)},
			{berechneOstersonntag, 2017, LocalDate.of(2017, 4, 16)},
			{berechneOstersonntag, 2018, LocalDate.of(2018, 4, 1)},
			{berechneOstersonntag, 2019, LocalDate.of(2019, 4, 21)},
			{berechneOstersonntag, 2020, LocalDate.of(2020, 4, 12)},
			{berechneBussUndBettag, 2015, LocalDate.of(2015, 11, 18)},
			{berechneBussUndBettag, 2016, LocalDate.of(2016, 11, 16)},
			{berechneBussUndBettag, 2017, LocalDate.of(2017, 11, 22)},
			{berechneBussUndBettag, 2018, LocalDate.of(2018, 11, 21)},
			{berechneBussUndBettag, 2019, LocalDate.of(2019, 11, 20)},
			{berechneBussUndBettag, 2020, LocalDate.of(2020, 11, 18)},
			{berechneBussUndBettag, 2021, LocalDate.of(2021, 11, 17)},
			{berechneBussUndBettag, 2022, LocalDate.of(2022, 11, 16)},
			{berechneBussUndBettag, 2023, LocalDate.of(2023, 11, 22)}
		});
	}

	private final Function<Integer, LocalDate> function;

	private final int jahr;

	private final LocalDate expected;

	public FeiertagsformelnTest(final Function<Integer, LocalDate> functionUnderTest, final int jahr, final LocalDate expected) {
		this.function = functionUnderTest;
		this.jahr = jahr;
		this.expected = expected;
	}

	@Test
	public void formelShouldWork() {
		assertThat(this.function.apply(jahr), is(equalTo(expected)));
	}
}
