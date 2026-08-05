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
        List(
          Person(
            Some(63),
            "1.63.1",
            "Person 1",
            "Person Label 1",
            "A Government Gateway authenticated natural, corporate or crown user of online services provided by the UK Government/HMRC whose type and class are not yet known or re-confirmed.",
            Some("20260804T135936Z"),
            None,
            CodeMeaning(Some("LTX-DOM-PSN"), Some("Local taxation domain person")),
            CodeMeaning(Some("COR"), Some("Corporate")),
            CodeMeaning(Some("GGY"), Some("Any government gateway user")),
            PersonItemData(
              List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
              List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
              List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
              NameData(Some("Mr"), None, Some("Alex"), Some("Tester63"), None, None, None, Some("P63")),
              Communications(
                Some("1 High Street, Telford, TF1 1AA"),
                Some("07700 90063"),
                Some("person63@example.test")
              )
            ),
            List(),
            Metadata(
              SendingMetadata(
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                ),
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                ),
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                )
              ),
              ReceivingMetadata(
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                ),
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                ),
                MetadataStage(
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map(),
                  Map()
                )
              )
            ),
            Map(),
            List(
              PersonItem(
                Some(59),
                "1.2.1.1",
                "Persona 1",
                "Persona Label 1",
                "A local taxation domain authenticated persona whose type and class are not yet known or re-confirmed, and that is assignable to the person.",
                Some("20260804T135936Z"),
                None,
                CodeMeaning(Some("LTX-DOM-PSA"), Some("Local taxation domain persona")),
                CodeMeaning(Some("TXP"), Some("LGFA taxpayer")),
                CodeMeaning(Some("RPO"), Some("Ratepayer (occupier)")),
                PersonItemData(
                  List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
                  List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
                  List(ForeignId(Some("Government_Gateway"), Some("UK"), Some("123456789567"))),
                  NameData(None, None, Some("Role 59"), Some("Holder"), None, None, None, Some("PR59")),
                  Communications(
                    Some("PO Box 159, Telford, TF2 2BB"),
                    Some("020 7946 0059"),
                    Some("persona59@example.test")
                  )
                ),
                List(),
                Metadata(
                  SendingMetadata(
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    )
                  ),
                  ReceivingMetadata(
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    )
                  )
                ),
                Map(),
                List()
              )
            )
          )
        )
      )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
