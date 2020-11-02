/*
 * Copyright 2017 michael-simons.eu.
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

import static de.bootifultodos.feiertage.Bundesland.GesetzlicherFeiertag.Neujahr;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Optional;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Michael J. Simons, 2017-02-20
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(includeFilters
	= @ComponentScan.Filter(
		type = FilterType.ASSIGNABLE_TYPE,
		classes = FeiertagsBerechnung.class
	)
)
@AutoConfigureRestDocs(
	outputDir = "target/generated-snippets",
	uriHost = "bootifultodos.de",
	uriPort = 80
)
public class EndpointIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BundeslandRepository bundeslandRepository;

	private final Bundesland nrw = new Bundesland(
		(short) 5,
		"NW", "Nordrhein-Westfalen",
		asList(Bundesland.GesetzlicherFeiertag.values())
	);

	@Test
	public void invalidBundeslandShouldWork()
		throws Exception
	{
		when(bundeslandRepository
			.findOneByNummer((short) 23)
		).thenReturn(Optional.empty());

		mockMvc
			.perform(get(
				"/api/feiertage/{jahr}/{bundeslandnummer}",
				2017, 23
			))
			.andExpect(status().isNotFound());
	}

	@Test
	public void feiertageShouldWork() throws Exception {
		when(bundeslandRepository
			.findOneByNummer((short) 5)
		).thenReturn(Optional.of(nrw));

		mockMvc
			.perform(get(
				"/api/feiertage/{jahr}/{bundeslandnummer}",
				2017, 5
			))
			.andExpect(status().isOk())
			.andExpect(jsonPath(
				"$", hasSize(nrw.getFeiertage().size())))
			.andExpect(jsonPath(
				"$[0].datum", equalTo("2017-01-01")))
			.andExpect(jsonPath(
				"$[0].feiertag", equalTo(Neujahr.name())))
			.andDo(document("feiertage/get",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				pathParameters(
					parameterWithName("jahr").description("Das Jahr der Berechnung"),
					parameterWithName("bundeslandnummer").description("Nummer des Bundeslandes")
				),
				responseFields(
					fieldWithPath("[]").description("Eine Liste mit Feiertagen im angefragen Jahr und Bundesland"),
					fieldWithPath("[].datum").description("Datum des Feiertages"),
					fieldWithPath("[].feiertag").description("Kürzel des Feiertages")
				)))
			;
	}

	@Test
	public void bundeslaenderShouldWork() throws Exception {
		when(bundeslandRepository
			.findAll()
		).thenReturn(Arrays.asList(nrw));

		mockMvc
			.perform(get("/api/bundeslaender"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andDo(document("bundeslaender/get",
				preprocessRequest(prettyPrint()),
				preprocessResponse(prettyPrint()),
				responseFields(
					fieldWithPath("[]")
						.description("Liste aller Bundesländer"),
					fieldWithPath("[].kuerzel")
						.description("Amtliches Kürzel"),
					fieldWithPath("[].name")
						.description("Offizieller Name"),
					fieldWithPath("[].nummer")
						.description("Offizielle Nummer"),
					fieldWithPath("[].feiertage")
						.description("Liste der Feiertage")
				)))
			;
	}
}