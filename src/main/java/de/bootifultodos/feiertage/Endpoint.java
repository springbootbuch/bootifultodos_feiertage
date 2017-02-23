/*
 * Copyright 2016-2017 michael-simons.eu.
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

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael J. Simons, 2016-12-07
 */
@RequiredArgsConstructor
@RestController
public class Endpoint {

	/**
	 * Eine Exception für den Fall, dass ein Bundesland nicht gefunden wird. Sie
	 * ist annotiert mit einem {@link ResponseStatus} so dass kein expliziter
	 * {@code ControllerAdvice} benötigt wird.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class BundeslandNotFoundException
		extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	private final BundeslandRepository bundeslandRepository;

	private final FeiertagsBerechnung feiertagsBerechnung;

	/**
	 * @return Eine Liste aller Bundesländer.
	 */
	@GetMapping("/bundeslaender")
	public List<Bundesland> bundeslaender() {
		return this.bundeslandRepository.findAll();
	}

	@GetMapping("/feiertage/{jahr}/{bundeslandnummer}")
	public List<FeiertagsDatum> feiertage(
		@PathVariable final int jahr,
		@PathVariable final short bundeslandnummer
	) {
		return this.bundeslandRepository
			.findOneByNummer(bundeslandnummer)
			.map(bundesland ->
				this.feiertagsBerechnung
					.feiertageIn(bundesland, jahr)
			)
			.orElseThrow(BundeslandNotFoundException::new);
	}
}
