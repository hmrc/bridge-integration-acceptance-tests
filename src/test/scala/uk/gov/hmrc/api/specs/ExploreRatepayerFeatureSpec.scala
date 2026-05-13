/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.api.specs

import models.{CodeMeaning, Communications, ForeignId, Metadata, MetadataStage, NameData, Person, PersonItem, PersonItemData, Persons, ReceivingMetadata, SendingMetadata}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.ExploreRatepayerContext
import steps.helpers.ExploreRatepayerStepHelper

class ExploreRatepayerFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with ExploreRatepayerStepHelper {

  override type FixtureParam = ExploreRatepayerContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = ExploreRatepayerContext()
    try test(context)
    finally ()
  }

  Feature("Explore Ratepayer API Test") {
    Scenario("Retrieve a Ratepayer Status Response") { context =>
      val personForeignId           = "123456789567"
      When(s"the get request is sent to the explore ratepayer endpoint with $personForeignId")
      exploreRatepayer(context, personForeignId)
      Then("the response should contain the following details")
      val expectedResponse: Persons = Persons(
        persons = List(
          Person(
            id = Some(16L),
            idx = "1.16.1",
            name = "Person 1",
            label = "Person Label 1",
            description =
              "A Government Gateway authenticated natural, corporate or crown user of online services provided by the UK Government/HMRC whose type and class are not yet known or re-confirmed.",
            origination = Some("20260407T111712Z"),
            termination = None,
            category = CodeMeaning(
              Some("LTX-DOM-PSN"),
              Some("Local taxation domain person")
            ),
            `type` = CodeMeaning(
              Some("COR"),
              Some("Corporate")
            ),
            `class` = CodeMeaning(
              Some("GGY"),
              Some("Any government gateway user")
            ),
            data = PersonItemData(
              foreign_ids = List(
                ForeignId(
                  system = Some("Government_Gateway"),
                  location = Some("UK"),
                  value = Some("123456789567")
                )
              ),
              foreign_names = List.empty,
              foreign_labels = List.empty,
              names = NameData(
                None,
                None,
                None,
                None,
                None,
                None,
                None,
                None
              ),
              communications = Communications(
                None,
                None,
                None
              )
            ),
            protodata = List.empty,
            metadata = Metadata(
              sending = SendingMetadata(
                extracting = MetadataStage(selecting = Map.empty),
                transforming = MetadataStage(
                  filtering = Map.empty,
                  supplementing = Map.empty,
                  recontextualising = Map.empty
                ),
                loading = MetadataStage()
              ),
              receiving = ReceivingMetadata(
                unloading = MetadataStage(),
                transforming = MetadataStage(),
                storing = MetadataStage()
              )
            ),
            compartments = Map.empty,
            items = List(
              PersonItem(
                id = Some(13L),
                idx = "1.2.1.1",
                name = "Persona 1",
                label = "Persona Label 1",
                description =
                  "A local taxation domain authenticated persona whose type and class are not yet known or re-confirmed, and that is assignable to the person.",
                origination = Some("20260407T111712Z"),
                termination = None,
                CodeMeaning(
                  Some("LTX-DOM-PSA"),
                  Some("Local taxation domain persona")
                ),
                `type` = CodeMeaning(
                  Some("TXP"),
                  Some("LGFA taxpayer")
                ),
                `class` = CodeMeaning(
                  Some("RPO"),
                  Some("Ratepayer (occupier)")
                ),
                data = PersonItemData(
                  foreign_ids = List(
                    ForeignId(
                      system = Some("Government_Gateway"),
                      location = Some("UK"),
                      value = Some("123456789567")
                    )
                  ),
                  foreign_names = List.empty,
                  foreign_labels = List.empty,
                  names = NameData(
                    None,
                    None,
                    None,
                    None,
                    None,
                    None,
                    None,
                    None
                  ),
                  communications = Communications(
                    None,
                    None,
                    None
                  )
                ),
                protodata = List.empty,
                metadata = Metadata(
                  sending = SendingMetadata(
                    extracting = MetadataStage(selecting = Map.empty),
                    transforming = MetadataStage(
                      filtering = Map.empty,
                      supplementing = Map.empty,
                      recontextualising = Map.empty
                    ),
                    loading = MetadataStage()
                  ),
                  receiving = ReceivingMetadata(
                    unloading = MetadataStage(),
                    transforming = MetadataStage(),
                    storing = MetadataStage()
                  )
                ),
                compartments = Map.empty,
                items = List.empty
              )
            )
          )
        )
      )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
