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

import com.fasterxml.jackson.annotation.JsonView;
import de.bootifultodos.feiertage.Endpoint.Views;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Repräsentiert deutsche Bundesländer. Die Bundeslandnummer ist im amtlichen
 * <a href="https://de.wikipedia.org/wiki/Amtlicher_Gemeindeschlüssel">Gemeindeschlüssel</a>
 * vorgeben. Die Kürzel entsprechen den offiziellen, europaweit verwendeten
 * Kürzeln deutscher Bundesländer.
 *
 * @author Michael J. Simons, 2016-11-30
 */
@AllArgsConstructor
@Getter
@Document(collection = "bundeslaender")
public final class Bundesland {

    /**
     * Die MongoDB interne ID. Soll nicht nach außen gegeben werden.
     */
    @Getter(AccessLevel.NONE)
    private final String id;

    /**
     * Bundeslandnummer laut amtlichen Gemeindeschlüssel.
     */
    private final String nummer;

    /**
     * Offizielles Kürzel des Bundeslandes.
     */
    private final String kuerzel;

    /**
     * Offizieller Name des Bundeslandes.
     */
    private final String name;

    /**
     * Die Liste der gesetzlichen Feiertage, die in diesem Bundesland gelten.
     */
    @JsonView(Views.Bundeslaender.class)
    private final List<String> feiertage;
}
