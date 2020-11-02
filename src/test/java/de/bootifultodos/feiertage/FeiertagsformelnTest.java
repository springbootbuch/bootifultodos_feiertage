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

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author Michael J. Simons, 2016-12-06
 */
public class FeiertagsformelnTest {

	public static Stream<Arguments> data() {
		final Function<Integer, LocalDate> berechneOstersonntag = new FeiertagsBerechnung.Ostersonntag();
		final Function<Integer, LocalDate> berechneBussUndBettag = new FeiertagsBerechnung.BussUndBettag();

		return Stream.of(
			Arguments.of(berechneOstersonntag, 1979, LocalDate.of(1979, 4, 15)),
			Arguments.of(berechneOstersonntag, 2015, LocalDate.of(2015, 4, 5)),
			Arguments.of(berechneOstersonntag, 2016, LocalDate.of(2016, 3, 27)),
			Arguments.of(berechneOstersonntag, 2017, LocalDate.of(2017, 4, 16)),
			Arguments.of(berechneOstersonntag, 2018, LocalDate.of(2018, 4, 1)),
			Arguments.of(berechneOstersonntag, 2019, LocalDate.of(2019, 4, 21)),
			Arguments.of(berechneOstersonntag, 2020, LocalDate.of(2020, 4, 12)),
			Arguments.of(berechneBussUndBettag, 2015, LocalDate.of(2015, 11, 18)),
			Arguments.of(berechneBussUndBettag, 2016, LocalDate.of(2016, 11, 16)),
			Arguments.of(berechneBussUndBettag, 2017, LocalDate.of(2017, 11, 22)),
			Arguments.of(berechneBussUndBettag, 2018, LocalDate.of(2018, 11, 21)),
			Arguments.of(berechneBussUndBettag, 2019, LocalDate.of(2019, 11, 20)),
			Arguments.of(berechneBussUndBettag, 2020, LocalDate.of(2020, 11, 18)),
			Arguments.of(berechneBussUndBettag, 2021, LocalDate.of(2021, 11, 17)),
			Arguments.of(berechneBussUndBettag, 2022, LocalDate.of(2022, 11, 16)),
			Arguments.of(berechneBussUndBettag, 2023, LocalDate.of(2023, 11, 22))
		);
	}

	@ParameterizedTest
	@MethodSource("data")
	public void formelShouldWork(Function<Integer, LocalDate> function, Integer jahr, LocalDate expected) {
		assertThat(function.apply(jahr)).isEqualTo(expected);
	}
}
