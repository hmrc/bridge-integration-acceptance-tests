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

import models.search.*
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.ExploreContext
import steps.helpers.ExploreStepHelper

class ExploreFeatureSpec extends FixtureAnyFeatureSpec with GivenWhenThen with Matchers with ExploreStepHelper {

  override type FixtureParam = ExploreContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = ExploreContext()
    try test(context)
    finally ()
  }

  Feature("Explore API Test") {
    Scenario("Explore status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the explore api with $personForeignId")
      explore(context, personForeignId)

      Then("the response should contain the following details")

      val expectedResponse: ExploreResult =
        ExploreResult(
          ExploreResults(
            List(
              ExploreRecord(
                ExploreData(
                  ValuationList(
                    Id(Some("1234567895657")),
                    Classification(
                      Some("CVW"),
                      Some("Council tax valuation list for a billing authority in Wales")
                    ),
                    Some(
                      Country(
                        Some("W92000005"),
                        Some("Wales")
                      )
                    ),
                    CollectionAuthority(
                      Some("W07000065"),
                      Some("Ceredigion")
                    ),
                    Some(
                      InforcementPeriod(
                        Some("20050401T000000Z"),
                        None
                      )
                    ),
                    Some(
                      ListAdministration(
                        Some("20261110T135207Z"),
                        Some("20261110T135207Z"),
                        Some("1051")
                      )
                    )
                  ),
                  ListEntry(
                    Some(
                      Id(
                        Some("5DE7F852-11CE-4A78-99D5-530A773BA600")
                      )
                    ),
                    Some(
                      DesignatedPerson(
                        None,
                        None,
                        None
                      )
                    ),
                    None,
                    Some(
                      Use(
                        Some("Domestic Use"),
                        Some("N"),
                        Some("N")
                      )
                    ),
                    Valuation(
                      Some("D"),
                      Some(
                        Method(
                          Some("V07000032"),
                          None
                        )
                      ),
                      None
                    ),
                    Some(
                      Period(
                        Some("20050401T000000Z"),
                        Some("20230331T235959Z")
                      )
                    ),
                    Some(
                      Administration(
                        Some("20261110T135207Z"),
                        Some("1"),
                        Some("1"),
                        Some("R"),
                        Some("Not Transitionally"),
                        None
                      )
                    ),
                    Some(
                      Workflow(
                        Some("R5R875-B52D043-F767863-66ZZZ")
                      )
                    ),
                    Some(
                      Property(
                        Some(
                          Id(
                            Some("B60A1694-88DD-4B88-B4C1-8A871D37DB4F")
                          )
                        ),
                        Some("273996000003"),
                        Some(
                          Address(
                            Some("3, Apartment 125 Block B, Cardiff, CF10 1EP"),
                            Some("3"),
                            Some("CF10 1EP"),
                            None
                          )
                        ),
                        Some(
                          PropertyWorkflow(
                            Some("N")
                          )
                        )
                      )
                    )
                  )
                )
              ),
              ExploreRecord(
                ExploreData(
                  ValuationList(
                    Id(Some("1234567895657")),
                    Classification(
                      Some("CVW"),
                      Some("Council tax valuation list for a billing authority in Wales")
                    ),
                    Some(
                      Country(
                        Some("W92000005"),
                        Some("Wales")
                      )
                    ),
                    CollectionAuthority(
                      Some("W07000065"),
                      Some("Ceredigion")
                    ),
                    Some(
                      InforcementPeriod(
                        Some("20050401T000000Z"),
                        None
                      )
                    ),
                    Some(
                      ListAdministration(
                        Some("20261110T135207Z"),
                        Some("20261110T135207Z"),
                        Some("1051")
                      )
                    )
                  ),
                  ListEntry(
                    Some(
                      Id(
                        Some("08767223-B323-41BF-B602-58CDE343FFBE")
                      )
                    ),
                    Some(
                      DesignatedPerson(
                        None,
                        None,
                        None
                      )
                    ),
                    None,
                    Some(
                      Use(
                        Some("Domestic Use"),
                        Some("C"),
                        Some("N")
                      )
                    ),
                    Valuation(
                      Some("E"),
                      Some(
                        Method(
                          Some("V07000032"),
                          None
                        )
                      ),
                      None
                    ),
                    Some(
                      Period(
                        Some("20230401T000000Z"),
                        None
                      )
                    ),
                    Some(
                      Administration(
                        Some("20261110T135207Z"),
                        Some("1"),
                        Some("1"),
                        Some("E"),
                        Some("Transitionally Certified"),
                        None
                      )
                    ),
                    Some(
                      Workflow(
                        Some("R5R875-B52D043-F767863-66ZZZ")
                      )
                    ),
                    Some(
                      Property(
                        Some(
                          Id(
                            Some("B60A1694-88DD-4B88-B4C1-8A871D37DB4F")
                          )
                        ),
                        Some("273996000003"),
                        Some(
                          Address(
                            Some("3, Apartment 125 Block B, Cardiff, CF10 1EP"),
                            Some("3"),
                            Some("CF10 1EP"),
                            None
                          )
                        ),
                        Some(
                          PropertyWorkflow(
                            Some("N")
                          )
                        )
                      )
                    )
                  )
                )
              )
            )
          )
        )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
